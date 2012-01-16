package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Recipe;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class RecipeCreate extends ActionSupport implements SessionAware {
    private Map<String, Object> session;  
    private Recipe recipe; 
    
    public String execute() throws Exception {
        this.recipe = getRecipe();
        
        
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
}
