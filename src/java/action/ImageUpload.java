package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Recipe;
import java.io.*;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import service.RecipeService;

public class ImageUpload extends ActionSupport implements SessionAware {
    private RecipeService recipeService;
    private Map<String, Object> session;    
    private File image;
    private String imageContentType;
    private String imageFileName;

    public String execute() {
        try {
            FileInputStream fin = new FileInputStream(image);
            byte[] fileContent = new byte[(int) image.length()];
            fin.read(fileContent);
            Recipe recipe = (Recipe) session.get("recipe");
            recipe.setImage(fileContent);
            //recipeService.save();
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