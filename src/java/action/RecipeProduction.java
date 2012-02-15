package action;

import entity.Recipe;
import java.util.Map;
import org.w3c.dom.*;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import service.RecipeService;
import util.UtilXML;

public class RecipeProduction extends ActionSupport implements SessionAware {
    private RecipeService recipeService;
    public static final String BACK = "back";
    private static Logger log = Logger.getLogger("common");
    private Map<String, Object> session;
    private String text, unit;
    private Integer amount;

    @Override
    public String execute() throws Exception {
        Recipe recipe =(Recipe) session.get("recipe");
        Document doc = (Document) session.get("doc");
      
        if (((String) session.get("xpath")).equals("root/info")) {
            NodeList infoList = doc.getElementsByTagName("info");
            Node info = infoList.item(0);
            Node txt = doc.createTextNode(text);
            info.appendChild(txt);
            session.put("xpath", "root/prepare");
        } else if (((String) session.get("xpath")).equals("root/prepare")) {
            NodeList prepareList = doc.getElementsByTagName("prepare");
            Node prepare = prepareList.item(0);
            Node txt = doc.createTextNode(text);
            Element ingredient = doc.createElement("ingredient");
            ingredient.setAttribute("unit", unit);
            ingredient.setAttribute("amount", amount.toString());
            ingredient.appendChild(txt);
            prepare.appendChild(ingredient);
        }else if (((String) session.get("xpath")).equals("root/cook")) {
            NodeList cookList = doc.getElementsByTagName("cook");
            Node cook = cookList.item(0);
            Node txt = doc.createTextNode(text);
            Element step = doc.createElement("step");
            step.appendChild(txt);
            cook.appendChild(step);
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
    public String cook() {
        session.put("xpath", "root/cook");
        return SUCCESS;
    }
    
    public String image() {
        session.put("xpath", "root/image");
        return SUCCESS;
    }    
    
    public String complete() throws Exception {
        session.remove("recipe");
        session.remove("xpath");
        session.remove("doc");
        return BACK;
    }
    
    public String delete() throws Exception {
        recipeService.delete((Recipe)session.get("recipe"));
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}