package service;

import dao.RecipeDAO;
import entity.*;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class RecipeService {
    private JdbcTemplate jdbcTemplate;
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
    public List<Recipe> getAllRecipe(){
        return recipeDAO.getAllRecipies();
    }
    public Recipe findById(Long id){
        return recipeDAO.find(id);
    }    

    public String getUserIdCategory(User user) {
        Long[] params = { user.getId() };
        List<Map> rows = jdbcTemplate.queryForList("select recipe_id from user_recipe tab where tab.user_id = ?", params);
        if (rows.size() == 0) return "";
        
        
        StringBuilder sb = new StringBuilder("[");
        for (Map row: rows){
            Long id = (Long) row.get("recipe_id");
            sb.append("{'id':").append(id.toString()).append(",");
            String category = getCategoryById(id);
            sb.append("'category':'").append(category).append("'}");
            sb.append(",");
        }
        String result = sb.substring(0, sb.lastIndexOf(","));
        return result + "]";
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String getCategoryById(Long id) {
        Long[] params = {id};
        String category = (String) jdbcTemplate.queryForObject("select category from recipe where id=?", params, java.lang.String.class);
        return category;
        
    }
}