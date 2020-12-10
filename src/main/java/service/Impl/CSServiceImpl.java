package service.Impl;

import it.unisa.dia.gas.jpbc.Element;
import org.springframework.stereotype.Service;
import service.CSService;

@Service
public class CSServiceImpl implements CSService {
    @Override
    public boolean authenticate(byte[] perD, byte[] sigma_perD) {
        Element left = SysParamServiceImpl.pairing.pairing(
                SysParamServiceImpl.pairing.getG1().newElementFromBytes(sigma_perD), SysParamServiceImpl.P);

        Element hash_perD = SysParamServiceImpl.pairing.getG1().newElementFromHash(perD, 0, perD.length);

        Element right = SysParamServiceImpl.pairing.pairing(hash_perD, SysParamServiceImpl.pkP);

        return left.isEqual(right);
    }
}
