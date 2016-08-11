package actions.create;

import com.opensymphony.xwork2.ActionSupport;
import jsonEntities.JsonAddress;
import org.apache.struts2.interceptor.SessionAware;
import util.ServiceUtil;

import java.util.Map;

public class CreateAddressAction extends ActionSupport implements SessionAware {
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
            if (userSession.get("position").equals("Администратор") ||
                    userSession.get("position").equals("Менеджер")) {
                answer = ServiceUtil.getCreateService().tryCreateAddress(data, userSession);
                return SUCCESS;
            }
        }
        answer = "Нет доступа.";
        return SUCCESS;
    }
}
