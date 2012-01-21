package action;

import entity.Recipe;
import java.io.StringWriter;
import java.util.Map;
import org.w3c.dom.*;
import javax.xml.xpath.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import service.RecipeService;

public class RecipeCreate extends ActionSupport implements SessionAware {
    private RecipeService recipeService;
    public static final String BACK = "back";
    private static Logger log = Logger.getLogger("common");
    private Map<String, Object> session;
    private Recipe recipe;
    private Document doc;
    private String text;

    public String execute() throws Exception {
        this.recipe = getRecipe();
        this.doc = getDocument();

        String path = (String) session.get("xpath");
        
        log.info("path = " + path);
        if (path.equals("root/info")) {
            Element info = doc.createElement("info");
            Node txt = doc.createTextNode(text);
            info.appendChild(txt);
            Element root = doc.getDocumentElement();
            root.appendChild(info);
        } else if (path.equals("root/prepare")&!checkIfElementExists(doc, "root/prepare")) {
            Element prepare = doc.createElement("prepare");
            Element ingredient = doc.createElement("ingredient");
            Node txt = doc.createTextNode(text);
            ingredient.appendChild(txt);
            prepare.appendChild(ingredient);
            Element root = doc.getDocumentElement();
            root.appendChild(prepare);
        }

        recipe.setXml(getXMLasString(doc));
        recipeService.save(recipe);
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    private Recipe getRecipe() {
        recipe = (Recipe) session.get("recipe");
        if (recipe == null) {
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
        session.remove("xpath");
        return BACK;
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    private Document getDocument() throws ParserConfigurationException {
        doc = (Document) session.get("doc");
        if (doc == null) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true); // never forget this!
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
            Element root = doc.createElement("root");
            doc.appendChild(root);
            session.put("doc", doc);
        }
        return doc;
    }

    public static String getXMLasString(Document doc) throws TransformerConfigurationException, TransformerException {
        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.METHOD, "xml");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(2));

        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(doc.getDocumentElement());

        trans.transform(source, result);
        return sw.toString();
    }

    public static Boolean checkIfElementExists(Document doc, String xPath) throws XPathExpressionException {
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList) xpath.evaluate(xPath, doc, XPathConstants.NODESET);
        log.info("nodes = " + nodes);
        if (nodes == null) return false;
        else return true;
    }
}