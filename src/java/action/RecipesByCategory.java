package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.*;
import java.util.*;
import org.apache.struts2.interceptor.SessionAware;
import service.RecipeService;

public class RecipesByCategory extends ActionSupport {
    private RecipeService recipeService;
    private String jsonIdCategory;
    private String category;    

    public String execute() throws Exception {
        jsonIdCategory = recipeService.getRecipiesByCategory(category);
        
        return category;
    }
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public String getJsonIdCategory() {
        return jsonIdCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}