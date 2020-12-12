package service.Impl;

import it.unisa.dia.gas.jpbc.Element;
import mapper.ConsultMapper;
import mapper.ProvStoreMapper;
import org.springframework.stereotype.Service;
import pojo.DO.Provenance;
import service.CSService;
import util.BytesUtil;
import util.CryptoUtil;

import javax.annotation.Resource;
import java.util.Base64;

import static service.Impl.PServiceImpl.k_rou_y_rou_plus_1;
import static service.Impl.PServiceImpl.spwP;
import static service.Impl.SysParamServiceImpl.*;

@Service
public class CSServiceImpl implements CSService {
    public static byte[] enc_k_rou_y_rou_plus_1;
    @Resource
    private ProvStoreMapper provStoreMapper;
    @Resource
    private ConsultMapper consultMapper;

    @Override
    public boolean authenticate(byte[] perD, byte[] sigma_perD) {
        Element left = pairing.pairing(pairing.getG1().newElementFromBytes(sigma_perD), SysParamServiceImpl.P);
        Element hash_perD = pairing.getG1().newElementFromHash(perD, 0, perD.length);
        Element right = pairing.pairing(hash_perD, SysParamServiceImpl.pkP);
        return left.isEqual(right);
    }

    @Override
    public void receiveProv(String idP, Provenance PB_l, String blockHash, Element sigma_PB_l, byte[] C_rou_y_rou,
                            byte[] perD, byte[] sigma_perD) {
        //verify sigma_perD
        Element left = pairing.pairing(pairing.getG1().newElementFromBytes(sigma_perD), P);
        Element hash_perD = pairing.getG1().newElementFromHash(perD, 0, perD.length);
        Element right = pairing.pairing(hash_perD, pkP);

        if (!left.isEqual(right)) {
            System.out.println("Cloud server's verification failed!");
        } else {
            //store PB_l and Bl_l
            if (provStoreMapper.updPB_l(idP, Base64.getEncoder().encodeToString(C_rou_y_rou),
                    new String(BytesUtil.toByteArray(PB_l)), blockHash) > 0) {
                enc_k_rou_y_rou_plus_1 = updateKey();
                if (!(consultMapper.selMaxStage(idP) == 1)) {
                    //还要ck_rou_y_rou_plus_1
                    int lastStage = consultMapper.selMaxStage(idP) - 1;
                    byte[] ck_rou_y_rou = Base64.getDecoder().decode(
                            provStoreMapper.selCk_rou_y_rouByStage(idP, lastStage));
                    byte[] ck_rou_y_rou_plus_1 = CryptoUtil.AESEncrypt(CryptoUtil.getHash("SHA-256", PServiceImpl.k_rou_y_rou_plus_1),
                            ck_rou_y_rou);
                    store(idP, ck_rou_y_rou_plus_1);
                }
            } else {
                System.out.println("update PB_l and Bl_l failed!");
            }
        }
    }

    @Override
    public byte[] updateKey() {
        return CryptoUtil.AESEncrypt(CryptoUtil.getHash("SHA-256", spwP), k_rou_y_rou_plus_1.toBytes());
    }

    @Override
    public void store(String idP, byte[] ck_rou_y_rou) {
        if (provStoreMapper.updCk_rou_y_rou(idP, Base64.getEncoder().encodeToString(ck_rou_y_rou)) > 0) {
            System.out.println("update ck_rou_y_rou succeeded!");
        } else {
            System.out.println("update ck_rou_y_rou failed!");
        }
    }
}
