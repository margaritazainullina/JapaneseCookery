package action;

import entity.*;
import java.util.Map;
import org.w3c.dom.*;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import service.*;
import util.UtilXML;

public class RecipeProduction extends ActionSupport implements SessionAware {
    // private static Logger log = Logger.getLogger("common");    
    private RecipeService recipeService;
    private UserService userService;

    private Map<String, Object> session;
    private String category;
    private String recipeName, info, unit, text;
    private Integer amount;

    @Override
    public String execute() throws Exception {
        Recipe recipe =(Recipe) session.get("recipe");
        Document doc = (Document) session.get("doc");
      
        if (((String) session.get("xpath")).equals("root/info")) {
            NodeList nameList = doc.getElementsByTagName("name");
            Node nameNode = nameList.item(0);
            Node nameTextNode = doc.createTextNode(recipeName);
            nameNode.appendChild(nameTextNode);            
            
            NodeList infoList = doc.getElementsByTagName("info");
            Node infoNode = infoList.item(0);
            Node infoTextNode = doc.createTextNode(info);
            infoNode.appendChild(infoTextNode);
            
            recipe.setCategory(category);
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

        return SUCCESS;
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
        String xPath = (String) session.get("xpath");
        if (xPath.equals("root/image")|xPath.equals("root/cook")) {
            Recipe recipe =(Recipe) session.get("recipe");
            recipe.setHtml(UtilXML.xsltTransform(recipe.getXml()));
            recipeService.save(recipe);
            User user = (User)session.get("user");
            userService.update(user);
            user.getRecipes().add(recipe);
            userService.save(user);
            session.remove("recipe");
            session.remove("xpath");
            session.remove("doc");
            return INPUT;
        }else return INPUT;
    }
    
    public String delete() throws Exception {
        session.remove("recipe");
        session.remove("xpath");
        session.remove("doc");
        return INPUT;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}