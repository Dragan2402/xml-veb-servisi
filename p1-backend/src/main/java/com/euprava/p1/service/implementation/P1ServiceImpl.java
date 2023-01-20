package com.euprava.p1.service.implementation;

import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.repository.P1Repository;
import com.euprava.p1.service.P1Service;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

@Service
public class P1ServiceImpl implements P1Service {
    @Autowired
    private P1Repository p1Repository;

    @Override
    public void createObrazacP1(ObrazacP1 obrazacP1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String documentId = generateDocumentId();
        obrazacP1.getPopunjavaZavod().setBrojPrijave(documentId.replace('-', '/'));
        p1Repository.save(documentId, obrazacP1);
    }

    private String generateDocumentId() throws XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String currentYear = Integer.toString(LocalDate.now().getYear());
        String currentYearCode = currentYear.substring(currentYear.length() - 2);

        String documentId;
        String lastId = p1Repository.getLastId();
        if (lastId == null || !lastId.substring(lastId.length() - 2).equals(currentYearCode)) {
            return "1-" + currentYearCode;
        } else {
            String[] tokens = lastId.split("-");
            int idNumber = Integer.parseInt(tokens[0]);
            idNumber++;
            documentId = idNumber + "-" + currentYearCode;
        }

        return documentId;
    }

    @Override
    public ObrazacP1 retrieveObrazacP1(String documentId) throws JAXBException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SAXException {
        return p1Repository.findById(documentId);
    }
}
