package interceptor;

import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import entity.User;
import java.util.Map;

public class Authentication extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        Map session = ai.getInvocationContext().getSession();
        User user = (User) session.get("user");
        if (user == null) return Action.LOGIN;
        else return ai.invoke();
    }
}