package actions;

import com.opensymphony.xwork2.ActionSupport;
import entities.*;
import org.apache.struts2.interceptor.SessionAware;
import util.ServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetDataAction extends ActionSupport implements SessionAware {

    private Map<String, Object> userSession;
    @Override
    public void setSession(Map<String, Object> map) {
        userSession = map;
    }

    public String dataEmployees() {
        data(Employee.class);
        return SUCCESS;
    }
    public String dataPositions() {
        data(Position.class);
        return SUCCESS;
    }
    public String dataAcceptorShifts() {
        data(AcceptorShift.class);
        return SUCCESS;
    }
    public String dataAddresses() {
        data(Address.class);
        return SUCCESS;
    }
    public String dataCells(){
        data(Cell.class);
        return SUCCESS;
    }
    public String dataCellTypes(){
        data(CellType.class);
        return SUCCESS;
    }
    public String dataCellVisitings(){
        data(CellVisiting.class);
        return SUCCESS;
    }
    public String dataCompanies(){
        data(Company.class);
        return SUCCESS;
    }
    public String dataProducts(){
        data(Product.class);
        return SUCCESS;
    }
    public String dataProductTypes(){
        data(ProductType.class);
        return SUCCESS;
    }
    public String dataRecievings(){
        data(Recieving.class);
        return SUCCESS;
    }
    public String dataTransfers(){
        data(Transfer.class);
        return SUCCESS;
    }
    public String dataUnitSets(){
        data(UnitSet.class);
        return SUCCESS;
    }
    public String dataAcceptors(){
        data = ServiceUtil.getEmployeeService().getAcceptors();
        return SUCCESS;
    }
    public List getData() {
        return data;
    }
    public void setData(List data) {
        this.data = data;
    }
    List data;

    public void data(Class type){
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            data = new ArrayList<>();
            List list = ServiceUtil.getDataService().getData(type);
            for (Object e : list) {
                data.add(e);
            }
        }
    }
}
