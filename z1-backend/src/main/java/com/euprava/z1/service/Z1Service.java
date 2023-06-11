package com.euprava.z1.service;

import com.euprava.z1.controller.response.Z1Response;
import com.euprava.z1.model.Z1;
import org.exist.http.NotFoundException;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Z1Service {
    String createZ1(Z1 z1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, DatatypeConfigurationException, NotFoundException, IOException, TransformerException;
    List<Z1Response> getAllZ1() throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException, InvocationTargetException, NoSuchMethodException;
}