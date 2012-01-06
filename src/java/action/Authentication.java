package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.User;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import service.UserService;

public class Authentication extends ActionSupport implements SessionAware {
    private Map<String, Object> session;
    private UserService userService;
    private String email;
    private String password;

    public String execute() throws Exception {
        User user1 = userService.authenticateUser(email, password);
        if (user1 != null) {
            session.put("user", user1);
            return SUCCESS;
        } else {
            addActionError("Пользователь не найден");
            return INPUT;
        }
    }

    private boolean isEmptyString(String value) {
        return value == null || "".equals(value.trim());
    }
    @Override
    public void validate() {
        if (isEmptyString(email)) addFieldError("email", "Не указан логин");
        if (isEmptyString(password)) addFieldError("password", "Не указан пароль");
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}