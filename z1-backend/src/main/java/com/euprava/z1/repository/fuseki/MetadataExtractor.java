package com.euprava.z1.repository.fuseki;

import net.sf.saxon.TransformerFactoryImpl;
import org.springframework.stereotype.Component;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

@Component
public class MetadataExtractor {

    private TransformerFactory transformerFactory;

    private static final String XSLT_FILE = "src/main/resources/data/xsl/grddl.xsl";
    private static final String RDF_FILE = "src/main/resources/rdf/metadata.rdf";

    public MetadataExtractor() {

        transformerFactory = new TransformerFactoryImpl();
    }

//    public void extractMetadata(String in) throws TransformerException, IOException {
//        try (OutputStream out = Files.newOutputStream(Paths.get(RDF_FILE))) {
//            StreamSource transformSource = new StreamSource(new File(XSLT_FILE));
//
//            Transformer grddlTransformer = transformerFactory.newTransformer(transformSource);
//            grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
//            grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
//
//            StreamSource source = new StreamSource(new StringReader(in));
//            StreamResult result = new StreamResult(out);
//
//            grddlTransformer.transform(source, result);
//        }
//    }

    public byte[] extractMetadata(String in) throws TransformerException {
        StreamSource transformSource = new StreamSource(new File(XSLT_FILE));

        Transformer grddlTransformer = transformerFactory.newTransformer(transformSource);
        grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamSource source = new StreamSource(new ByteArrayInputStream(in.getBytes()));

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        grddlTransformer.transform(source, new StreamResult(result));

        return result.toByteArray();
    }
}