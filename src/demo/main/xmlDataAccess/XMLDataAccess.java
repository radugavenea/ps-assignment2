package demo.main.xmlDataAccess;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by radu on 11.04.2017.
 */
public class XMLDataAccess {

    private URL url;
    private File xmlFile;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document document;
    private TransformerFactory transformerFactory;
    private Transformer transformer;
    private DOMSource source;
    private StreamResult result;


    /**
     * Sets up the connection to parse xml
     * @param xmlPath
     * @return Document Element used to parse xml file
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public Document getXMLDocumentElement(String xmlPath) throws IOException, ParserConfigurationException, SAXException {
        getXMLFile(xmlPath);
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        document = dBuilder.parse(xmlPath);
        document.getDocumentElement().normalize();
        return document;
    }


    /**
     * Method used to create the transformer for writing the xml file
     * @param xmlPath
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void setUpXMLTransformerWriter(Document document, String xmlPath) throws TransformerException, ParserConfigurationException, SAXException, IOException {
        transformerFactory = TransformerFactory.newInstance();
        transformer = transformerFactory.newTransformer();
        source = new DOMSource(document);
        result = new StreamResult(xmlPath);
        transformer.transform(source,result);
    }

    /**
     * Retrieve the xml file or creates new one if doesn't exist
     * @param xmlPath
     * @throws IOException
     */
    private void getXMLFile(String xmlPath) throws IOException {
        url = getClass().getResource(xmlPath);
        if(url != null){
            xmlFile = new File(url.getPath());
        }
        else{
            xmlFile = new File(xmlPath);
            xmlFile.createNewFile();
        }
    }


}
