package dao;

import entity.User;

public interface UserDAO {
    User getByPassword(String email, String pwd);
}