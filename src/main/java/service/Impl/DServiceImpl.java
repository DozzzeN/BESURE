package service.Impl;

import it.unisa.dia.gas.jpbc.Element;
import mapper.ConsultMapper;
import mapper.ProvStoreMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import pojo.VO.EHR;
import pojo.VO.Provenance;
import service.CSService;
import service.DService;
import service.HService;
import util.ArraysUtil;
import util.BytesUtil;
import util.CryptoUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Base64;

import static service.Impl.SysParamServiceImpl.*;

@Service
public class DServiceImpl implements DService {
    public static Element k_rou_y_rou_plus_1;
    public static byte[] pidD;
    public static Element sigma_PB_l;
    public static EHR ehr;
    public static byte[] perD;
    public static byte[] sigma_perD;
    public static Provenance PB_l;
    public static byte[] C_rou_y_rou_plus_1;
    private final Logger logger = Logger.getLogger(DServiceImpl.class);
    @Resource
    private CSService csServiceImpl;
    @Resource
    private HService hServiceImpl;
    @Resource
    private ProvStoreMapper provStoreMapper;
    @Resource
    private ConsultMapper consultMapper;
    private Element tk;


    @Override
    public void consult_D() {
//        Element b = pairing.getZr().newRandomElement().getImmutable();
//        k_rou_y_rou_plus_1 = aP.duplicate().mulZn(b.duplicate()).getImmutable();
        k_rou_y_rou_plus_1 = pairing.getZr().newRandomElement().getImmutable();
    }

    @Override
    public boolean authenticate(byte[] perD, byte[] sigma_perD) {
        return csServiceImpl.authenticate(perD, sigma_perD);
    }

    @Override
    public void createEHR(String idP, EHR ehr) {
        DServiceImpl.ehr = ehr;
        byte[] ehrBytes = BytesUtil.toByteArray(ehr);
        C_rou_y_rou_plus_1 = CryptoUtil.AESEncrypt(
                CryptoUtil.getHash("SHA-256", k_rou_y_rou_plus_1.duplicate()), ehrBytes);
        int currentStage = consultMapper.selMaxStage(idP) + 1;
        if (currentStage == 1) {
            if (!(consultMapper.insC_rou_y_rou(idP, currentStage, Base64.getEncoder().encodeToString(C_rou_y_rou_plus_1),
                    Base64.getEncoder().encodeToString(k_rou_y_rou_plus_1.toBytes())) > 0)) {
                logger.warn("update c_rou_y_rou failed!");
            }
        } else {
            byte[] ck_rou_y_rou = Base64.getDecoder().decode(provStoreMapper.selCk_rou_y_rouByStage(idP, currentStage - 1));
            byte[] ck_rou_y_rou_plus_1 = CryptoUtil.AESEncrypt(CryptoUtil.getHash("SHA-256", k_rou_y_rou_plus_1), ck_rou_y_rou);
            if (!(consultMapper.insC_rou_y_rou(idP, currentStage, Base64.getEncoder().encodeToString(C_rou_y_rou_plus_1),
                    Base64.getEncoder().encodeToString(ck_rou_y_rou_plus_1)) > 0)) {
                logger.warn("update c_rou_y_rou failed!");
            }
        }
    }

    @Override
    public void sendTKPIDDToDoctor(Element tk, byte[] pidD) {
        this.tk = tk;
        DServiceImpl.pidD = pidD;
    }

    @Override
    public void appoint_D(byte[] enc_perD_sigma_perD, int length, int length1) {
        byte[] perD_sigma_perD = CryptoUtil.AESDecrypt(
                CryptoUtil.getHash("SHA-256", tk.toBytes()), enc_perD_sigma_perD);

        byte[][] splitByte = ArraysUtil.splitByte(perD_sigma_perD, length, length1);
        perD = splitByte[0];
        sigma_perD = splitByte[1];

        Element left = SysParamServiceImpl.pairing.pairing(
                SysParamServiceImpl.pairing.getG1().newElementFromBytes(sigma_perD), SysParamServiceImpl.P);

        Element hash_perD = SysParamServiceImpl.pairing.getG1().newElementFromHash(perD, 0, perD.length);

        Element right = SysParamServiceImpl.pairing.pairing(hash_perD, SysParamServiceImpl.pkP);

        if (!left.isEqual(right)) {
            logger.warn("Doctor's verification failed!");
        }
    }

