package com.euprava.euprava.service;

import com.euprava.euprava.model.a1sertifikat.ObrazacA1;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.util.Map;

public interface IA1Service {

    ObrazacA1 getExample() throws JAXBException;

    ObrazacA1 createRequest(Map<String, Object> obrazacA1);

    boolean saveA1Request(ObrazacA1 request);

    boolean submitRequest(Map<String, Object> obrazacA1);

    String uploadDescriptionFile(MultipartFile file);

    String uploadExampleFile(MultipartFile file);
}
