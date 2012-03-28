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
    public void update(Recipe recipe) {
        template.update(recipe);
    }
    @Override
    public Recipe find(Long id) {
        Long[] params = {id};
        List<Recipe> recipies = template.find("FROM Recipe u WHERE u.id = ?", params);
        if (recipies==null || recipies.isEmpty()) return null;
        else return recipies.get(0);        
    }    
}