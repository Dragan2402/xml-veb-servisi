package com.euprava.euprava.repository;


import com.euprava.euprava.existdb.ExistDBManager;
import com.euprava.euprava.model.a1sertifikat.ObrazacA1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Repository
public class A1RequestRepository {
    @Autowired
    private ExistDBManager existDBManager;

    public void save(String collectionId, String documentId, ObrazacA1 request) throws Exception{
        JAXBContext context = JAXBContext.newInstance("com.euprava.euprava.model.a1sertifikat");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();

        marshaller.marshal(request, os);
        existDBManager.store(collectionId, documentId, os.toString());
    }
}
