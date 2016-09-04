package services;

import entities.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ParsingService {
    //ALL
    public static String getString(String string, String name, int maxSize) {
        if (string.length() == 0)
            throw new ClassCastException(name+"не может быть пустым.");
        if (string.length() > maxSize)
            throw new ClassCastException(name+" не может содержать больше"+maxSize+"символов.");
        return string;
    }
    public static String getTime(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        try {
            sdf.parse(time);
        } catch (ParseException e) {
            throw new ClassCastException(
                    "Время должно быть допустимым и должно соответствовать формату yyyy-MM-dd HH:mm:ss.");
        }
        return time;
    }
    public static String getLastTime(String lastTime,String firstTime,String error){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        try {
            Date first = sdf.parse(firstTime);
            Date last = sdf.parse(lastTime);
            if (first.after(last))
                throw new ClassCastException(error);
        } catch (ParseException e) {
            throw new ClassCastException(
                    "Время должно быть допустимым и должно соответствовать формату yyyy-MM-dd HH:mm:ss.");
        }
        return lastTime;
    }
    public static Object getForeign(String id, Session session, String error,Class type){
        int i;
        try{
            i = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            throw new ClassCastException("Идентификатор должен быть числом.");
        }
        Object result = session.createCriteria(type)
                .add(Restrictions.eq("id", i)).uniqueResult();
        if(result == null)
            throw new ClassCastException(error);
        return result;
    }
    public static Object getById(String id, Session session, Class type){
        try {
            Integer newId = Integer.parseInt(id);
            Criteria c = session.createCriteria(type);
            c.add(Restrictions.eq("id", newId));
            Object result = c.uniqueResult();
            if (result == null)
                throw new ClassCastException("Указана неизвестная зависимая сущность.");
            return result;
        } catch (NumberFormatException ex) {
            throw new ClassCastException("Не выбрана связанная сущность.");
        }
    }
    //EMPLOYEE
    public static long getEmployeeTin(String tin) {
        try {
            Long newTin = Long.parseLong(tin);
            if (newTin < 100000000000L || newTin >= 1000000000000L)
                throw new ClassCastException("ИНН должен быть 12-тизначным положительным числом.");
            return newTin;
        } catch (NumberFormatException ex) {
            throw new ClassCastException("ИНН должен быть числом.");
        }
    }
    public static String getEmployeeFullName(String fullName) {
        if (fullName.length() == 0)
            throw new ClassCastException("Имя не может быть пустым.");
        if (fullName.length() > 64)
            throw new ClassCastException("Имя должно содержать до 65-ти символов.");
        return fullName;
    }
    public static String getEmployeeTakingOffice(String takingOffice) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(takingOffice);
        } catch (ParseException e) {
            throw new ClassCastException(
                    "Дата должна быть допустимой и должна соответствовать формату YYYY-MM-DD.");
        }
        return takingOffice;
    }
    public static String getEmployeePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 0)
            throw new ClassCastException("Номер телефона не может быть пустым.");
        if (phoneNumber.length() > 20)
            throw new ClassCastException("Номер телефона должен содержать до 21-го символа.");
        return phoneNumber;
    }
    public static String getEmployeeLogin(String login, Session session) {
        Object another = session.createCriteria(Employee.class).add(Restrictions.eq("login", login)).uniqueResult();
        if (another != null)
            throw new ClassCastException("Логин должен быть уникальным.");
        if (login.length() == 0)
            throw new ClassCastException("Логин не может быть пустым.");
        if (login.length() > 32)
            throw new ClassCastException("Логин должен содержать до 33-ех символов.");
        return login;
    }
    //POSITION
    public static String getPositionName(String name, Session session) {
        if (name.length() == 0)
            throw new ClassCastException("Название не может быть пустым.");
        Object another = session.createCriteria(Position.class).add(Restrictions.eq("name", name)).uniqueResult();
        if (another != null)
            throw new ClassCastException("Название должно быть уникальным.");
        if (name.length() > 32)
            throw new ClassCastException("Название должно содержать до 33-ех символов.");
        return name;
    }
    //ADDRESS
    public static short getAddressFlat(String flat) {
        try {
            Short newFlat = Short.parseShort(flat);
            if (newFlat < 0)
                throw new ClassCastException("Квартира не может быть отрицательной.");
            return newFlat;
        } catch (NumberFormatException ex) {
            throw new ClassCastException("ИНН должен быть числом.");
        }
    }
    //CELL
    public static CellType getCellType(String cellType, Session session){
        try {
            int id = Integer.parseInt(cellType);
            if (id <= 0)
                throw new ClassCastException("Cell type must be a positive number.");
            Object type = session.createCriteria(CellType.class).add(Restrictions.eq("id", id)).uniqueResult();
            if (type == null){
                throw new ClassCastException("Cell type with id " + id + " doesn't exist.");
            }
            return (CellType)type;
        }
        catch (NumberFormatException ex){
            throw new ClassCastException("Cell type must be a number.");
        }

    }
    public static String getCellNumber(String number){
        String error = "Номер не соответствует формату (например 1-A-10).";
        if (number.length() < 5)
            throw new ClassCastException(error);
        String[] parts = number.split("-");
        if (parts.length != 3)
            throw new ClassCastException(error);
        try {
            int a = Integer.parseInt(parts[0]);
            int c = Integer.parseInt(parts[2]);
            if (c <= 0 || a <= 0)
                throw new ClassCastException(error);
        }
        catch (NumberFormatException ex){
            throw new ClassCastException(error);
        }
        if (parts[1].length()!=1 || !Character.isUpperCase(parts[1].charAt(0)))
            throw new ClassCastException(error);
        return number;
    }
    public static Float getCoordinate(String x){
        try {
            float f = Float.parseFloat(x);
            if (f > 1000 || f < -1000){
                throw new ClassCastException("Координаты должны быть в диапазоне [-1000,1000]");
            }
            return f;
        } catch (NumberFormatException ex) {
            throw new ClassCastException("Координаты должны быть числами (точка в качестве разделителя).");
        }

    }
    //CELL TYPE
    public static Integer getValue(String value){
        try {
            int x = Integer.parseInt(value);
            if (x <= 0)
                throw new ClassCastException("Величина должна быть больше нуля.");
            return x;
        } catch (NumberFormatException ex) {
            throw new ClassCastException("Координаты должны быть числами (точка в качестве разделителя).");
        }
    }
    //CELL VISITING
    public static Product getCellVisitingProduct(String id, Session session){
        int i;
        try{
            i = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            throw new ClassCastException("Идентификатор должен быть числом.");
        }
        Object product = session.createCriteria(Product.class)
                .add(Restrictions.eq("id", i)).uniqueResult();
        if(product == null)
            throw new ClassCastException("Указанный товар не существует.");
        return (Product) product;
    }
    public static AcceptorShift getCellVisitingShift(String id, Session session){
        int i;
        try{
            i = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            throw new ClassCastException("Идентификатор должен быть числом.");
        }
        Object shift = session.createCriteria(AcceptorShift.class)
                .add(Restrictions.eq("id", i)).uniqueResult();
        if(shift == null)
            throw new ClassCastException("Указанная смена не существует.");
        return (AcceptorShift)shift;
    }
    //PRODUCT TYPE
    public static long getBarcode(String code){
        try {
            Long newCode = Long.parseLong(code);
            if (newCode <= 0)
                throw new ClassCastException("Штрихкод не может быть меньше или равен 0.");
            return newCode;
        } catch (NumberFormatException ex) {
            throw new ClassCastException("Штрихкод должен быть числом.");
        }
    }
}
