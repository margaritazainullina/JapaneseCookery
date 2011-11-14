package service;

import dao.UserDAO;
import entity.*;

public class UserService {
    private UserDAO userDAO;
    
    public UserDAO getUserDAO() {
        return userDAO;
    }
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
  
    /* Если email и пароль верны возвращает пользователя, иначе null. */
    public User authenticateUser(String email, String password) {
        return userDAO.getByPassword(email, password);
    }
}