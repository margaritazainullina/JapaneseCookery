package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Recipe;
import java.io.*;
import java.util.Map;
import javax.xml.transform.*;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import service.RecipeService;
import util.UtilXML;
import org.apache.commons.codec.binary.Base64;

public class ImageUpload extends ActionSupport implements SessionAware {
    private RecipeService recipeService;
    private Map<String, Object> session;    
    private File image;
    private String imageContentType;
    private String imageFileName;
    // private static Logger log = Logger.getLogger("common");    
        
    public ImageUpload() {}
    
    @Override
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
            
            byte[] encoded = Base64.encodeBase64(fileContent); //all chars in encoded are guaranteed to be 7-bit ASCII
            imageList.item(0).setTextContent(new String(encoded));
            
            recipe.setXml(UtilXML.getXMLasString(doc));
            recipeService.save(recipe);
        } catch(FileNotFoundException e){
            //log.info("FileNotFoundException");
        }
        catch (IOException ioe) {
            //log.info("IOException");
        }
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