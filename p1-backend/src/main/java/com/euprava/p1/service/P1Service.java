package com.euprava.p1.service;

import com.euprava.p1.model.ObrazacP1;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface P1Service {
    ObrazacP1 readObrazacP1(String fileName) throws JAXBException, IOException;
    void createObrazacP1(Map<?, ?> obrazacP1Map) throws DatatypeConfigurationException, ParseException, JAXBException, IOException;
}
