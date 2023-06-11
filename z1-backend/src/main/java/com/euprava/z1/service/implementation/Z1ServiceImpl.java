package com.euprava.z1.service.implementation;

import com.euprava.z1.model.Z1;
import com.euprava.z1.repository.Z1Repository;
import com.euprava.z1.service.Z1Service;
import lombok.RequiredArgsConstructor;
import org.exist.http.NotFoundException;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Service
@RequiredArgsConstructor
public class Z1ServiceImpl implements Z1Service {

    private final Z1Repository z1Repository;

    @Override
    public List<Z1> getAllZ1() throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException, InvocationTargetException, NoSuchMethodException {
        String queryPath = "src/main/resources/data/xquery/all.xqy";
        byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
        String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
        List<org.xmldb.api.base.Resource> resources = z1Repository.getObrazacByQuery("/db/z1", "http://euprava.euprava.com/model", xqueryExpression);
        List<Z1> z1List = new ArrayList<>();
        for (org.xmldb.api.base.Resource resource : resources) {
            Z1 z1 = unmarshallXMLResource((XMLResource) resource);
            z1List.add(z1);
        }
        return z1List;

    }

    @Override
    public String createZ1(Z1 z1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, DatatypeConfigurationException, NotFoundException, IOException, TransformerException {
        int randomNum = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        String documentId = String.valueOf(randomNum);
        z1Repository.save(documentId, z1);
        return documentId;
    }

    private Z1 unmarshallXMLResource(XMLResource resource) throws JAXBException, XMLDBException {
        JAXBContext context = JAXBContext.newInstance(Z1.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Z1) unmarshaller.unmarshal(new StringReader(resource.getContent().toString()));
    }
}
