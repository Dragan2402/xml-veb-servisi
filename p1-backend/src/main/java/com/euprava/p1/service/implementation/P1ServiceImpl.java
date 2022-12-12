package com.euprava.p1.service.implementation;

import com.euprava.p1.mapper.ObrazacP1Mapper;
import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.service.P1Service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Service
public class P1ServiceImpl implements P1Service {
    private static final String DOCUMENT_DIRECTORY_PATH = "data/documents/";

    @Override
    public ObrazacP1 readObrazacP1(String fileName) throws JAXBException, IOException {
        Resource resource = new ClassPathResource(DOCUMENT_DIRECTORY_PATH + fileName);
        File file = resource.getFile();
        JAXBContext context = JAXBContext.newInstance(ObrazacP1.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (ObrazacP1) unmarshaller.unmarshal(file);
    }

    @Override
    public void createObrazacP1(Map<?, ?> obrazacP1Map) throws DatatypeConfigurationException, ParseException, JAXBException, IOException {
        ObrazacP1 obrazacP1 = ObrazacP1Mapper.map(obrazacP1Map);
        String[] fileNameTokens = obrazacP1.getPopunjavaZavod().getBrojPrijave().split("/");
        String fileName = fileNameTokens[0] + "_" + fileNameTokens[1] + ".xml";

        JAXBContext context = JAXBContext.newInstance(ObrazacP1.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("C:/Users/Dimitrije/Desktop/" + fileName);

        FileOutputStream stream = new FileOutputStream(file);

        marshaller.marshal(obrazacP1, stream);
        stream.close();
    }
}
