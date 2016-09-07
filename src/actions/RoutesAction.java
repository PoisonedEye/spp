package actions;

import util.ServiceUtil;

public class RoutesAction extends BaseAction {
    public String execute() {
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Acceptor")){
                return SUCCESS;
            }
        }
        return "denied";
    }
}
