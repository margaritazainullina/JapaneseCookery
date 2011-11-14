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
        if (user1!=null) {
            session.put("user", user1);
              return SUCCESS;
        }
        else return INPUT;
    }

    @Override
    public void setSession(Map<String, Object> map) {
       this.session=map;
    }
}
