package dao;

import entity.Recipe;
import entity.User;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class RecipeDAOImpl implements RecipeDAO {
    private HibernateTemplate template;
    
    @Override
    public void save(Recipe recipe) {
        template.saveOrUpdate(recipe);
    }
    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public void delete(Recipe recipe) {
        template.delete(recipe);
    }

    @Override
    public List<Recipe> getRecipies(User user) {
        List<Recipe> recipies = (List<Recipe>) template.find("from Recipe");
        //List<Recipe> recipies = (List<Recipe>) template.find("from Recipe where user=?", user);
        return recipies;
    }

    @Override
    public void update(Recipe recipe) {
        template.update(recipe);
    }
}