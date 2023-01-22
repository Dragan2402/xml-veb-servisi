package com.euprava.p1.service.transformation.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component
public class PDFTransformer {
    private static DocumentBuilderFactory documentFactory;
    private static TransformerFactory transformerFactory;

    private static final String XSL_FILE = "p1-backend/src/main/resources/data/pdf/p1.xsl";
    public static final String HTML_FILE = "p1-backend/src/main/resources/data/pdf/p1.html";
    private static final String OUTPUT_FILE = "p1-backend/src/main/resources/data/pdf/p1.pdf";

    static {
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        transformerFactory = TransformerFactory.newInstance();
    }

    public void generateHTML(Node xmlDocument) throws FileNotFoundException {
        try {
            StreamSource transformSource = new StreamSource(new File(XSL_FILE));

            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            DOMSource source = new DOMSource(xmlDocument);
            StreamResult result = new StreamResult(new FileOutputStream(HTML_FILE));
            transformer.transform(source, result);

        } catch (TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
        }
    }

    public File generatePDF(Node xmlDocument) throws IOException, DocumentException {
        generateHTML(xmlDocument);

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUT_FILE));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML_FILE));
        document.close();
        return new File(OUTPUT_FILE);
    }
}
