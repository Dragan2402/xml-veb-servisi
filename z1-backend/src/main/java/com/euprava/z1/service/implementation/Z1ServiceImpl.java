package com.euprava.z1.service.implementation;

import com.euprava.z1.model.Z1;
import com.euprava.z1.repository.Z1Repository;
import com.euprava.z1.service.Z1Service;
import lombok.RequiredArgsConstructor;
import org.exist.http.NotFoundException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadLocalRandom;


@Service
@RequiredArgsConstructor
public class Z1ServiceImpl implements Z1Service {

    private final Z1Repository z1Repository;
    private static final String DOCUMENT_DIRECTORY_PATH = "data/documents/";


    @Override
    public String createZ1(Z1 z1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, DatatypeConfigurationException, NotFoundException, IOException, TransformerException {
        int randomNum = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        String documentId = String.valueOf(randomNum);
        z1Repository.save(documentId, z1);
        return documentId;
    }

    public Z1 getZ1(String fileName) throws JAXBException, IOException {
        Resource resource = new ClassPathResource(DOCUMENT_DIRECTORY_PATH + fileName);
        File file = resource.getFile();
        JAXBContext context = JAXBContext.newInstance(Z1.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Z1 zahtevZaPriznanjeZiga = (Z1) unmarshaller.unmarshal(file);
        return zahtevZaPriznanjeZiga;
    }

}
