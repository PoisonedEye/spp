package actions;

import util.ServiceUtil;

public class AdminAction extends BaseAction {
    public String execute() {
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Administrator")){
                return SUCCESS;
            }
        }
        return "denied";
    }
}
