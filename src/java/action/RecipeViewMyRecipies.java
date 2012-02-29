package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Recipe;
import java.util.*;
import org.apache.log4j.Logger;
import service.RecipeService;

public class RecipeViewMyRecipies extends ActionSupport {
    private RecipeService recipeService;
    private static Logger log = Logger.getLogger("common");    
    private Map<Integer, String> categories;
    private String category;
    private List<Recipe> recipies;
   
    public RecipeViewMyRecipies() {
        categories = new HashMap<Integer, String>();
        categories.put(0, "все");
        categories.put(1, "супы");
        categories.put(2, "лапша");
        categories.put(3, "суши");
        categories.put(4, "десерты");
        categories.put(5, "другое");
        category = "0";
        // recipies = recipeService.
     }
    public String execute() throws Exception {
        log.info("category  -> " + category);
        return SUCCESS;
    }
    public Map<Integer, String> getCategories() {
        return categories;
    }
    public void setCategories(Map<Integer, String> categories) {
        this.categories = categories;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public List<Recipe> getRecipies() {
        return recipies;
    }

    public void setRecipies(List<Recipe> recipies) {
        this.recipies = recipies;
    }
        
}
