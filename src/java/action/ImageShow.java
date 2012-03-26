package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class ImageShow extends ActionSupport implements ServletRequestAware{
    private InputStream imageStream;
    private HttpServletRequest request;

    public InputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
    }

    @Override
    public String execute() throws Exception {
//        Logger log = Logger.getLogger("common");
//        log.info(request.getParameter("src"));
        imageStream = ImageShow.class.getResourceAsStream("/misc/" + request.getParameter("src"));
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}