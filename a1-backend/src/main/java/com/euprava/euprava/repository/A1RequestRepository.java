package com.euprava.euprava.repository;


import com.euprava.euprava.existdb.ExistDBManager;
import com.euprava.euprava.model.a1sertifikat.ObrazacA1;
import com.euprava.euprava.util.SchemaValidationHandler;
import com.euprava.euprava.util.exception.customExceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.base.Resource;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.util.List;

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

    public ObrazacA1 findById(String collectionId, String documentId) throws Exception {
        XMLResource resource = existDBManager.load(collectionId, documentId);
        if(resource == null){
            throw new ObjectNotFoundException("Document with provided ID does not exist");
        }
        JAXBContext context = JAXBContext.newInstance("com.euprava.euprava.model.a1sertifikat");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = new File("src/main/resources/data/schemas/a1_shema.xsd");
        Schema schema = schemaFactory.newSchema(schemaFile);

        unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new SchemaValidationHandler());
        return (ObrazacA1) unmarshaller.unmarshal(new StringReader(resource.getContent().toString()));
    }

    public String getObrazacAsStringById(String id) throws Exception {
        JAXBContext context = JAXBContext.newInstance("com.euprava.euprava.model.a1sertifikat");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(this.findById("/db/a1","id_"+id), os);
        return os.toString();
    }

    public XMLResource loadXmlResource(String collectionId, String documentId) throws Exception {
        XMLResource resource = existDBManager.load(collectionId, documentId);
        if(resource == null){
            throw new ObjectNotFoundException("Document with provided ID does not exist");
        }
        return resource;
    }

    public void saveTempXml(String id, String temp_file_path) throws Exception {
        JAXBContext context = JAXBContext.newInstance("com.euprava.euprava.model.a1sertifikat");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("src/main/resources/data/gen/temp.xml");
        FileOutputStream stream = new FileOutputStream(file);
        marshaller.marshal(this.findById("/db/a1","id_"+id), stream);
        stream.close();
    }

    public List<Resource> getObrazacByQuery(String collection, String namespace, String query) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return existDBManager.executeQuery(collection, namespace, query);
    }
}
