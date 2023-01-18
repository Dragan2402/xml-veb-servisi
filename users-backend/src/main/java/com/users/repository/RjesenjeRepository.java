package com.users.repository;

import com.users.existdb.ExistDBManager;
import com.users.model.rjesenje.Rjesenje;
import com.users.util.SchemaValidationHandler;
import com.users.util.exception.customExceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.modules.XMLResource;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.StringReader;

@Repository
public class RjesenjeRepository {

    @Autowired
    private ExistDBManager existDBManager;

    public void save(String collectionId, String documentId, Rjesenje request) throws Exception {
        JAXBContext context = JAXBContext.newInstance("com.users.model.rjesenje");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();

        marshaller.marshal(request, os);
        existDBManager.store(collectionId, documentId, os.toString());
    }

    public Rjesenje findById(String collectionId, String documentId) throws Exception {
        XMLResource resource = existDBManager.load(collectionId, documentId);
        if(resource == null){
            throw new ObjectNotFoundException("Document with provided ID does not exist");
        }
        JAXBContext context = JAXBContext.newInstance("com.users.model.rjesenje");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = new File("src/main/resources/data/schemas/rjesenje.xsd");
        Schema schema = schemaFactory.newSchema(schemaFile);

        unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new SchemaValidationHandler());
        return (Rjesenje) unmarshaller.unmarshal(new StringReader(resource.getContent().toString()));
    }
}
