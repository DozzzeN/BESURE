package service;


import it.unisa.dia.gas.jpbc.Element;

public interface KSService {
    /**
     * 返回sigma_star
     */
    Element[] genSpw(Element pwP_star);

    void register_KHC(Element[] au);

    Element[][] consult1(String idP, Element pwP_star);

    boolean consult2(byte[][] encrypted_lambda_star);

}
