package dao;

import entity.Recipe;

public interface RecipeDAO {
    public void save(Recipe recipe);
    public void delete(Recipe recipe);
}
