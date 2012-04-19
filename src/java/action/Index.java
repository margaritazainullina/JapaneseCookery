package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Recipe;
import service.RecipeService;

public class Index extends ActionSupport {
    private Recipe recipe;    
    private RecipeService recipeService;    
    
    public String execute() throws Exception {
        return SUCCESS;
    }

    public Recipe getRecipe() {
        Recipe recipe = recipeService.getRandomRecipeWithImage();
        recipe.setXml(recipe.getXml().replaceAll("[\\n,\\r]", ""));
        return recipe;
    }
    public RecipeService getRecipeService() {
        return recipeService;
    }
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}