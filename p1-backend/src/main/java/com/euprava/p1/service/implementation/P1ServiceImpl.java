package com.euprava.p1.service.implementation;

import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.repository.P1Repository;
import com.euprava.p1.service.P1Service;
import jakarta.xml.bind.JAXBException;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Map;

@Service
public class P1ServiceImpl implements P1Service {
    private static final String DOCUMENT_DIRECTORY_PATH = "data/documents/";

    @Autowired
    P1Repository p1Repository;

    @Override
    public ObrazacP1 retrieveObrazacP1(String documentId) throws XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, JAXBException, NotFoundException, SAXException {
        return p1Repository.findById(documentId);
    }

    @Override
    public void createObrazacP1(ObrazacP1 obrazacP1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String documentId = obrazacP1.getPopunjavaZavod().getBrojPrijave().replace('/', '-');
        p1Repository.saveObrazacP1(documentId, obrazacP1);
    }
}
