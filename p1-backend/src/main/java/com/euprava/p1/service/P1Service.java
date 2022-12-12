package com.euprava.p1.service;

import com.euprava.p1.model.ObrazacP1;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface P1Service {
    ObrazacP1 getObrazacP1(String fileName) throws JAXBException, IOException;
}
