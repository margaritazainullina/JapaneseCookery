package service;

import dao.RecipeDAO;
import entity.Recipe;

public class RecipeService {
    private RecipeDAO recipeDAO;

    public RecipeDAO getRecipeDAO() {
        return recipeDAO;
    }

    public void setRecipeDAO(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }
    public void save(Recipe recipe) {
        recipeDAO.save(recipe);
    }    
}