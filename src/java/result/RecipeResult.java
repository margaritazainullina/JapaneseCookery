package result;

import com.opensymphony.xwork2.ActionInvocation;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.dispatcher.StrutsResultSupport;

public class RecipeResult extends StrutsResultSupport{
    private String xsltLocation;
    @Override
    protected void doExecute(String string, ActionInvocation ai) throws Exception {
        HttpServletResponse response = (HttpServletResponse) ai.getInvocationContext().get("HTTP_RESPONSE");
        response.setContentType("text/plain");
        response.getWriter().println("Hello from RecipePreview");
    }

    public String getXsltLocation() {
        return xsltLocation;
    }

    public void setXsltLocation(String xsltLocation) {
        this.xsltLocation = xsltLocation;
    }
}