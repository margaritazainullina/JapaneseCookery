package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.*;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import service.UserService;

public class RecipeViewMyRecipies extends ActionSupport implements SessionAware {
    private Map<String, Object> session;    
    private UserService userService;
    // private static Logger log = Logger.getLogger("common");    
    private Map<Integer, String> categories;
    private String category;
    private Set<Recipe> recipies;
   
    public RecipeViewMyRecipies() {
        categories = new HashMap<Integer, String>();
        categories.put(0, "все");
        categories.put(1, "супы");
        categories.put(2, "лапша");
        categories.put(3, "суши");
        categories.put(4, "десерты");
        categories.put(5, "другое");
        category = "0";
    }
    
    public String execute() throws Exception {
        recipies = userService.getRecipies((User) session.get("user"));
        // log.info("recipies.get(0).getXml() -> " + recipies.get(0).getXml());
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

    public Set<Recipe> getRecipies() {
        Set<Recipe> list = new HashSet<Recipe>();
        if (category.equals("0")){
            return list = recipies;
        } else if (category.equals("1")){
            for (Recipe recipe : recipies){
                if (recipe.getCategory().equals("супы")) list.add(recipe);
            }
        } else if (category.equals("2")){
            for (Recipe recipe : recipies){
                if (recipe.getCategory().equals("лапша")) list.add(recipe);
            }
        } else if (category.equals("3")){
            for (Recipe recipe : recipies){
                if (recipe.getCategory().equals("суши")) list.add(recipe);
            }
        } else if (category.equals("4")){
            for (Recipe recipe : recipies){
                if (recipe.getCategory().equals("десерты")) list.add(recipe);
            }
        } else if (category.equals("5")){
            for (Recipe recipe : recipies){
                if (recipe.getCategory().equals("другое")) list.add(recipe);
            }
        } 
        return list;
    }
    public void setRecipies(Set<Recipe> recipies) {
        this.recipies = recipies;
    }
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}