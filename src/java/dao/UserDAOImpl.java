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
        if (users==null || users.isEmpty()) return null;
        else return users.get(0);
    }

    @Override
    public User create(String mail, String pwd, String fName, String lName, String sex) {
        User user = new User(mail, pwd, fName, lName, sex);
        template.save(user);
        return user;
    }

    @Override
    public void save(User user) {
        template.saveOrUpdate(user);
    }

    @Override
    public void update(User user) {
        template.update(user);
    }

    @Override
    public User find(Long id) {
        User user = (User) template.get(User.class, id);
        return user;
    }
}