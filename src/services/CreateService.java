package services;

import entities.*;
import jsonEntities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
import java.util.Map;

public class CreateService {
    public String tryCreateEmployee(JsonEmployee json, Map<String, Object> session) {
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Employee employee = new Employee();
        try {
            employee.setFullName(ParsingService.getEmployeeFullName(json.getFullName()));
            employee.setTakingOffice(ParsingService.getEmployeeTakingOffice(json.getTakingOffice()));
            employee.setPhoneNumber(ParsingService.getEmployeePhoneNumber(json.getPhoneNumber()));
            employee.setTin(ParsingService.getEmployeeTin(json.getTin()));
            employee.setLogin(ParsingService.getEmployeeLogin(json.getLogin(), dbSession));
            if (json.getPassword() == null) {
                return "Password can't be empty.";
            }
            employee.setPassword(json.getPassword());
            Position position = (Position)ParsingService.getById(json.getPosition(), dbSession,Position.class);
            employee.setPosition(position);
            if (employee.getPosition().getName().equals("Administrator") ^ position.getName().equals("Administrator")) {
                return "Appointment and removal of positions 'Administrator' available only by administrators.";
            }
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }

        employee.setFired(json.getFired());
        employee.setBusy(json.getBusy());
        dbSession.save(employee);
        tx.commit();
        return "Employee successfully added";
    }

