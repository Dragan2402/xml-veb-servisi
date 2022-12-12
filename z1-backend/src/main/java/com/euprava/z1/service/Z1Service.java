package com.euprava.z1.service;

import com.euprava.z1.model.ZahtevZaPriznanjeZiga;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface Z1Service {
    ZahtevZaPriznanjeZiga getZahtevZaPriznanjeZiga(String fileName) throws JAXBException, IOException;
}
