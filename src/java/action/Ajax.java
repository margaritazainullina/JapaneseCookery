package action;

import java.io.InputStream;
import com.opensymphony.xwork2.ActionSupport;
import entity.Recipe;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import service.RecipeService;

public class Ajax extends ActionSupport {
    private RecipeService recipeService;     
    private InputStream xmlStream;
    
    public InputStream getXmlStream() {
        return xmlStream;
    }
    public String execute() throws Exception {
        List<Recipe> list = recipeService.getAllRecipe();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++){
            builder.append(list.get(i).getId()).append(",");
        }
        String result = builder.substring(0, builder.lastIndexOf(","));
        xmlStream = convert(result); // 
        return SUCCESS;
    }
    private static InputStream convert(String str) {
        InputStream is = null;
        try { is = new ByteArrayInputStream(str.getBytes("UTF-8")); } 
        catch (UnsupportedEncodingException e) { e.printStackTrace(); }
        return is;
    }
    public RecipeService getRecipeService() {
        return recipeService;
    }
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}