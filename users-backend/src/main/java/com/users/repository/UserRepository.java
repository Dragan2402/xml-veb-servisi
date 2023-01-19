package com.users.repository;

import com.users.existdb.ExistDBManager;
import com.users.model.user.User;
import com.users.util.exception.customExceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Repository
public class UserRepository {

    @Autowired
    private ExistDBManager existDBManager;

    public void save(String collectionId, String documentId, User request) throws Exception{
        JAXBContext context = JAXBContext.newInstance("com.users.model.user");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();

        marshaller.marshal(request, os);
        existDBManager.store(collectionId, documentId, os.toString());
    }

    public XMLResource loadXmlResource(String collectionId, String documentId) throws Exception {
        XMLResource resource = existDBManager.load(collectionId, documentId);
        if(resource == null){
            throw new ObjectNotFoundException("User with provided ID does not exist");
        }
        return resource;
    }

    public User getUserByEmail(String email, boolean existing) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return existDBManager.getUserByEmail(email, existing);
    }
}
