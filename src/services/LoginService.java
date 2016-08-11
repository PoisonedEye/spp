package services;

import entities.Employee;
import util.ServiceUtil;
import java.util.Map;

public class LoginService {

    public boolean Login(String login, String password, Map<String, Object> session) {
        Object isLogined = session.get("logined");
        if (isLogined != null)
            if ((Boolean) isLogined) {
                session.put("loginFailedMessage", "Вход в систему уже выполнен.");
                return false;
            }
        // try to get employee with selected login
        Employee employee = ServiceUtil.getEmployeeService().getEmployeeByLogin(login);

        // no user with this login
        if (employee == null) {
            session.put("loginFailedMessage", "Такой логин не существует.");
            return false;
        }
        // password is ok
        if (employee.getPassword().equals(password)) {
            session.put("logined", true);
            session.put("fullName", employee.getFullName());
            session.put("position", employee.getPosition().getName());
            session.put("id", employee.getId());
            session.put("loginFailedMessage", "Вход прошел успешно. Для устранения неполадок обратитесь к администратору.");
            return true;
        }
        // password is invalid
        session.put("loginFailedMessage", "Неверный пароль.");
        return false;
    }

    public boolean isLogined(Map<String, Object> session) {
        Object isLogined = session.get("logined");
        if (isLogined != null)
            if ((Boolean) isLogined)
                return true;
        return false;
    }

    public boolean Logout(Map<String, Object> session) {
        Object isLogined = session.get("logined");
        if (isLogined != null)
            if ((Boolean) isLogined) {
                session.put("logined", false);
                return true;
            }
        return false;
    }

}
