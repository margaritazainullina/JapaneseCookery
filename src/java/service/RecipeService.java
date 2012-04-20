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
    public void update(Recipe recipe) {
        recipeDAO.update(recipe);
    }
    public Recipe getRandomRecipeWithImage(){
        Random rand = new Random(); 
        List<Recipe> list = recipeDAO.getAllRecipies();
        int size = list.size(), randomIndex;
        boolean isFind = false;
        Recipe recipe = null;
        
        while((size > 0) & (!isFind)){
            randomIndex = rand.nextInt(size);
            size = size - 1;
            recipe = list.get(randomIndex);
            isFind = recipe.getIsPhotoAdded();
            if (!isFind) list.remove(randomIndex);
        }
        if (!isFind) return null;
        else return recipe;
    }
}