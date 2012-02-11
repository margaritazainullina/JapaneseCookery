package action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.w3c.dom.*;

public class RecipePreview extends ActionSupport implements SessionAware {
    private Map<String, Object> session;
    private String info;
    private List ingredients = new ArrayList();
    private String[] steps;
    private static Logger log = Logger.getLogger("common");

    @Override
    public String execute() throws Exception {
        Document doc = (Document) session.get("doc");
        NodeList infoList = doc.getElementsByTagName("info");
        Node infoNode = infoList.item(0);
        info = infoNode.getTextContent();
        if (info.isEmpty()) {
            info = "Здесь будет отображаться рецепт!";
        }

        NodeList ingredientNodes = doc.getElementsByTagName("ingredient");
        if (ingredientNodes == null | ingredientNodes.getLength() == 0) {
            StringBuffer sb = new StringBuffer(info);
            sb.append("<br/>Ингридиентов вообще нет!");
            info = sb.toString();
        } else {
            for (int i = 0; i < ingredientNodes.getLength(); i++) {
                ingredients.add(ingredientNodes.item(i).getTextContent());
                log.info(ingredientNodes.item(i).getTextContent());
            }
        }

        return SUCCESS;
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
