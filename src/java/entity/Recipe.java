package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name="recipe")
@Table(name = "RECIPE")
public class Recipe implements Serializable {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)   
    private Long id;
    @Lob
    @Column(name = "xml", nullable = true)   
    private String xml; 
    @Lob
    @Column(name = "image", nullable = true)   
    private byte[] image;            

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}