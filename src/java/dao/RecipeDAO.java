package dao;

import entity.*;
import java.util.List;

public interface RecipeDAO {
    public void save(Recipe recipe);
    public void delete(Recipe recipe);
    public List<Recipe> getRecipies(User user);
    public void update(Recipe recipe);
}
