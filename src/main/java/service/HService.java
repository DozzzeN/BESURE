package service;

import it.unisa.dia.gas.jpbc.Element;

public interface HService {
    boolean authenticate(String id_p, Element au_h);

    int appoint_H(Element[] encTK, String ID_P);
}
