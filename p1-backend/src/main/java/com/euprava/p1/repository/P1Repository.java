package com.euprava.p1.repository;

import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.repository.database.xml.ExistManager;
import com.euprava.p1.repository.database.xml.SchemaValidationHandler;
import jakarta.xml.bind.*;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
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

//    public ObrazacA1 findById(String collectionId, String documentId) throws Exception {
//        XMLResource resource = existDBManager.load(collectionId, documentId);
//        if(resource == null){
//            throw new ObjectNotFoundException("Document with provided ID does not exist");
//        }
//        JAXBContext context = JAXBContext.newInstance("com.euprava.euprava.model.a1sertifikat");
//        Unmarshaller unmarshaller = context.createUnmarshaller();
//        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//        File schemaFile = new File("src/main/resources/data/schemas/a1_shema.xsd");
//        Schema schema = schemaFactory.newSchema(schemaFile);
//
//        unmarshaller.setSchema(schema);
//        unmarshaller.setEventHandler(new SchemaValidationHandler());
//        return (ObrazacA1) unmarshaller.unmarshal(new StringReader(resource.getContent().toString()));
//    }
//
//    public String getObrazacAsStringById(String id) throws Exception {
//        JAXBContext context = JAXBContext.newInstance("com.euprava.euprava.model.a1sertifikat");
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//        OutputStream os = new ByteArrayOutputStream();
//        marshaller.marshal(this.findById("/db/a1","id_"+id), os);
//        return os.toString();
//    }
//
//    public XMLResource loadXmlResource(String collectionId, String documentId) throws Exception {
//        XMLResource resource = existDBManager.load(collectionId, documentId);
//        if(resource == null){
//            throw new ObjectNotFoundException("Document with provided ID does not exist");
//        }
//        return resource;
//    }
//
//    public void saveTempXml(String id, String temp_file_path) throws Exception {
//        JAXBContext context = JAXBContext.newInstance("com.euprava.euprava.model.a1sertifikat");
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//        File file = new File("src/main/resources/data/gen/temp.xml");
//        FileOutputStream stream = new FileOutputStream(file);
//        marshaller.marshal(this.findById("/db/a1","id_"+id), stream);
//        stream.close();
//    }
//
//    public List<Resource> getObrazacByQuery(String collection, String namespace, String query) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        return existDBManager.executeQuery(collection, namespace, query);
//    }
//
//    public void approveRequest(String collection, String documentId) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        existDBManager.updateRequestToApproved(collection,documentId);
//    }
//
//    public void declineRequest(String collectionUri, String documentId) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        existDBManager.updateRequestToDeclined(collectionUri, documentId);
//    }
}
