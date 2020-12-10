package service;


import it.unisa.dia.gas.jpbc.Element;

public interface DService {
    void sendTKPIDDToDoctor(Element tk, byte[] pid_d);

    void appoint_D(byte[] enc_perD_sigma_perD, int length, int length1);

    Element consult_D(Element aP);

    boolean authenticate(byte[] perD, byte[] sig_perD);

    byte[] createEHR(String idP);
}
