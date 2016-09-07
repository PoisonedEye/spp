package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import services.LoginService;
import util.ServiceUtil;

import java.util.Map;

public class UserAction extends ActionSupport implements SessionAware {

    String fullName;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    String position;

    private Map<String, Object> userSession;

    public void setSession(Map<String, Object> session) {
        userSession = session;
    }

    public String userInfo() {
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            fullName = (String) userSession.get("fullName");
            position = (String) userSession.get("position");
            return SUCCESS;
        }
        fullName = "Guest";
        position = "Guest";
        return SUCCESS;
    }

    public String userPosition() {
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            position = (String) userSession.get("position");
            return SUCCESS;
        }
        position = "Guest";
        return SUCCESS;
    }


}
