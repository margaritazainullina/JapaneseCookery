package action;

import java.io.*;
import com.opensymphony.xwork2.ActionSupport;
import entity.User;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class ShowPhoto extends ActionSupport implements SessionAware{
    private Map<String, Object> session;  
    private static final long serialVersionUID = 1L;
    private InputStream imageStream;

    public InputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
    }

    @Override
    public String execute() throws Exception {
        byte[] ar = ((User) session.get("user")).getPhoto();
        
        imageStream = new ByteArrayInputStream(ar);
        return SUCCESS;
    }
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }      
}