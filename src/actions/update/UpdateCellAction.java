package actions.update;

import com.opensymphony.xwork2.ActionSupport;
import jsonEntities.JsonCell;
import org.apache.struts2.interceptor.SessionAware;
import util.ServiceUtil;

import java.util.Map;

public class UpdateCellAction extends ActionSupport implements SessionAware {
    Map<String, Object> userSession;

    @Override
    public void setSession(Map<String, Object> map) {
        userSession = map;
    }

    private JsonCell data;

    public JsonCell getData() {
        return data;
    }

    public void setData(JsonCell data) {
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
                answer = ServiceUtil.getUpdateService().tryUpdateCell(data, userSession);
                return SUCCESS;
            }
        }
        answer = "Access denied.";
        return SUCCESS;
    }
}
