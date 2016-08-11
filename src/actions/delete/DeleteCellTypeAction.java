package actions.delete;

import com.opensymphony.xwork2.ActionSupport;
import entities.Cell;
import entities.CellType;
import jsonEntities.JsonCellType;
import org.apache.struts2.interceptor.SessionAware;
import util.ServiceUtil;

import java.util.Map;

public class DeleteCellTypeAction extends ActionSupport implements SessionAware {
    Map<String, Object> userSession;

    @Override
    public void setSession(Map<String, Object> map) {
        userSession = map;
    }

    private JsonCellType data;

    public JsonCellType getData() {
        return data;
    }

    public void setData(JsonCellType data) {
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
                answer = ServiceUtil.getDeleteService().tryDelete(data, userSession, CellType.class);
                return SUCCESS;
            }
        }
        answer = "Нет доступа.";
        return SUCCESS;
    }
}
