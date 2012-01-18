package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Recipe;
import java.io.StringWriter;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.struts2.interceptor.SessionAware;
import org.w3c.dom.Document;
import service.RecipeService;

public class RecipeCreate extends ActionSupport implements SessionAware {
    private RecipeService recipeService;
    public static final String BACK = "back";
    private Map<String, Object> session;
    private Recipe recipe;
    private Document doc;
    private String text;

    public String execute() throws Exception {
        this.recipe = getRecipe();
        this.doc = getDocument();
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
            session.put("doc", doc);
        }
        return doc;
    }

    private String getXMLasString(Document doc) throws TransformerConfigurationException, TransformerException {
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
}