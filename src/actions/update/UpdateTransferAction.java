package actions.update;

import com.opensymphony.xwork2.ActionSupport;
import jsonEntities.JsonTransfer;
import org.apache.struts2.interceptor.SessionAware;
import util.ServiceUtil;

import java.util.Map;

public class UpdateTransferAction extends ActionSupport implements SessionAware {
    Map<String, Object> userSession;

    @Override
    public void setSession(Map<String, Object> map) {
        userSession = map;
    }

    private JsonTransfer data;

    public JsonTransfer getData() {
        return data;
    }

    public void setData(JsonTransfer data) {
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
                answer = ServiceUtil.getUpdateService().tryUpdateTransfer(data, userSession);
                return SUCCESS;
            }
        }
        answer = "Нет доступа.";
        return SUCCESS;
    }
}
