package dao;

import entity.User;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class UserDAOImpl implements UserDAO {
    private HibernateTemplate template;

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public User getByPassword(String email, String pwd) {
        String[] params = {email, pwd};
        List<User> users = template.find("FROM user u WHERE u.email = ? and u.password = ?", params);
        if (users==null || users.size()==0) return null;
        else return users.get(0);
    }    
}