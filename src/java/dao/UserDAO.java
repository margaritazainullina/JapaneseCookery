package dao;

import entity.*;
import java.util.Set;

public interface UserDAO {
    User getByPassword(String email, String pwd);
    User create(String mail, String pwd, String fName, String lName, String sex);
    void save(User user);
    void update(User user);
    public User find(Long id);    
    public Set<Recipe> getRecipies(User user);
}