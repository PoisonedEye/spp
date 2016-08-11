package util;

import services.*;


public class ServiceUtil {

    private static LoginService loginService = new LoginService();

    public static LoginService getLoginService() {
        return loginService;
    }

    private static EmployeeService employeeService = new EmployeeService();

    public static EmployeeService getEmployeeService() {
        return employeeService;
    }

    private static DataService dataService = new DataService();

    public static DataService getDataService() {
        return dataService;
    }

    private static UpdateService updateService = new UpdateService();

    public static UpdateService getUpdateService() {
        return updateService;
    }

    private static DeleteService deleteService = new DeleteService();

    public static DeleteService getDeleteService() {
        return deleteService;
    }

    private static CreateService createService = new CreateService();

    public static CreateService getCreateService() {
        return createService;
    }
}
