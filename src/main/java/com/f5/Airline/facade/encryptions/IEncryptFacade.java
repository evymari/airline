package com.f5.Airline.facade.encryptions;

public interface IEncryptFacade {

    String encode(String type, String data);
    String decode(String type, String data);

}
