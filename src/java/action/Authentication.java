package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.User;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class Authentication extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private String email;
    private String password;

    public String execute() throws Exception {
        User user1=new User();
        
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
       this.session=map;
    }
}
