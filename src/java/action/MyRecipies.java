package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.*;
import java.util.*;
import org.apache.struts2.interceptor.SessionAware;
import service.RecipeService;

public class MyRecipies extends ActionSupport implements SessionAware {
    private Map<String, Object> session;    
    private RecipeService recipeService;
    //id рецептов конкретного пользователя
    private String ids;

    public String execute() throws Exception {
        User user = (User) session.get("user");
        ids = recipeService.getUserIDsRecipies(user);
        
        return SUCCESS;
    }
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    public String getIds() {
        return ids;
    }
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}