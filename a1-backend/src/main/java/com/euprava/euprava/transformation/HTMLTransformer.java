package com.euprava.euprava.transformation;

import com.euprava.euprava.model.a1sertifikat.ObrazacA1;
import org.apache.fop.apps.Fop;
import org.springframework.stereotype.Component;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.HashMap;

@Component
public class HTMLTransformer {

    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    private static final HashMap<Class, String> shemaLocationRegistry = new HashMap<>();

    static {

        shemaLocationRegistry.put(ObrazacA1.class, "src/main/resources/data/xsl_fo/a1-fo.xsl");
    }

    public HTMLTransformer(){
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);
        transformerFactory = TransformerFactory.newInstance();
    }

    public org.w3c.dom.Document buildDocument(String filePath) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(new File(filePath));

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;

        }

        return document;
    }


    public String generateHTML(String xmlPath, String xslPath, String HTML_FILE) throws FileNotFoundException {

        try {

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(xmlPath));
            StreamResult result = new StreamResult(new FileOutputStream(HTML_FILE));
            transformer.transform(source, result);
            return HTML_FILE;

        } catch (TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
            return "";
        }

    }
}
