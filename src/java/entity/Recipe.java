package entity;

import java.io.*;
import javax.persistence.*;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import util.UtilXML;

@Entity(name = "Recipe")
@Table(name = "RECIPE")
public class Recipe implements Serializable {
    // private static Logger log = Logger.getLogger("common");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Lob
    @Column(name = "xml", nullable = true)
    private String xml;
    @Column(name = "category", nullable = false)    
    private String category;
    @Lob    
    @Column(name = "html", nullable = true)
    private String html;      
    @Transient
    private Boolean isPhotoAdded;

    public Recipe() {}

    public String getXml() {
        return xml;
    }
    public void setXml(String xml) {
        this.xml = xml;
    }

    public Boolean getIsPhotoAdded() throws ParserConfigurationException, SAXException, IOException {
        Document doc = UtilXML.getDocumentFromString(xml);
        NodeList imageList = doc.getElementsByTagName("image");

        byte[] image = imageList.item(0).getTextContent().getBytes();
        if (image.length > 0) return true;
        else return false;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    @Override
    public String toString(){
        return this.getXml();
    }
    public String getHtml() {
        return html;
    }
    public void setHtml(String html) {
        this.html = html;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;

        final Recipe recipe = (Recipe) o;
        return getHtml().equals(recipe.getHtml());
    }
    public int hashCode() {
        return getHtml().hashCode();
    }    
}