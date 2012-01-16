package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Recipe;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.w3c.dom.Document;
import service.RecipeService;

public class RecipeCreate extends ActionSupport implements SessionAware {
    private RecipeService recipeService;
    public static final String BACK = "back";
    private Map<String, Object> session;  
    private Recipe recipe; 
    private Document doc;
    private String text;
    
    public String execute() throws Exception {
        this.recipe = getRecipe();
        this.doc = getDocument();
        recipe.setXml(text);
        
        recipeService.save(recipe);
        
        return SUCCESS;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }    
    private Recipe getRecipe(){
        Recipe recipe = (Recipe) session.get("recipe");
        if (recipe==null) {
            recipe = new Recipe();
            session.put("recipe", recipe);
        }
        return recipe;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String delete() throws Exception {
        session.remove("recipe");
        return BACK;
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    private Document getDocument() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}