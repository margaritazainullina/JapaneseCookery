package action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class Exit extends ActionSupport implements SessionAware {
    private Map<String, Object> session;

    @Override
    public String execute() throws Exception {
        if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
            ((SessionMap) session).invalidate();
        }
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}