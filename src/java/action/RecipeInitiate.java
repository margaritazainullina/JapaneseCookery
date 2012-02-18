package action;

import entity.*;
import java.util.Map;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class RecipeInitiate extends ActionSupport implements SessionAware {
    private Map<String, Object> session;

    @Override
    public String execute() throws Exception {
        session.put("doc", initDoc());
        session.put("recipe", new Recipe());
        session.put("xpath", "root/info");
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    private Document initDoc() throws ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element root = doc.createElement("root");
        Element info = doc.createElement("info");
        Element prepare = doc.createElement("prepare");
        Element cook = doc.createElement("cook");
        Element image = doc.createElement("image");
        root.appendChild(info);
        root.appendChild(prepare);
        root.appendChild(cook);
        root.appendChild(image);
        doc.appendChild(root);
        return doc;
    }
}