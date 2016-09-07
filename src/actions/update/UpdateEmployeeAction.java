package actions.update;

import com.opensymphony.xwork2.ActionSupport;
import entities.Employee;
import jsonEntities.JsonEmployee;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import util.ServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateEmployeeAction extends ActionSupport implements SessionAware {

    Map<String, Object> userSession;

    @Override
    public void setSession(Map<String, Object> map) {
        userSession = map;
    }

    private JsonEmployee data;

    public JsonEmployee getData() {
        return data;
    }

    public void setData(JsonEmployee data) {
        this.data = data;
    }

    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String execute() {
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Administrator") ||
                    userSession.get("position").equals("Менеджер")) {
                answer = ServiceUtil.getUpdateService().tryUpdateEmployee(data, userSession);
                return SUCCESS;
            }
        }
        answer = "Access denied.";
        return SUCCESS;
    }
}
