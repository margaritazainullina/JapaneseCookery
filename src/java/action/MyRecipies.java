package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.*;
import java.util.*;
import org.apache.struts2.interceptor.SessionAware;
import service.RecipeService;

public class MyRecipies extends ActionSupport implements SessionAware {
    private Map<String, Object> session;    
    private RecipeService recipeService;
    private String jsonIdCategory;

    public String execute() throws Exception {
        User user = (User) session.get("user");
        jsonIdCategory = recipeService.getUserIdCategory(user);
        
        return SUCCESS;
    }
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public String getJsonIdCategory() {
        return jsonIdCategory;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}