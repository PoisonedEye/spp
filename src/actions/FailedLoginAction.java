package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class FailedLoginAction extends ActionSupport implements SessionAware {
    private Map<String, Object> userSession;

    public void setSession(Map<String, Object> session) {
        userSession = session;
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    public String getFailedLoginMessage() {
        return failedLoginMessage;
    }

    public void setFailedLoginMessage(String failedLoginMessage) {
        this.failedLoginMessage = failedLoginMessage;
    }

    private String failedLoginMessage;

    public String failedLoginInfo() {
        Object message = userSession.get("loginFailedMessage");
        if (message != null)
            failedLoginMessage = (String) message;
        else
            failedLoginMessage = "Не было попыток входа.";
        return SUCCESS;
    }
}
