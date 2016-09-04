package services;

import entities.*;
import jsonEntities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
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
                return "Пароль не может быть пустым";
            }
            employee.setPassword(json.getPassword());
            Position position = (Position)ParsingService.getById(json.getPosition(), dbSession,Position.class);
            employee.setPosition(position);
            if (employee.getPosition().getName().equals("Администратор") ^ position.getName().equals("Администратор")) {
                return "Назначение и снятие должности 'Администратор' доступно только самим администраторам.";
            }
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }


        employee.setFired(json.getFired());
        dbSession.save(employee);
        tx.commit();
        return "Сотрудник успешно добавлен.";
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
        return "Должность успешно добавлена.";
    }
    public String tryCreateAcceptorShift(JsonAcceptorShift json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        AcceptorShift acceptorShift = new AcceptorShift();
        try {
            acceptorShift.setAcceptor((Employee) ParsingService.getById(json.getAcceptor(),dbSession,Employee.class));
            acceptorShift.setBegining(ParsingService.getTime(json.getBegining()));
            String error = "Смена не может оканчиваться до ее начала";
            acceptorShift.setEnding(ParsingService.getLastTime(json.getEnding(),json.getBegining(),error));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(acceptorShift);
        tx.commit();
        return "Смена успешно добавлена.";
    }
    public String tryCreateAddress(JsonAddress json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Address address = new Address();
        try {
            address.setCity(ParsingService.getString(json.getCity(),"Город",128));
            address.setStreet(ParsingService.getString(json.getStreet(),"Улица",128));
            address.setHouse(ParsingService.getString(json.getHouse(),"Дом",20));
            address.setFlat(ParsingService.getAddressFlat(json.getFlat()));
            address.setEmployee((Employee) ParsingService.getById(json.getEmployee(),dbSession,Employee.class));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(address);
        tx.commit();
        return "Адрес успешно добавлен.";
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
        return "Ячейка успешно добавлена.";
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
        return "Вид ячейки успешно добавлен.";
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
        return "Посещение ячейки успешно добавлено.";
    }
    public String tryCreateCompany(JsonCompany json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Company company = new Company();
        try {
            company.setName(ParsingService.getString(json.getName(),"Имя компании",64));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(company);
        tx.commit();
        return "Компания успешно добавлена.";
    }

    public String tryCreateProduct(JsonProduct json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Product product = new Product();
        try {
            product.setFirstGetTime(ParsingService.getTime(json.getFirstGetTime()));
            if (json.getLastGetTime() != null && !json.getLastGetTime().equals("")){
                String error ="Продукт не может быть взят до того, как он был положен.";
                product.setLastGetTime(ParsingService.getLastTime(json.getLastGetTime(),json.getFirstGetTime(),error));
            }
            String error1 = "Указанной приемки не существует.";
            product.setRecieving((Recieving) ParsingService.getForeign
                    (json.getRecieving(),dbSession,error1,Recieving.class));
            String error2 = "Указанной передачи не существует.";
            product.setTransfer((Transfer) ParsingService.getForeign
                    (json.getTransfer(),dbSession,error2,Transfer.class));
            product.setProductType((ProductType)ParsingService.getById(json.getProductType(),dbSession,ProductType.class));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(product);
        tx.commit();
        return "Товар успешно добавлен.";
    }
    public String tryCreateProductType(JsonProductType json, Map<String, Object> session){
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
        return "Приемка успешно добавлена.";
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
        return "Передача успешно добавлена.";
    }
    public String tryCreateUnitSet(JsonUnitSet json, Map<String, Object> session){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        UnitSet unitSet = new UnitSet();
        try {
            unitSet.setDistance(ParsingService.getString(json.getDistance(),"Имя еденицы измерения",16));
            unitSet.setWeight(ParsingService.getString(json.getWeight(),"Имя еденицы измерения",16));
        } catch (ClassCastException ex) {
            return ex.getMessage();
        }
        dbSession.save(unitSet);
        tx.commit();
        return "Еденицы измерения успешно добавлены.";
    }

}
