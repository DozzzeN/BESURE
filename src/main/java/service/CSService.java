package service;

public interface CSService {
    boolean authenticate(byte[] perD, byte[] sigma_perD);
}
