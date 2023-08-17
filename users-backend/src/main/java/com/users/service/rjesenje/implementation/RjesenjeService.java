package com.users.service.rjesenje.implementation;

import com.users.controller.Responses.CreateRjesenjeResponse;
import com.users.controller.Responses.RjesenjeResponse;
import com.users.model.rjesenje.Rjesenje;
import com.users.repository.RjesenjeRepository;
import com.users.service.rjesenje.IRjesenjeService;
import com.users.transformation.XSLFOTransformer;
import com.users.util.EmailService;
import com.users.util.SchemaValidationHandler;
import com.users.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Service
public class RjesenjeService implements IRjesenjeService {

    private final RjesenjeRepository rjesenjeRepository;

    private final XSLFOTransformer xslfoTransformer;

    private final EmailService emailService;

    @Override
    public CreateRjesenjeResponse create(Rjesenje rjesenje) throws Exception {
        rjesenje.setId(Utility.getNextId());
        DatatypeFactory df = DatatypeFactory.newInstance();
        XMLGregorianCalendar xmlCalendar = df.newXMLGregorianCalendar();
        xmlCalendar.setYear(GregorianCalendar.getInstance().get(Calendar.YEAR));
        xmlCalendar.setMonth(GregorianCalendar.getInstance().get(Calendar.MONTH) + 1);
        xmlCalendar.setDay(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
        rjesenje.setDatum(xmlCalendar);

        rjesenjeRepository.save("/db/rjesenje","id_"+rjesenje.getId(), rjesenje);

        Rjesenje rjesenjeSaved = findById(rjesenje.getId());
        if(rjesenjeSaved.getSifra() == null){
            return new CreateRjesenjeResponse("",rjesenjeSaved.getId());
        }else{
            return new CreateRjesenjeResponse(rjesenje.getSifra().toString(),rjesenje.getId());
        }
    }

    @Override
    public Rjesenje findById(long id) throws Exception {
        return rjesenjeRepository.findById("/db/rjesenje","id_"+id);
    }

    @Override
    public ByteArrayResource getRjesenjeStringByRequestId(long requestId) throws Exception {
        String queryPath = "src/main/resources/data/xquery/rjesenjeByRequestId.xqy";
        byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
        String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
        String formattedXQueryExpression = String.format(xqueryExpression, requestId);
        System.out.println(formattedXQueryExpression);

        List<Resource> resources = rjesenjeRepository.getObrazacByQuery("/db/a1", "http://euprava.euprava.com/model/a1Sertifikat", formattedXQueryExpression);

        ByteArrayResource resourceReturn = null;
        for (Resource resource : resources) {
            Rjesenje tempRequest = unmarshallXMLResource((XMLResource) resource);
            JAXBContext context = JAXBContext.newInstance("com.users.model.rjesenje");
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            OutputStream os = new ByteArrayOutputStream();
            marshaller.marshal(tempRequest, os);
            resourceReturn = xslfoTransformer.generatePDF(os.toString(),"src/main/resources/data/xsl_fo/rjesenje.xsl");
        }
        return resourceReturn;
    }

    @Override
    public void sendEmail(String email, String id) throws Exception {
        Rjesenje rjesenje = findById(Long.parseLong(id));
        JAXBContext context = JAXBContext.newInstance("com.users.model.rjesenje");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(rjesenje, os);
        ByteArrayResource resourceReturn = xslfoTransformer.generatePDF(os.toString(),"src/main/resources/data/xsl_fo/rjesenje.xsl");
        emailService.sendEmailWithAttachment(email, resourceReturn);
    }

    private Rjesenje unmarshallXMLResource(XMLResource resource) throws JAXBException, SAXException, XMLDBException {
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
