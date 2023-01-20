package com.euprava.p1.service;

import com.euprava.p1.model.ObrazacP1;
import org.exist.http.NotFoundException;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

public interface P1Service {
    void createObrazacP1(ObrazacP1 obrazacP1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    ObrazacP1 retrieveObrazacP1(String documentId) throws JAXBException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SAXException;
}
