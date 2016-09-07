package actions;

import util.ServiceUtil;

public class NewReceivingAction extends BaseAction {
    public String execute() {
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Storekeeper")){
                return SUCCESS;
            }
        }
        return "denied";
    }
}