    public String tryCreatePosition(JsonPosition json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Position position = new Position();
        try {
            position.setName(ParsingService.getPositionName(json.getName(),dbSession));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(position);
        tx.commit();
        return "Position successfully added";
    }
    public String tryCreateAcceptorShift(JsonAcceptorShift json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        AcceptorShift acceptorShift = new AcceptorShift();
        try {
            acceptorShift.setAcceptor((Employee) ParsingService.getById(json.getAcceptor(),dbSession,Employee.class));
            acceptorShift.setBegining(ParsingService.getTime(json.getBegining()));
            String error = "Shift can not be terminated before it's start";
            acceptorShift.setEnding(ParsingService.getLastTime(json.getEnding(),json.getBegining(),error));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(acceptorShift);
        tx.commit();
        return "Shift successfully added";
    }
    public String tryCreateAddress(JsonAddress json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Address address = new Address();
        try {
            address.setCity(ParsingService.getString(json.getCity(),"City",128));
            address.setStreet(ParsingService.getString(json.getStreet(),"Street",128));
            address.setHouse(ParsingService.getString(json.getHouse(),"House",20));
            address.setFlat(ParsingService.getAddressFlat(json.getFlat()));
            address.setEmployee((Employee) ParsingService.getById(json.getEmployee(),dbSession,Employee.class));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(address);
        tx.commit();
        return "Address successfully added ";
    }
    public String tryCreateCell(JsonCell json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Cell cell = new Cell();
        try {
            cell.setCellType(ParsingService.getCellType(json.getCellType(),dbSession));
            cell.setNumber(ParsingService.getCellNumber(json.getNumber()));
            cell.setCellType((CellType) ParsingService.getById(json.getCellType(),dbSession,CellType.class));
            cell.setX(ParsingService.getCoordinate(json.getX()));
            cell.setY(ParsingService.getCoordinate(json.getY()));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(cell);
        tx.commit();
        return "Cell successfully added";
    }
    public String tryCreateCellType(JsonCellType json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        CellType cellType = new CellType();
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
        dbSession.save(cellType);
        tx.commit();
        return "Cell type has been successfully added";
    }
    public String tryCreateCellVisiting(JsonCellVisiting json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        CellVisiting cellVisiting = new CellVisiting();
        try {
            cellVisiting.setCell((Cell)ParsingService.getById(json.getCell(),dbSession,Cell.class));
            cellVisiting.setProduct(ParsingService.getCellVisitingProduct(json.getProduct(),dbSession));
            cellVisiting.setProductTaken(json.getProductTaken());
            cellVisiting.setShift(ParsingService.getCellVisitingShift(json.getShift(),dbSession));
            cellVisiting.setTime(ParsingService.getTime(json.getTime()));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(cellVisiting);
        tx.commit();
        return "Visit cells successfully added";
    }
    public String tryCreateCompany(JsonCompany json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Company company = new Company();
        try {
            company.setName(ParsingService.getString(json.getName(),"Company name",64));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(company);
        tx.commit();
        return "Company successfully added";
    }

    public String tryCreateProduct(JsonProduct json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Product product = new Product();
        try {
            product.setFirstGetTime(ParsingService.getTime(json.getFirstGetTime()));
            if (json.getLastGetTime() != null && !json.getLastGetTime().equals("")){
                String error ="The product can be taken before it was laid.";
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
        dbSession.save(product);
        tx.commit();
        return "Product successfully added.";
    }
    public String tryCreateProductType(JsonProductType json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        ProductType productType = new ProductType();
        try {
            productType.setFullModelName(ParsingService.getString(json.getFullModelName(),"Model name", 64));
            productType.setBarcode(ParsingService.getBarcode(json.getBarcode()));
            productType.setModel(ParsingService.getString(json.getModel(),"Model",64));
            productType.setPackHeight(ParsingService.getValue(json.getPackHeight()));
            productType.setPackLength(ParsingService.getValue(json.getPackLength()));
            productType.setPackWidth(ParsingService.getValue(json.getPackWidth()));
            productType.setProducer(ParsingService.getString(json.getProducer(),"Producer",64));
            int id = Integer.parseInt(json.getUnitSet());
            productType.setUnitSet((UnitSet) DataService.getById(UnitSet.class,id,dbSession));
            productType.setWeight(ParsingService.getValue(json.getWeight()));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(productType);
        tx.commit();
        return "Product type successfully added.";
    }
    public String tryCreateProductTypeWithId(JsonProductType json, Map<String, Object> session, List<Integer> ids){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        ProductType productType = new ProductType();
        try {
            productType.setFullModelName(ParsingService.getString(json.getFullModelName(),"Название модели", 64));
            productType.setBarcode(ParsingService.getBarcode(json.getBarcode()));
            productType.setModel(ParsingService.getString(json.getModel(),"Модель",64));
            productType.setPackHeight(ParsingService.getValue(json.getPackHeight()));
            productType.setPackLength(ParsingService.getValue(json.getPackLength()));
            productType.setPackWidth(ParsingService.getValue(json.getPackWidth()));
            productType.setProducer(ParsingService.getString(json.getProducer(),"Производитель",64));
            int id = Integer.parseInt(json.getUnitSet());
            productType.setUnitSet((UnitSet) DataService.getById(UnitSet.class,id,dbSession));
            productType.setWeight(ParsingService.getValue(json.getWeight()));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(productType);
        ids.add(productType.getId());
        tx.commit();
        return "Вид товара успешно добавлен.";
    }
    public String tryCreateRecieving(JsonRecieving json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Recieving recieving = new Recieving();
        try {
            recieving.setTime(ParsingService.getTime(json.getTime()));
            int company = Integer.parseInt(json.getCompany());
            recieving.setCompany((Company)DataService.getById(Company.class,company,dbSession));
            recieving.setReciever((Employee)ParsingService.getById(json.getReciever(),dbSession,Employee.class));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(recieving);
        tx.commit();
        return "Receiving successfully added.";
    }

    public String tryCreateTransfer(JsonTransfer json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Transfer transfer = new Transfer();
        try {
            transfer.setTime(ParsingService.getTime(json.getTime()));
            int company = Integer.parseInt(json.getCompany());
            transfer.setCompany((Company)DataService.getById(Company.class,company,dbSession));
            transfer.setTransferer((Employee)ParsingService.getById(json.getTransferer(),dbSession,Employee.class));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(transfer);
        tx.commit();
        return "Transfer successfully added.";
    }
    public String tryCreateUnitSet(JsonUnitSet json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        UnitSet unitSet = new UnitSet();
        try {
            unitSet.setDistance(ParsingService.getString(json.getDistance(),"Units name",16));
            unitSet.setWeight(ParsingService.getString(json.getWeight(),"Units name",16));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(unitSet);
        tx.commit();
        return "Units successfully added.";
    }

}
