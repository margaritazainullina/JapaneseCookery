package interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import entity.User;
import java.util.Map;

public class Authentication implements Interceptor {

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        Map session = ai.getInvocationContext().getSession();
        User user = (User) session.get("user");
        if (user == null) {
            return Action.LOGIN;
        } else {
           // Action action = (Action) ai.getAction();
            return ai.invoke();
        }

    }
}
