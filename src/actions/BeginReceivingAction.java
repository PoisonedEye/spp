package actions;

import com.opensymphony.xwork2.ActionSupport;
import jsonEntities.JsonNewReceiving;
import org.apache.struts2.interceptor.SessionAware;
import util.ServiceUtil;

import java.util.Map;

public class BeginReceivingAction extends ActionSupport implements SessionAware {
    Map<String, Object> userSession;

    @Override
    public void setSession(Map<String, Object> map) {
        userSession = map;
    }

    private JsonNewReceiving data;

    public JsonNewReceiving getData() {
        return data;
    }

    public void setData(JsonNewReceiving data) {
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
            if (userSession.get("position").equals("Storekeeper")) {
                //answer = ServiceUtil.getCreateService().tryCreateAcceptorShift(data, userSession);
                return SUCCESS;
            }
        }
        answer = "Access denied.";
        return SUCCESS;
    }

}