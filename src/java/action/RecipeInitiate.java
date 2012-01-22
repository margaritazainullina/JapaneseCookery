package action;

import entity.Recipe;
import java.util.Map;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class RecipeInitiate extends ActionSupport implements SessionAware {
    private Map<String, Object> session;

    @Override
    public String execute() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element root = doc.createElement("root");
        Element info = doc.createElement("info");
        Element prepare = doc.createElement("prepare");
        Element cook = doc.createElement("cook");
        root.appendChild(info);
        root.appendChild(prepare);
        root.appendChild(cook);
        doc.appendChild(root);
        session.put("doc", doc);
        Recipe recipe = new Recipe();
        session.put("recipe", recipe);
        session.put("xpath", "root/info");
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}