package actions;

import util.ServiceUtil;

public class TasksAction extends BaseAction {
    public String execute() {
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Acceptor")){
                return SUCCESS;
            }
        }
        return "denied";
    }
}
