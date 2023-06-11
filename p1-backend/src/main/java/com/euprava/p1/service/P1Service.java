package com.euprava.p1.service;

import com.euprava.p1.controller.Responses.ObrazacP1SearchResponseList;
import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.model.ZahtevZaPriznanjePatenta;
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

public interface P1Service {
    String createObrazacP1(ZahtevZaPriznanjePatenta zahtev) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, DatatypeConfigurationException, NotFoundException, IOException, TransformerException;
    ObrazacP1 retrieveObrazacP1(String documentId) throws JAXBException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SAXException;
    File retrieveObrazacP1AsPDF(String documentId) throws IOException, DocumentException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    File retrieveObrazacP1AsHTML(String documentId) throws XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, FileNotFoundException;
    String retrieveObrazacP1MetadataAsRDF(String documentId) throws IOException;
    String retrieveObrazacP1MetadataAsJSON(String documentId) throws IOException;
    ObrazacP1SearchResponseList retrieveObrazacP1SearchResponseListByText(String text) throws XMLDBException, JAXBException;
}
