package com.euprava.z1.service;

import com.euprava.z1.controller.response.NumberResponse;
import com.euprava.z1.controller.response.Z1Response;
import com.euprava.z1.controller.response.Z1ResponseList;
import com.euprava.z1.model.Z1;
import com.itextpdf.text.DocumentException;
import org.exist.http.NotFoundException;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Z1Service {
    File retrieveZ1AsPDF(String documentId) throws Exception;
    Z1ResponseList retrieveZ1ResponseListByText(String text) throws XMLDBException, JAXBException;
    File retrieveZ1AsHTML(String documentId) throws XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, FileNotFoundException;
    String createZ1(Z1 z1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, DatatypeConfigurationException, NotFoundException, IOException, TransformerException;
    List<Z1Response> getAllZ1() throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException, InvocationTargetException, NoSuchMethodException;
    List<Z1Response> searchMetadata(String search) throws Exception;
    List<Z1Response> searchByReference(String param) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException, InvocationTargetException, NoSuchMethodException;
    Z1 getZ1ById(String id) throws Exception;
    String retrieveObrazacZ1MetadataAsRDF(String documentId) throws IOException;
    String retrieveObrazacZ1MetadataAsJSON(String documentId) throws IOException;
    void setZ1StatusAsOdobren(String documentId) throws XMLDBException;
    void setZ1StatusAsOdbijen(String documentId) throws XMLDBException;
    NumberResponse getNumberOfRequests(String start, String end) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException, InvocationTargetException, NoSuchMethodException;

}