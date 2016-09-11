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
            throw new ClassCastException(name+"can't be empty.");
        if (string.length() > maxSize)
            throw new ClassCastException(name+" doesn't may contain more than "+maxSize+"symbols.");
        return string;
    }
    public static String getTime(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        try {
            sdf.parse(time);
        } catch (ParseException e) {
            throw new ClassCastException(
                    "Time must be valid and must follow the format yyyy-MM-dd HH:mm:ss.");
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
                    "Time must be valid and must follow the format yyyy-MM-dd HH:mm:ss.");
        }
        return lastTime;
    }
    public static Object getForeign(String id, Session session, String error,Class type){
        int i;
        try{
            i = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            throw new ClassCastException("The identifier has to be number.");
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
                throw new ClassCastException("The unknown dependent entity is specified.");
            return result;
        } catch (NumberFormatException ex) {
            throw new ClassCastException("Related entity is not selected.");
        }
    }
    //EMPLOYEE
    public static long getEmployeeTin(String tin) {
        try {
            Long newTin = Long.parseLong(tin);
            if (newTin < 100000000000L || newTin >= 1000000000000L)
                throw new ClassCastException("TIN has to be 12-digit positive number.");
            return newTin;
        } catch (NumberFormatException ex) {
            throw new ClassCastException("TIN has to be number.");
        }
    }
    public static String getEmployeeFullName(String fullName) {
        if (fullName.length() == 0)
            throw new ClassCastException("The name can't be empty.");
        if (fullName.length() > 64)
            throw new ClassCastException("The name has to contain up to 65 symbols.");
        return fullName;
    }
    public static String getEmployeeTakingOffice(String takingOffice) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(takingOffice);
        } catch (ParseException e) {
            throw new ClassCastException(
                    "Date must be valid and must follow the format YYYY-MM-DD.");
        }
        return takingOffice;
    }
    public static String getEmployeePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 0)
            throw new ClassCastException("The phone number can't be empty.");
        if (phoneNumber.length() > 20)
            throw new ClassCastException("The phone number has to contain to the 21 symbol.");
        return phoneNumber;
    }
    public static String getEmployeeLogin(String login, Session session) {
        Object another = session.createCriteria(Employee.class).add(Restrictions.eq("login", login)).uniqueResult();
        if (another != null)
            throw new ClassCastException("Login has to be unique.");
        if (login.length() == 0)
            throw new ClassCastException("Login can't be empty.");
        if (login.length() > 32)
            throw new ClassCastException("Login has to contain to the 33 of symbols.");
        return login;
    }
    //POSITION
    public static String getPositionName(String name, Session session) {
        if (name.length() == 0)
            throw new ClassCastException("Name can't be empty.");
        Object another = session.createCriteria(Position.class).add(Restrictions.eq("name", name)).uniqueResult();
        if (another != null)
            throw new ClassCastException("Name has to be unique.");
        if (name.length() > 32)
            throw new ClassCastException("Name has to contain to the 33 of symbols.");
        return name;
    }
    //ADDRESS
    public static short getAddressFlat(String flat) {
        try {
            Short newFlat = Short.parseShort(flat);
            if (newFlat < 0)
                throw new ClassCastException("The flat number can't be negative");
            return newFlat;
        } catch (NumberFormatException ex) {
            throw new ClassCastException("TIN has to be number.");
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
        String error = "Number doesn't match (e.g. 1-A-10).";
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
                throw new ClassCastException("Coordinates should be in the range [-1000,1000]");
            }
            return f;
        } catch (NumberFormatException ex) {
            throw new ClassCastException("Coordinates must be numbers (point as a separator).");
        }

    }
    //CELL TYPE
    public static Integer getValue(String value){
        try {
            int x = Integer.parseInt(value);
            if (x <= 0)
                throw new ClassCastException("The value should be greater than zero.");
            return x;
        } catch (NumberFormatException ex) {
            throw new ClassCastException("Coordinates must be numbers (point as a separator).");
        }
    }
    //CELL VISITING
    public static Product getCellVisitingProduct(String id, Session session){
        int i;
        try{
            i = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            throw new ClassCastException("The identifier must be a number.");
        }
        Object product = session.createCriteria(Product.class)
                .add(Restrictions.eq("id", i)).uniqueResult();
        if(product == null)
            throw new ClassCastException("NThis item does not exist.");
        return (Product) product;
    }
    public static AcceptorShift getCellVisitingShift(String id, Session session){
        int i;
        try{
            i = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            throw new ClassCastException("The identifier has to be number.");
        }
        Object shift = session.createCriteria(AcceptorShift.class)
                .add(Restrictions.eq("id", i)).uniqueResult();
        if(shift == null)
            throw new ClassCastException("Specified shift doesn't exist");
        return (AcceptorShift)shift;
    }
    //PRODUCT TYPE
    public static long getBarcode(String code){
        try {
            Long newCode = Long.parseLong(code);
            if (newCode <= 0)
                throw new ClassCastException("The barcode can not be less than or equal to 0.");
            return newCode;
        } catch (NumberFormatException ex) {
            throw new ClassCastException("The bar code has to be number.");
        }
    }
}
