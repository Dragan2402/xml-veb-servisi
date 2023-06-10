package com.euprava.z1.repository;

import com.euprava.z1.model.Z1;
import com.euprava.z1.repository.exist.ExistManager;
import lombok.RequiredArgsConstructor;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.TransformerException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

@Repository
@RequiredArgsConstructor
public class Z1Repository {
    private static final String COLLECTION_ID = "db/z1";
    private static final String CONTEXT_PATH = "com.euprava.z1.model";

    private final ExistManager existManager;

    public void save(String documentId, Z1 z1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotFoundException, TransformerException, IOException {
        JAXBContext context = JAXBContext.newInstance(CONTEXT_PATH);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(z1, os);
        existManager.store(COLLECTION_ID, documentId, os.toString());
    }
    public Z1 findById(String documentId) throws XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotFoundException, JAXBException, SAXException {
        XMLResource resource = existManager.load(COLLECTION_ID, documentId);
        if (resource == null) {
            throw new NotFoundException("Document with id [" + documentId + "] not found.");
        }

        JAXBContext context = JAXBContext.newInstance(CONTEXT_PATH);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = new File("src/main/resources/data/schemas/z1_schema.xsd");
        Schema schema = schemaFactory.newSchema(schemaFile);
        unmarshaller.setSchema(schema);
        return (Z1) unmarshaller.unmarshal(new StringReader(resource.getContent().toString()));
    }

    public Node findByIdAsNode(String documentId) throws XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotFoundException {
        XMLResource resource = existManager.load(COLLECTION_ID, documentId);
        if (resource == null) {
            throw new NotFoundException("Document with id [" + documentId + "] not found.");
        }

        return resource.getContentAsDOM();
    }
}
