package actions;

import com.opensymphony.xwork2.ActionSupport;
import entities.Employee;
import org.apache.struts2.interceptor.SessionAware;
import services.LoginService;
import util.ServiceUtil;

import java.util.Map;

public class LoginAction extends ActionSupport implements SessionAware {
    private Map<String, Object> userSession;

    public void setSession(Map<String, Object> session) {
        userSession = session;
    }
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    private Boolean answer;

    public String tryLogin() {
        LoginService loginService = ServiceUtil.getLoginService();
        answer = loginService.Login(getLogin(), getPassword(), userSession);
        return SUCCESS;
    }

    public String tryLogout() {
        LoginService loginService = ServiceUtil.getLoginService();
        answer = loginService.Logout(userSession);
        return SUCCESS;
    }


}
