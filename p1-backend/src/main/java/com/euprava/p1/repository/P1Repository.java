package com.euprava.p1.repository;

import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.repository.exist.ExistManager;
import com.euprava.p1.repository.fuseki.FusekiWriter;
import com.euprava.p1.repository.fuseki.MetadataExtractor;
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
public class P1Repository {
    private static final String COLLECTION_ID = "db/p1";
    private static final String CONTEXT_PATH = "com.euprava.p1.model";

    private final ExistManager existManager;
    private final MetadataExtractor metadataExtractor;

    public void save(String documentId, ObrazacP1 obrazacP1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotFoundException, TransformerException, IOException {
        JAXBContext context = JAXBContext.newInstance(CONTEXT_PATH);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();

        marshaller.marshal(obrazacP1, os);
        existManager.store(COLLECTION_ID, documentId, os.toString());
        saveMetadata(documentId);
    }

    private void saveMetadata(String documentId) throws NotFoundException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, TransformerException, IOException {
        XMLResource resource = existManager.load(COLLECTION_ID, documentId);
        if (resource == null) {
            throw new NotFoundException("Document with id [" + documentId + "] not found.");
        }

        byte[] out = metadataExtractor.extractMetadata(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out));
    }

    public ObrazacP1 findById(String documentId) throws XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotFoundException, JAXBException, SAXException {
        XMLResource resource = existManager.load(COLLECTION_ID, documentId);
        if (resource == null) {
            throw new NotFoundException("Document with id [" + documentId + "] not found.");
        }

        JAXBContext context = JAXBContext.newInstance(CONTEXT_PATH);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = new File("src/main/resources/data/schemas/p1_schema.xsd");
        Schema schema = schemaFactory.newSchema(schemaFile);

        unmarshaller.setSchema(schema);
        return (ObrazacP1) unmarshaller.unmarshal(new StringReader(resource.getContent().toString()));
    }

    public Node findByIdAsNode(String documentId) throws XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotFoundException {
        XMLResource resource = existManager.load(COLLECTION_ID, documentId);
        if (resource == null) {
            throw new NotFoundException("Document with id [" + documentId + "] not found.");
        }

        return resource.getContentAsDOM();
    }

    public String getLastId() throws XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return existManager.getLastId(COLLECTION_ID);
    }
}
