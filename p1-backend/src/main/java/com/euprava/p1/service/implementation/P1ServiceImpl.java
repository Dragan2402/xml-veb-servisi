package com.euprava.p1.service.implementation;

import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.service.P1Service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

@Service
public class P1ServiceImpl implements P1Service {
//    private static final String DOCUMENT_DIRECTORY_PATH = "D:/Dimitrije/Documents/GitHub/xml-veb-servisi/p1-backend/src/main/resources/data/documents/";
    private static final String DOCUMENT_DIRECTORY_PATH = "data/documents/";

    @Override
    public ObrazacP1 getObrazacP1(String fileName) throws JAXBException, IOException {
        Resource resource = new ClassPathResource(DOCUMENT_DIRECTORY_PATH + fileName);
        File file = resource.getFile();
        JAXBContext context = JAXBContext.newInstance(ObrazacP1.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ObrazacP1 p1 = (ObrazacP1) unmarshaller.unmarshal(file);
        return p1;
    }
}
