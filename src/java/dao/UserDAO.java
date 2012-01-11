package dao;

import entity.User;

public interface UserDAO {
    User getByPassword(String email, String pwd);
    User create(String mail, String pwd, String fName, String lName, String sex);
}