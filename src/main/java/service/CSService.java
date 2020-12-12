package service;

import it.unisa.dia.gas.jpbc.Element;
import pojo.DO.Provenance;

public interface CSService {
    boolean authenticate(byte[] perD, byte[] sigma_perD);

    void receiveProv(String idP, Provenance PB_l, String blockHash, Element sigma_PB_l, byte[] C_rou_y_rou_plus_1,
                     byte[] perD, byte[] sigma_perD);

    byte[] updateKey();

    void store(String idP, byte[] ck_rou_y_rou);
}
