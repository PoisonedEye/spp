package services;

import entities.Employee;
import util.ServiceUtil;
import java.util.Map;

public class LoginService {

    public String Login(String login, String password, Map<String, Object> session) {
        if (session == null)
            return "Session is null.";
        Object isLogined = session.get("logined");
        if (isLogined != null)
            if ((Boolean) isLogined) {
                return "Login is already executed.";
            }
        // try to get employee with selected login
        Employee employee = ServiceUtil.getEmployeeService().getEmployeeByLogin(login);

        // no user with this login
        if (employee == null) {
            return "Incorrect username or password";
        }
        // password is ok
        if (employee.getPassword().equals(password)) {
            session.put("logined", true);
            session.put("fullName", employee.getFullName());
            session.put("position", employee.getPosition().getName());
            session.put("id", employee.getId());
            return "true";
        }
        // password is invalid
        return "Incorrect username or password";
    }

    public boolean isLogined(Map<String, Object> session) {
        if (session == null)
            return false;
        Object isLogined = session.get("logined");
        if (isLogined != null)
            if ((Boolean) isLogined)
                return true;
        return false;
    }

    public String Logout(Map<String, Object> session) {
        if (session == null)
            return "Session is null.";
        Object isLogined = session.get("logined");
        if (isLogined != null)
            if ((Boolean) isLogined) {
                session.put("logined", false);
                return "true";
            }
        return "Not logined.";
    }

}
