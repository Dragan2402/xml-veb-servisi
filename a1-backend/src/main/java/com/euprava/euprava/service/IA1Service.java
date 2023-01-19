package com.euprava.euprava.service;

import com.euprava.euprava.controller.Responses.A1Response;
import com.euprava.euprava.controller.Responses.NumberResponse;
import com.euprava.euprava.model.a1sertifikat.ObrazacA1;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IA1Service {

    ObrazacA1 getObrazacById(String id) throws Exception;

    ObrazacA1 saveA1Request(ObrazacA1 request);

    ObrazacA1 submitRequest(ObrazacA1 obrazacA1);

    String uploadDescriptionFile(MultipartFile file);

    String uploadExampleFile(MultipartFile file);

    List<ObrazacA1> searchByParam(String param) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;

    List<ObrazacA1> searchMetadataByParam(String param) throws Exception;

    List<A1Response> searchMetadataByLogicalParams(String search) throws Exception;

    String getMetadata(String id, String type) throws IOException;

    File getPDFFileById(String id) throws Exception;

    File getHTMLFileById(String id) throws Exception;

    ObrazacA1 approveRequest(String id, int code, long idRjesenja) throws Exception;

    ObrazacA1 declineRequest(String id, long idRjesenja) throws Exception;

    List<A1Response> getClientRequests(long clientId) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;

    List<A1Response> searchClientByParam(long clientId, String param) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;

    List<A1Response> searchEmployeeByParam(String param) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;

    List<A1Response> getRequests() throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;

    NumberResponse getNumberOfRequests(String start, String end) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;

    List<A1Response> searchEmployeeByReference(String param) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;
}
