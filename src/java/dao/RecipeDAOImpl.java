package dao;

import entity.Recipe;
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
}
