package service.Impl;

import it.unisa.dia.gas.jpbc.Element;
import mapper.AppointMapper;
import org.springframework.stereotype.Service;
import pojo.VO.Doctor;
import service.DService;
import service.HService;
import service.PService;
import util.ArraysUtil;
import util.CryptoUtil;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Service
public class HServiceImpl implements HService {
    @Resource
    private PService pServiceImpl;
    @Resource
    private DService dServiceImpl;
    @Resource
    private AppointMapper appointMapper;

    @Override
    public boolean authenticate(String idP, Element auH) {
        try {
            if (appointMapper.selIdP(idP, new String(auH.toBytes(), "ISO8859-1")) > 0) {
                return true;
            } else {
                System.out.println("failed to authenticated with H!");
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int appoint_H(Element[] encTK, String ID_P) {
        Element tk = CryptoUtil.ElGamalDecrypt(SysParamServiceImpl.skH, encTK).getImmutable();//解密获取tk
        Doctor doctor = new Doctor("doctor");//委派医生
        long tpD = System.currentTimeMillis();//当前时间
        byte[] idD_tpD = ArraysUtil.mergeByte(doctor.getID().getBytes(), Long.toString(tpD).getBytes());
        byte[] pidD = CryptoUtil.AESEncrypt(CryptoUtil.getHash("SHA-256", tk.duplicate()), idD_tpD);//医生的伪身份

        //send tk pidD to D
        dServiceImpl.sendTKPIDDToDoctor(tk.duplicate(), pidD);

        //send appointment information to P
        PServiceImpl.pidD = pidD;
        PServiceImpl.aux = "dep";
        return pServiceImpl.sendAppointInfoToPatient(doctor.getID().getBytes().length, Long.toString(tpD).getBytes().length);
    }

    @Override
    public Element genSig(byte[] PB_l) {
        return SysParamServiceImpl.pairing.getG1().newElementFromHash(PB_l, 0, PB_l.length).
                mulZn(SysParamServiceImpl.skH);
    }
}
