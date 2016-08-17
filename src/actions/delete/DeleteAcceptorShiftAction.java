package actions.delete;

import com.opensymphony.xwork2.ActionSupport;
import entities.AcceptorShift;
import jsonEntities.JsonAcceptorShift;
import org.apache.struts2.interceptor.SessionAware;
import util.ServiceUtil;

import java.util.Map;

public class DeleteAcceptorShiftAction extends ActionSupport implements SessionAware {
    Map<String, Object> userSession;

    @Override
    public void setSession(Map<String, Object> map) {
        userSession = map;
    }

    private JsonAcceptorShift data;

    public JsonAcceptorShift getData() {
        return data;
    }

    public void setData(JsonAcceptorShift data) {
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
                answer = ServiceUtil.getDeleteService().tryDelete(data, userSession, AcceptorShift.class);
                return SUCCESS;
            }
        }
        answer = "Нет доступа.";
        return SUCCESS;
    }
}