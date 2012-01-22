package action;

import entity.Recipe;
import java.util.Map;
import org.w3c.dom.*;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import service.RecipeService;
import util.UtilXML;

public class RecipeCreate extends ActionSupport implements SessionAware {
    private RecipeService recipeService;
    public static final String BACK = "back";
    private static Logger log = Logger.getLogger("common");
    private Map<String, Object> session;
    private Recipe recipe;
    private Document doc;
    private String xpath, text;

    @Override
    public String execute() throws Exception {
        this.recipe =(Recipe) session.get("recipe");
        this.doc = (Document) session.get("doc");
        this.xpath = (String) session.get("xpath"); 
        
        if (xpath.equals("root/info")) {
            NodeList infoList = doc.getElementsByTagName("info");
            Node info = infoList.item(0);
            Node txt = doc.createTextNode(text);
            info.appendChild(txt);
            session.put("xpath", "root/prepare");
        } else if (xpath.equals("root/prepare")) {
            NodeList prepareList = doc.getElementsByTagName("prepare");
            Node prepare = prepareList.item(0);
            Node txt = doc.createTextNode(text);
            Element ingredient = doc.createElement("ingredient");
            ingredient.appendChild(txt);
            prepare.appendChild(ingredient);
        }

        recipe.setXml(UtilXML.getXMLasString(doc));
        recipeService.save(recipe);
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String delete() throws Exception {
        session.remove("recipe");
        session.remove("xpath");
        session.remove("doc");
        return BACK;
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}