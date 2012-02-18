package action;

import java.io.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.codec.binary.Base64;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.w3c.dom.*;

public class RecipeShowPhoto extends ActionSupport implements SessionAware{
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
        Document doc = (Document) session.get("doc");
        NodeList imageList = doc.getElementsByTagName("image");
        String str = imageList.item(0).getTextContent();
        byte[] decoded = Base64.decodeBase64(str.getBytes());
        imageStream = new ByteArrayInputStream(decoded);
        return SUCCESS;
    }
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }      
}