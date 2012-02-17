package entity;

import java.io.IOException;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.CharacterData;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import util.UtilXML;

@Entity(name = "recipe")
@Table(name = "RECIPE")
public class Recipe implements Serializable {
    @Transient
    private static Logger log = Logger.getLogger("common");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Lob
    @Column(name = "xml", nullable = true)
    private String xml;
    @Transient
    private Boolean isPhotoAdded;

    public Recipe() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getXml() {
        return xml;
    }
    public void setXml(String xml) {
        this.xml = xml;
    }

    public Boolean getIsPhotoAdded() throws ParserConfigurationException, SAXException, IOException {
        Document doc = UtilXML.getDocumentFromString(xml);
        NodeList imageList = doc.getElementsByTagName("image");
//        log.info("imageList.getLength() -> " + imageList.getLength());
//        log.info("imageList.item(0).getNodeValue() -> " + imageList.item(0).getNodeValue());
//        log.info("imageList.item(0).getNodeName() -> " + imageList.item(0).getNodeName());
//        log.info("imageList.item(0).getTextContent() -> " + imageList.item(0).getTextContent());
//        log.info("xml -> " + xml);

        byte[] image = imageList.item(0).getTextContent().getBytes();
        if (image.length > 0) {
            return true;
        } else {
            return false;
        }
    }

//    public static String getCharacterDataFromElement(Element e) {
//        Node child = e.getFirstChild();
//        if (child instanceof CharacterData) {
//            CharacterData cd = (CharacterData) child;
//            return cd.getData();
//        }
//        return "";
//    }
}