package actions.create;

import com.opensymphony.xwork2.ActionSupport;
import jsonEntities.JsonEmployee;
import jsonEntities.JsonPosition;
import org.apache.struts2.interceptor.SessionAware;
import util.ServiceUtil;

import java.util.Map;

public class CreatePositionAction extends ActionSupport implements SessionAware {
    Map<String, Object> userSession;

    @Override
    public void setSession(Map<String, Object> map) {
        userSession = map;
    }

    private JsonPosition data;

    public JsonPosition getData() {
        return data;
    }

    public void setData(JsonPosition data) {
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
            if (userSession.get("position").equals("Administrator")){
                answer = ServiceUtil.getCreateService().tryCreatePosition(data, userSession);
                return SUCCESS;
            }
        }
        answer = "Нет доступа.";
        return SUCCESS;
    }
}
