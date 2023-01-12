package com.euprava.euprava.service;

import com.euprava.euprava.model.a1sertifikat.ObrazacA1;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IA1Service {

    ObrazacA1 getObrazacById(String id) throws Exception;

    ObrazacA1 createRequest(Map<String, Object> obrazacA1);

    ObrazacA1 saveA1Request(ObrazacA1 request);

    ObrazacA1 submitRequest(ObrazacA1 obrazacA1);

    String uploadDescriptionFile(MultipartFile file);

    String uploadExampleFile(MultipartFile file);

    String generatePDF(String id) throws  Exception;

    String generateXHTML(String id) throws Exception;
}
