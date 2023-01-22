package com.euprava.p1.service.implementation;

import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.repository.P1Repository;
import com.euprava.p1.service.P1Service;
import com.euprava.p1.service.transformation.pdf.PDFTransformer;
import com.itextpdf.text.DocumentException;
import org.apache.fop.apps.FOPException;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

@Service
public class P1ServiceImpl implements P1Service {
    @Autowired
    private P1Repository p1Repository;
    @Autowired
    private PDFTransformer pdfTransformer;

    @Override
    public void createObrazacP1(ObrazacP1 obrazacP1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String documentId = generateDocumentId();
        obrazacP1.getPopunjavaZavod().setBrojPrijave(documentId.replace('-', '/'));
        p1Repository.save(documentId, obrazacP1);
    }

    @Override
    public ObrazacP1 retrieveObrazacP1(String documentId) throws JAXBException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SAXException {
        return p1Repository.findById(documentId);
    }

    @Override
    public File retrieveObrazacP1AsPDF(String documentId) throws IOException, DocumentException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
//        String obrazacP1String = p1Repository.findByIdAsString(documentId);
//        return xslfoTransformer.generatePDF(obrazacP1String);

        Node documentNode = p1Repository.findByIdAsNode(documentId);
        return pdfTransformer.generatePDF(documentNode);
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
}
