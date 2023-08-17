package com.euprava.p1.service.transformation.pdf;

import java.io.*;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.*;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import net.sf.saxon.TransformerFactoryImpl;

public class XSLFOTransformer {
    private FopFactory fopFactory;
    private TransformerFactory transformerFactory;

    private static final String XSL_FILE = "src/main/resources/data/pdf/p1_fo.xsl";
    private static final String OUTPUT_FILE = "src/main/resources/data/pdf/p1_output.pdf";
    private static final String XCONF_FILE = "src/main/resources/data/pdf/fop.xconf";

    public XSLFOTransformer() throws IOException, SAXException {
        fopFactory = FopFactory.newInstance(new File(XCONF_FILE));
        transformerFactory = new TransformerFactoryImpl();
    }

    public File generatePDF(String xmlString) throws TransformerException, FOPException, IOException {
        File xslFile = new File(XSL_FILE);
        StreamSource transformSource = new StreamSource(xslFile);
//        File inputFile = new File(INPUT_FILE);
        InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes());
        StreamSource source = new StreamSource(inputStream);

        FOUserAgent userAgent = fopFactory.newFOUserAgent();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
        Result result = new SAXResult(fop.getDefaultHandler());
        xslFoTransformer.transform(source, result);

        File pdfFile = new File(OUTPUT_FILE);
        if (!pdfFile.getParentFile().exists()) {
            pdfFile.getParentFile().mkdir();
        }

        OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
        out.write(outStream.toByteArray());
        out.close();

        return pdfFile;
    }
}
