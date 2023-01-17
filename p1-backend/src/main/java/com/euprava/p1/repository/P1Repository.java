package com.euprava.p1.repository;

import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.repository.database.xml.ExistManager;
import jakarta.xml.bind.*;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

@Repository
public class P1Repository {
    @Autowired
    private ExistManager existManager;

    private static final String COLLECTION_ID = "db/p1";
    private static final String CONTEXT_PATH = "com.euprava.p1.model";

    public ObrazacP1 findById(String documentId) throws XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotFoundException, JAXBException, SAXException {
        XMLResource resource = existManager.load(COLLECTION_ID, documentId);
        if(resource == null){
            return null;
        }

        JAXBContext context = JAXBContext.newInstance(CONTEXT_PATH);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        String resourceString = resource.getContent().toString();
        return (ObrazacP1) unmarshaller.unmarshal(new StringReader(resourceString));
    }

    public void saveObrazacP1(String documentId, ObrazacP1 obrazacP1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        JAXBContext context = JAXBContext.newInstance(CONTEXT_PATH);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();

        marshaller.marshal(obrazacP1, os);
        existManager.storeFromText(COLLECTION_ID, documentId, os.toString());
    }
}
