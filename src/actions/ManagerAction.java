package actions;

import util.ServiceUtil;

public class ManagerAction extends BaseAction {
    public String execute() {
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Manager")){
                return SUCCESS;
            }
        }
        return "denied";
    }
}
