package com.euprava.p1.service;

import com.euprava.p1.model.ObrazacP1;
import jakarta.xml.bind.JAXBException;
import org.exist.http.NotFoundException;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface P1Service {
    ObrazacP1 retrieveObrazacP1(String fileName) throws IOException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, JAXBException, NotFoundException, SAXException;
    void createObrazacP1(ObrazacP1 obrazacP1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
}
