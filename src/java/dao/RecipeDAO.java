package dao;

import entity.*;
import java.util.List;

public interface RecipeDAO {
    public void save(Recipe recipe);
    public void delete(Recipe recipe);
    public void update(Recipe recipe);
    public Recipe find(Long id);
    public List<Recipe> getAllRecipies();
}
