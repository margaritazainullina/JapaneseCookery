package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.User;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import service.UserService;

public class RecordUser extends ActionSupport implements SessionAware {
    private Map<String, Object> session;
    private UserService userService;
    private String firstName, lastName, password, email, sex;

    public String execute() throws Exception {
        User user = userService.createUser(email, password, firstName, lastName, sex);
        if (user == null) return INPUT;
        else {
            session.put("user", user);
            return SUCCESS;
        }
    }
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }    
}