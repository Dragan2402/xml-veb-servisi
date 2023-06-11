package com.euprava.z1.repository;

import com.euprava.z1.controller.response.Z1Response;
import com.euprava.z1.model.Z1;
import com.euprava.z1.repository.exist.ExistManager;
import lombok.RequiredArgsConstructor;
import org.exist.http.NotFoundException;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
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
import java.util.ArrayList;
import java.util.List;

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

    public List<Resource> getObrazacByQuery(String collection, String namespace, String query) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return existManager.executeQuery(collection, namespace, query);
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

    public List<Z1Response> searchByText(String xPathExp) throws XMLDBException, JAXBException {
        ResourceSet result = existManager.retrieve(COLLECTION_ID, xPathExp);
        List<Z1Response> list = new ArrayList<>();
        ResourceIterator iter = result.getIterator();
        while (iter.hasMoreResources()) {
            XMLResource resource = (XMLResource) iter.nextResource();
            long id = Long.parseLong(resource.getId().split("_")[0]);
            JAXBContext context = JAXBContext.newInstance(Z1.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Z1 z1 = (Z1) unmarshaller.unmarshal(resource.getContentAsDOM());
            list.add(new Z1Response(z1, id));
        }
        return list;
    }
}
