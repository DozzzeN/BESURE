package service;


import it.unisa.dia.gas.jpbc.Element;
import pojo.DO.EHR;

import java.util.List;

public interface DService {
    void sendTKPIDDToDoctor(Element tk, byte[] pid_d);

    void appoint_D(byte[] enc_perD_sigma_perD, int length, int length1);

    Element consult_D(Element aP);

    boolean authenticate(byte[] perD, byte[] sig_perD);

    void createEHR(String idP, EHR ehr);

    String outsource(String idP);

    boolean sendPBToH(byte[] PB_l);

    void sendBlockHash(String idP, String blockHash);

    List<EHR> get_k_rou_y_rou(String idP, byte[] ck_rou_y_rou);
}
