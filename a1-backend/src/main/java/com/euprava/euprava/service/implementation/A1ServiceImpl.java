package com.euprava.euprava.service.implementation;

import com.euprava.euprava.model.a1Sertifikat.ObrazacA1;
import com.euprava.euprava.service.IA1Service;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Service
public class A1ServiceImpl implements IA1Service {

    @Override
    public ObrazacA1 getExample() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.euprava.euprava.model.a1Sertifikat");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ObrazacA1 a1 = (ObrazacA1) unmarshaller.unmarshal(new File("src/main/resources/data/schemas/ExampleA1-1.xml"));
        return a1;
    }
}
