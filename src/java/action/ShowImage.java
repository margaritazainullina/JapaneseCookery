package action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.ParameterAware;

public class ShowImage extends ActionSupport implements ParameterAware{
    private InputStream imageStream;
    private Map<String, String[]> parameters;

    public InputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
    }

    @Override
    public String execute() throws Exception {
        imageStream = ShowImage.class.getResourceAsStream("/misc/boy.jpg");
        //imageStream = ShowImage.class.getResourceAsStream("/misc/" + parameters.get("src")[0]);
        return SUCCESS;
    }

    @Override
    public void setParameters(Map<String, String[]> maps) {
        this.parameters = parameters;
    }
}