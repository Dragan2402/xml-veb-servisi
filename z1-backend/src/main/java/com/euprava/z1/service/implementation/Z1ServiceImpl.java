package com.euprava.z1.service.implementation;

import com.euprava.z1.model.Z1;
import com.euprava.z1.service.Z1Service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;


@Service
public class Z1ServiceImpl implements Z1Service {

    private static final String DOCUMENT_DIRECTORY_PATH = "data/documents/";

    public Z1 getZ1(String fileName) throws JAXBException, IOException {
        Resource resource = new ClassPathResource(DOCUMENT_DIRECTORY_PATH + fileName);
        File file = resource.getFile();
        JAXBContext context = JAXBContext.newInstance(Z1.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Z1 zahtevZaPriznanjeZiga = (Z1) unmarshaller.unmarshal(file);
        return zahtevZaPriznanjeZiga;
    }

}
