package service.Impl;

import it.unisa.dia.gas.jpbc.Element;
import org.springframework.stereotype.Service;
import pojo.DO.EHR;
import service.CSService;
import service.DService;
import util.ArraysUtil;
import util.BytesUtil;
import util.CryptoUtil;

import javax.annotation.Resource;

import static service.Impl.SysParamServiceImpl.P;
import static service.Impl.SysParamServiceImpl.pairing;

@Service
public class DServiceImpl implements DService {
    public static Element k_rou_y_rou_plus_1;
    @Resource
    private CSService csServiceImpl;
    private Element tk;
    private byte[] pidD;

    @Override
    public Element consult_D(Element aP) {
        Element b = pairing.getZr().newRandomElement().getImmutable();
        k_rou_y_rou_plus_1 = aP.duplicate().mulZn(b.duplicate()).getImmutable();
        //send bP to P
        return P.duplicate().mulZn(b.duplicate()).getImmutable();
    }

    @Override
    public boolean authenticate(byte[] perD, byte[] sigma_perD) {
        return csServiceImpl.authenticate(perD, sigma_perD);
    }

    @Override
    public byte[] createEHR(String idP) {
        EHR ehr = new EHR();
        ehr.setPidD(pidD);
        ehr.setEndCreateTime(System.currentTimeMillis());
        ehr.setStartCreateTime(System.currentTimeMillis());
        ehr.setIdP(idP);
        byte[] ehrBytes = BytesUtil.toByteArray(ehr);
        return CryptoUtil.AESEncrypt(
                CryptoUtil.getHash("SHA-256", k_rou_y_rou_plus_1.duplicate()), ehrBytes);
    }

    @Override
    public void sendTKPIDDToDoctor(Element tk, byte[] pidD) {
        this.tk = tk;
        this.pidD = pidD;
    }

    @Override
    public void appoint_D(byte[] enc_perD_sigma_perD, int length, int length1) {
        byte[] perD_sigma_perD = CryptoUtil.AESDecrypt(CryptoUtil.getHash("SHA-256", tk.toBytes()), enc_perD_sigma_perD);

        byte[][] splitByte = ArraysUtil.splitByte(perD_sigma_perD, length, length1);
        byte[] perD = splitByte[0];
        byte[] sigma_perD = splitByte[1];

        Element left = SysParamServiceImpl.pairing.pairing(
                SysParamServiceImpl.pairing.getG1().newElementFromBytes(sigma_perD), SysParamServiceImpl.P);

        Element hash_perD = SysParamServiceImpl.pairing.getG1().newElementFromHash(perD, 0, perD.length);

        Element right = SysParamServiceImpl.pairing.pairing(hash_perD, SysParamServiceImpl.pkP);

        if (!left.isEqual(right)) {
            System.out.println("Doctor's verification failed!");
        }
    }
}