    @Override
    public byte[] outsource(String idP) {
        //check if the first time come to this department
        //generate PB_l
        PB_l = new Provenance();
        byte[] ehrHash = CryptoUtil.getHash("SHA-256", ehr.content.getBytes());
        PB_l.setEhrHash(new String(ehrHash));
        PB_l.setPidD(new String(pidD));
        PB_l.setDepName(ehr.depName);
        PB_l.setDepID(ehr.depID);
        PB_l.setIdP(idP);
        PB_l.setHName(ehr.HName);
        PB_l.setHID(ehr.HID);
        PB_l.setStartCreateTime(System.currentTimeMillis());
        PB_l.setEndCreateTime(System.currentTimeMillis());
        Element sigma_PB_l = hServiceImpl.genSig(BytesUtil.toByteArray(PB_l));
        DServiceImpl.sigma_PB_l = sigma_PB_l;

        if (consultMapper.selMaxStage(idP) == 1) {
            PB_l.setViewHash(new ArrayList<>());
//            PB_l.setBlock(null);
            PB_l.setStartViewTime(0L);
            PB_l.setEndViewTime(0L);
            if (!sendPBToH(sigma_PB_l, BytesUtil.toByteArray(PB_l))) {
                logger.warn("signature sigma_PB_l verification failed!");
            }
        } else {
            int lastStage = consultMapper.selMaxStage(idP);
            //TODO add view ehr
            PB_l.setViewHash(new ArrayList<>());
            PB_l.getViewHash().add(
                    new String(CryptoUtil.getHash("SHA-256", ehr.content.getBytes())));
//            PB_l.setBlock(provStoreMapper.selBl_l(idP, lastStage));
            PB_l.setStartViewTime(System.currentTimeMillis());
            PB_l.setEndViewTime(System.currentTimeMillis());

            String PB_l_minus_1 = provStoreMapper.selPB_l(idP, lastStage - 1);
            Element sigma_PB_l_minus_1 = hServiceImpl.genSig(PB_l_minus_1.getBytes());
            if (!(sendPBToH(sigma_PB_l_minus_1, PB_l_minus_1.getBytes()))
                    && sendPBToH(sigma_PB_l, BytesUtil.toByteArray(PB_l))) {
                logger.warn("signature sigma_PB_l verification failed!");
            }
        }
        //send TX
        byte[] provBytes_hash = CryptoUtil.getHash("SHA-256", BytesUtil.toByteArray(PB_l));
        Element PB_l_hash = pairing.getZr().newElementFromHash(provBytes_hash, 0, provBytes_hash.length);
        byte[] PB_l_hash_sigma_PB_l_hash = CryptoUtil.getHash(
                "SHA-256", ArraysUtil.mergeByte(PB_l_hash.toBytes(), sigma_PB_l.toBytes()));
        return pairing.getZr().newElementFromHash(
                PB_l_hash_sigma_PB_l_hash, 0, PB_l_hash_sigma_PB_l_hash.length).toBytes();
    }

    @Override
    public boolean sendPBToH(Element sigma_PB_l, byte[] PB_l) {
        //verify
        Element left = pairing.pairing(sigma_PB_l, P).getImmutable();
        Element right = pairing.pairing(pairing.getG1().newElementFromHash(PB_l, 0, PB_l.length), pkH);

        return left.isEqual(right);
    }

    @Override
    public void sendBlockHash(String idP, String blockHash) {
        //send blockHash to CS
        csServiceImpl.receiveProv(idP, PB_l, blockHash, sigma_PB_l, C_rou_y_rou_plus_1, perD, sigma_perD);
    }

    @Override
    public EHR get_k_rou_y_rou(String idP, int stage, byte[] ck_rou_y_rou) {
        //解密已存的最新的密钥明文
        byte[] k_rou_y_rou = CryptoUtil.AESDecrypt(CryptoUtil.getHash(
                "SHA-256", k_rou_y_rou_plus_1), ck_rou_y_rou);
        if (csServiceImpl.authenticate(perD, sigma_perD)) {
            if (stage == 1) {
                return (EHR) BytesUtil.toObject(CryptoUtil.AESDecrypt(CryptoUtil.getHash("SHA-256", k_rou_y_rou),
                        Base64.getDecoder().decode(consultMapper.selC_rou_y_rou(idP, stage))));
            } else {
                return (EHR) BytesUtil.toObject(CryptoUtil.AESDecrypt(CryptoUtil.getHash("SHA-256", k_rou_y_rou),
                        Base64.getDecoder().decode(consultMapper.selC_rou_y_rou(idP, stage))));
            }
//            List<String> ck_rou_y_rouList = consultMapper.selCk_rou_y_rou(idP);
//            List<String> C_rou_y_rouList = consultMapper.selC_rou_y_rou(idP);
//            byte[] key = k_rou_y_rou;
//            for (int i = ck_rou_y_rouList.size() - 1; i > 1; i--) {
//                EHR ehr = (EHR) BytesUtil.toObject(
//                        CryptoUtil.AESDecrypt(key, Base64.getDecoder().decode(C_rou_y_rouList.get(i))));
//                key = CryptoUtil.AESDecrypt(CryptoUtil.getHash("SHA-256", key),
//                        Base64.getDecoder().decode(ck_rou_y_rouList.get(i)));
//                ehrList.add(ehr);
//            }
        } else {
            logger.warn("CS authentication failed!");
            return null;
        }
    }
}
