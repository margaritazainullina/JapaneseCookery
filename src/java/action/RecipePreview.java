package action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.w3c.dom.*;

public class RecipePreview extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private String info;
    private List<String> ingredients = new ArrayList<String>();
    private List<String> steps = new ArrayList<String>();
    private static Logger log = Logger.getLogger("common");

    @Override
    public String execute() throws Exception {
        Document doc = (Document) session.get("doc");
        // Заполним поле info
        NodeList infoList = doc.getElementsByTagName("info");
        info = infoList.item(0).getTextContent();
        if (info.isEmpty()) {
            info = "Здесь будет отображаться рецепт!";
        }
        // Заполним поле ingredient        
        NodeList ingredientNodes = doc.getElementsByTagName("ingredient");
        for (int i = 0; i < ingredientNodes.getLength(); i++) {
            ingredients.add(ingredientNodes.item(i).getTextContent());
        }
        // Заполним поле ingredient        
        NodeList stepNodes = doc.getElementsByTagName("step");
        for (int i = 0; i < stepNodes.getLength(); i++) {
            steps.add(stepNodes.item(i).getTextContent());
        }
        return SUCCESS;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
