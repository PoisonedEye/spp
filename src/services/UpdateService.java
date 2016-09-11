package services;

import entities.*;
import jsonEntities.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.util.Map;

public class UpdateService {
    public String tryUpdateEmployee(JsonEmployee json, Map<String, Object> session) {
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(Employee.class).add(Restrictions.eq("id", id));
        Employee employee = (Employee) criteria.uniqueResult();
        try {
            Position position = (Position)ParsingService.getById(json.getPosition(), dbSession,Position.class);
            employee.setTin(ParsingService.getEmployeeTin(json.getTin()));
            employee.setFullName(ParsingService.getEmployeeFullName(json.getFullName()));
            employee.setTakingOffice(ParsingService.getEmployeeTakingOffice(json.getTakingOffice()));
            employee.setPhoneNumber(ParsingService.getEmployeePhoneNumber(json.getPhoneNumber()));
            if (!employee.getLogin().equals(json.getLogin()))
                employee.setLogin(ParsingService.getEmployeeLogin(json.getLogin(), dbSession));
            if (session.get("position").equals("Administrator")) {
                if ((int) session.get("id") == id && !position.getName().equals("Administrator")) {
                    return "Only other administrator can change the administrator's position.";
                }
            } else {
                if (employee.getPosition().getName().equals("Administrator") ^ position.getName().equals("Administrator")) {
                    return "Appointment and removal of positions 'Administrator' available only by administrators.";
                }
            }
            if (json.getPassword() != null)
                employee.setPassword(json.getPassword());
            employee.setPosition(position);
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        employee.setFired(json.getFired());
        tx.commit();
        return "Successfully updated.";
    }
    public String tryUpdatePosition(JsonPosition json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(Position.class).add(Restrictions.eq("id", id));
        Position position = (Position) criteria.uniqueResult();
        try {
            if (!json.getName().equals(position.getName()))
                position.setName(ParsingService.getPositionName(json.getName(), dbSession));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        tx.commit();
        return "Successfully updated.";
    }
    public String tryUpdateAcceptorShift(JsonAcceptorShift json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(AcceptorShift.class).add(Restrictions.eq("id", id));
        AcceptorShift acceptorShift = (AcceptorShift) criteria.uniqueResult();
        try {
            acceptorShift.setAcceptor((Employee) ParsingService.getById(json.getAcceptor(),dbSession,Employee.class));
            acceptorShift.setBegining(ParsingService.getTime(json.getBegining()));
            String error = "Shift can not be terminated before it's start";
            acceptorShift.setEnding(ParsingService.getLastTime(json.getEnding(),json.getBegining(),error));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        tx.commit();
        return "The transfer successfully updated.";
    }
    public String tryUpdateAddress(JsonAddress json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(Address.class).add(Restrictions.eq("id", id));
        Address address = (Address) criteria.uniqueResult();
        try {
            address.setCity(ParsingService.getString(json.getCity(),"City",128));
            address.setStreet(ParsingService.getString(json.getStreet(),"Street",128));
            address.setHouse(ParsingService.getString(json.getHouse(),"House",20));
            address.setFlat(ParsingService.getAddressFlat(json.getFlat()));
            address.setEmployee((Employee) ParsingService.getById(json.getEmployee(),dbSession,Employee.class));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        tx.commit();
        return "Successfully updated.";
    }
    public String tryUpdateCell(JsonCell json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(Cell.class).add(Restrictions.eq("id", id));
        Cell cell = (Cell) criteria.uniqueResult();
        try {
            cell.setNumber(ParsingService.getPositionName(json.getNumber(), dbSession));
            cell.setX(ParsingService.getCoordinate(json.getX()));
            cell.setY(ParsingService.getCoordinate(json.getY()));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        tx.commit();
        return "Successfully updated.";
    }
    public String tryUpdateCellType(JsonCellType json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(CellType.class).add(Restrictions.eq("id", id));
        CellType cellType = (CellType) criteria.uniqueResult();
        try {
            cellType.setDepth(ParsingService.getValue(json.getDepth()));
            cellType.setHeight(ParsingService.getValue(json.getHeight()));
            cellType.setMaxWeight(ParsingService.getValue(json.getMaxWeight()));
            cellType.setWidth(ParsingService.getValue(json.getWidth()));
            int set = Integer.parseInt(json.getUnitSet());
            cellType.setUnitSet((UnitSet) DataService.getById(UnitSet.class,set,dbSession));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        tx.commit();
        return "Successfully updated.";
    }
    public String tryUpdateCellVisiting(JsonCellVisiting json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(CellVisiting.class).add(Restrictions.eq("id", id));
        CellVisiting cellVisiting = (CellVisiting) criteria.uniqueResult();
        try {
            cellVisiting.setCell((Cell)ParsingService.getById(json.getCell(),dbSession,Cell.class));
            cellVisiting.setProduct(ParsingService.getCellVisitingProduct(json.getProduct(),dbSession));
            cellVisiting.setProductTaken(json.getProductTaken());
            cellVisiting.setShift(ParsingService.getCellVisitingShift(json.getShift(),dbSession));
            cellVisiting.setTime(ParsingService.getTime(json.getTime()));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        tx.commit();
        return "Successfully updated.";
    }
    public String tryUpdateCompany(JsonCompany json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(Company.class).add(Restrictions.eq("id", id));
        Company company = (Company) criteria.uniqueResult();
        try {
            company.setName(ParsingService.getString(json.getName(),"Company name",64));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        tx.commit();
        return "Company successfully updated.";
    }
    public String tryUpdateProduct(JsonProduct json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(Product.class).add(Restrictions.eq("id", id));
        Product product = (Product) criteria.uniqueResult();
        try {
            product.setFirstGetTime(ParsingService.getTime(json.getFirstGetTime()));
            if (json.getLastGetTime() != null && !json.getLastGetTime().equals("")){
                String error ="The product can't be taken before it has been put.";
                product.setLastGetTime(ParsingService.getLastTime(json.getLastGetTime(),json.getFirstGetTime(),error));
            }
            String error1 = "The specified receiving does not exist.";
            product.setRecieving((Recieving) ParsingService.getForeign
                    (json.getRecieving(),dbSession,error1,Recieving.class));
            String error2 = "The specified transfer does not exist.";
            product.setTransfer((Transfer) ParsingService.getForeign
                    (json.getTransfer(),dbSession,error2,Transfer.class));
            product.setProductType((ProductType)ParsingService.getById(json.getProductType(),dbSession,ProductType.class));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        tx.commit();
        return "Product successfully updated.";
    }
    public String tryUpdateProductType(JsonProductType json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(ProductType.class).add(Restrictions.eq("id", id));
        ProductType productType = (ProductType) criteria.uniqueResult();
        try {
            productType.setFullModelName(ParsingService.getString(json.getFullModelName(),"Model name", 64));
            productType.setBarcode(ParsingService.getBarcode(json.getBarcode()));
            productType.setModel(ParsingService.getString(json.getModel(),"Model",64));
            productType.setPackHeight(ParsingService.getValue(json.getPackHeight()));
            productType.setPackLength(ParsingService.getValue(json.getPackLength()));
            productType.setPackWidth(ParsingService.getValue(json.getPackWidth()));
            productType.setProducer(ParsingService.getString(json.getProducer(),"Producer",64));
            int setId = Integer.parseInt(json.getUnitSet());
            productType.setUnitSet((UnitSet) DataService.getById(UnitSet.class,setId,dbSession));
            productType.setWeight(ParsingService.getValue(json.getWeight()));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        tx.commit();
        return "Product successfully updated.";
    }
    public String tryUpdateRecieving(JsonRecieving json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(Recieving.class).add(Restrictions.eq("id", id));
        Recieving recieving = (Recieving) criteria.uniqueResult();
        try {
            recieving.setTime(ParsingService.getTime(json.getTime()));
            int company = Integer.parseInt(json.getCompany());
            recieving.setCompany((Company)DataService.getById(Company.class,company,dbSession));
            recieving.setReciever((Employee)ParsingService.getById(json.getReciever(),dbSession,Employee.class));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        tx.commit();
        return "Receiving successfully updated.";
    }
    public String tryUpdateTransfer(JsonTransfer json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(Transfer.class).add(Restrictions.eq("id", id));
        Transfer transfer = (Transfer) criteria.uniqueResult();
        try {
            transfer.setTime(ParsingService.getTime(json.getTime()));
            int company = Integer.parseInt(json.getCompany());
            transfer.setCompany((Company)DataService.getById(Company.class,company,dbSession));
            transfer.setTransferer((Employee)ParsingService.getById(json.getTransferer(),dbSession,Employee.class));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        tx.commit();
        return "Transfer successfully updated.";
    }
    public String tryUpdateUnitSet(JsonUnitSet json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        int id = Integer.parseInt(json.getId());
        Criteria criteria = dbSession.createCriteria(UnitSet.class).add(Restrictions.eq("id", id));
        UnitSet unitSet = (UnitSet) criteria.uniqueResult();
        try {
            unitSet.setDistance(ParsingService.getString(json.getDistance(),"Units name",16));
            unitSet.setWeight(ParsingService.getString(json.getWeight(),"Units name",16));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        tx.commit();
        return "Units successfully added.";
    }
}
