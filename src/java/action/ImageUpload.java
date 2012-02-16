package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Recipe;
import java.io.*;
import java.util.Map;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.struts2.interceptor.SessionAware;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import service.RecipeService;
import util.UtilXML;

public class ImageUpload extends ActionSupport implements SessionAware {
    private RecipeService recipeService;
    private Map<String, Object> session;    
    private File image;
    private String imageContentType;
    private String imageFileName;

    public ImageUpload() {}

    public String execute() throws TransformerConfigurationException, TransformerException {
        try {
            FileInputStream fin = new FileInputStream(image);
            byte[] fileContent = new byte[(int) image.length()];
            fin.read(fileContent);
            Recipe recipe = (Recipe) session.get("recipe");
            // Добавление байтового представления в xml
            // 1. Получить doc
            Document doc = (Document) session.get("doc");
            NodeList imageList = doc.getElementsByTagName("image");
            imageList.item(0).setTextContent(new String(fileContent));
            recipe.setXml(UtilXML.getXMLasString(doc));
            recipeService.save(recipe);
        } catch (FileNotFoundException e){}
        catch (IOException ioe) {}
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
   
}