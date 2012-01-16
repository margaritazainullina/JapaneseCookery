package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Recipe;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class RecipeCreate extends ActionSupport implements SessionAware {
    public static final String BACK = "back";
    private Map<String, Object> session;  
    private Recipe recipe; 
    private String text;
    
    public String execute() throws Exception {
        this.recipe = getRecipe();
        recipe.setXml(text);
        session.put("recipe", recipe);
        
        return SUCCESS;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }    
    private Recipe getRecipe(){
        Recipe recipe = (Recipe) session.get("recipe");
        if (recipe==null) recipe = new Recipe();
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
}
