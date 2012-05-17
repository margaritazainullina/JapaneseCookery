package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Recipe;
import java.io.*;
import service.RecipeService;

public class GetByIdRecipeXML extends ActionSupport {
    private RecipeService recipeService;
    private InputStream xmlStream;
    private String id;

    @Override
    public String execute() throws Exception {
        Recipe recipe = recipeService.findById(new Long(id));
        String xml = recipe.getXml();
        
        xmlStream = convert(xml);
        return SUCCESS;
    }
    public GetByIdRecipeXML(){}
    
    private static InputStream convert(String str) {
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return is;
    }

    public InputStream getXmlStream() {
        return xmlStream;
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}