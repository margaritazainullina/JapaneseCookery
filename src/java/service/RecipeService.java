package service;

import dao.RecipeDAO;
import entity.*;
import java.util.*;

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
    public void delete(Recipe recipe) {
        recipeDAO.delete(recipe);
    } 
    public List<Recipe> getRecipies(User user){
        return recipeDAO.getRecipies(user);
    }
}