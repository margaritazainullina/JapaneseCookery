package util;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Document;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.*;

public class UtilXML {
    public static Document getDocumentFromString(String xml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml)));
        return doc;
    }

    public static String getXMLasString(Document doc) throws TransformerConfigurationException, TransformerException {
        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.METHOD, "xml");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(2));

        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(doc.getDocumentElement());

        trans.transform(source, result);
        return sw.toString();
    }
    public static String xsltTransform(String xmlString){
        String result = null;
        try {
            StringWriter writer = new StringWriter();
            StringReader reader = new StringReader(xmlString);
            
            TransformerFactory factory = TransformerFactory.newInstance();
            StreamSource xslt = new StreamSource(UtilXML.class.getResourceAsStream("/misc/recipe.xslt"));
            Transformer transformer = factory.newTransformer(xslt);
           
            transformer.transform(new StreamSource(reader), new StreamResult(writer));
            result = writer.toString();
        } catch (Exception ex) { }
        return result;
    }
}