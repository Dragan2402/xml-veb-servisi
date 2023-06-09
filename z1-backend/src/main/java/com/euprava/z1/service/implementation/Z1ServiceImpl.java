package com.euprava.z1.service.implementation;

import com.euprava.z1.model.ZahtevZaPriznanjeZiga;
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

    public ZahtevZaPriznanjeZiga getZahtevZaPriznanjeZiga(String fileName) throws JAXBException, IOException {
        Resource resource = new ClassPathResource(DOCUMENT_DIRECTORY_PATH + fileName);
        File file = resource.getFile();
        JAXBContext context = JAXBContext.newInstance(ZahtevZaPriznanjeZiga.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(file);
    }

}
