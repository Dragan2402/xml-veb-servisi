package com.euprava.euprava.service;

import com.euprava.euprava.model.a1sertifikat.ObrazacA1;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IA1Service {

    ObrazacA1 getObrazacById(String id) throws Exception;

    ObrazacA1 saveA1Request(ObrazacA1 request);

    ObrazacA1 submitRequest(ObrazacA1 obrazacA1);

    String uploadDescriptionFile(MultipartFile file);

    String uploadExampleFile(MultipartFile file);

    String generatePDF(String id) throws  Exception;

    String generateXHTML(String id) throws Exception;

    List<ObrazacA1> searchByParam(String param) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;

    List<ObrazacA1> searchMetadataByParam(String param) throws Exception;

    List<ObrazacA1> searchMetadataByLogicalParams(String search) throws Exception;
}
