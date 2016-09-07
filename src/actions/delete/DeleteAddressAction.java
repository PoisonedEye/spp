package actions.delete;

import com.opensymphony.xwork2.ActionSupport;
import entities.Address;
import jsonEntities.JsonAddress;
import org.apache.struts2.interceptor.SessionAware;
import util.ServiceUtil;

import java.util.Map;

public class DeleteAddressAction extends ActionSupport implements SessionAware {
    Map<String, Object> userSession;

    @Override
    public void setSession(Map<String, Object> map) {
        userSession = map;
    }

    private JsonAddress data;

    public JsonAddress getData() {
        return data;
    }

    public void setData(JsonAddress data) {
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
                answer = ServiceUtil.getDeleteService().tryDelete(data, userSession, Address.class);
                return SUCCESS;
            }
        }
        answer = "Access denied.";
        return SUCCESS;
    }
}
