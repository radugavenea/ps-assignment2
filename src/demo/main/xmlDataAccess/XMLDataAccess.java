package demo.main.xmlDataAccess;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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


    public Document setUpXMLConnection(String xmlPath) throws IOException, ParserConfigurationException, SAXException {
        getXMLFile(xmlPath);

        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        document = dBuilder.parse(xmlPath);
        document.getDocumentElement().normalize();

        return document;
    }


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
