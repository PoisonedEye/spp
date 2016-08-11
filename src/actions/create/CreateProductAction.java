package actions.create;

import com.opensymphony.xwork2.ActionSupport;
import jsonEntities.JsonEmployee;
import jsonEntities.JsonProduct;
import org.apache.struts2.interceptor.SessionAware;
import util.ServiceUtil;

import java.util.Map;

public class CreateProductAction extends ActionSupport implements SessionAware {
    Map<String, Object> userSession;

    @Override
    public void setSession(Map<String, Object> map) {
        userSession = map;
    }

    private JsonProduct data;

    public JsonProduct getData() {
        return data;
    }

    public void setData(JsonProduct data) {
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
                answer = ServiceUtil.getCreateService().tryCreateProduct(data, userSession);
                return SUCCESS;
            }
        }
        answer = "Нет доступа.";
        return SUCCESS;
    }
}
