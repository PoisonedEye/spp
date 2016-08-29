package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import util.ServiceUtil;

import java.util.Map;

public class RedirectAction extends ActionSupport implements SessionAware {

    protected String fullName;
    protected String position;
    protected Map<String, Object> userSession;

    public String getPosition() {
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            return (String) userSession.get("position");
        }
        return "Guest";
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFullName() {
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            return (String) userSession.get("fullName");
        }
        return "Greetings!";
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSession(Map<String, Object> session) {
        userSession = session;
    }

    public String execute(){
        return getPosition();
    }
}
