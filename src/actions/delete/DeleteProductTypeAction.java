package actions.delete;

import com.opensymphony.xwork2.ActionSupport;
import entities.ProductType;
import jsonEntities.JsonProductType;
import org.apache.struts2.interceptor.SessionAware;
import util.ServiceUtil;

import java.util.Map;

public class DeleteProductTypeAction extends ActionSupport implements SessionAware {
    Map<String, Object> userSession;

    @Override
    public void setSession(Map<String, Object> map) {
        userSession = map;
    }

    private JsonProductType data;

    public JsonProductType getData() {
        return data;
    }

    public void setData(JsonProductType data) {
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
                answer = ServiceUtil.getDeleteService().tryDelete(data, userSession, ProductType.class);
                return SUCCESS;
            }
        }
        answer = "Access denied.";
        return SUCCESS;
    }
}
