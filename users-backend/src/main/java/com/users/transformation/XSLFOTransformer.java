package com.users.transformation;
import net.sf.saxon.TransformerFactoryImpl;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.HashMap;

@Component
public class XSLFOTransformer {


    private FopFactory fopFactory;
    private TransformerFactory transformerFactory;
    private static HashMap<Class, String> shemaLocationRegistry = new HashMap<>();

    static {

        //shemaLocationRegistry.put(ObrazacA1.class, "src/main/resources/data/xsl_fo/a1-fo.xsl");
    }

    public XSLFOTransformer() throws SAXException, IOException {

        // Initialize FOP factory object
        fopFactory = FopFactory.newInstance(new File("users-backend/src/main/java/com/users/fop.xconf"));

        // Setup the XSLT transformer factory
        transformerFactory = new TransformerFactoryImpl();
    }

    public ByteArrayResource generatePDF(String xmlContent, String XSL_FILE) throws Exception {


        System.out.println("[INFO] " + XSLFOTransformer.class.getSimpleName());

        // Point to the XSL-FO file
        File xslFile = new File(XSL_FILE);

        // Create transformation source
        StreamSource transformSource = new StreamSource(xslFile);

        // Initialize the transformation subject

        InputStream targetStream = new ByteArrayInputStream(xmlContent.getBytes());

        StreamSource source = new StreamSource(targetStream);

        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Create the output stream to store the results
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

        // Resulting SAX events
        Result res = new SAXResult(fop.getDefaultHandler());

        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);


        return new ByteArrayResource(outStream.toByteArray());

    }


}
