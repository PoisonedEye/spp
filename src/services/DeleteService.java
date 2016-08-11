package services;

import entities.Employee;
import jsonEntities.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import util.HibernateUtil;
import java.util.Map;

public class DeleteService {
    public String tryDeleteEmployee(JsonEmployee json, Map<String, Object> session) {
        if (!session.get("position").equals("Администратор")) {
            return "Для удаления записи обратитесь к администратору.";
        }
        int targetId = Integer.parseInt(json.getId());
        if (session.get("id").equals(targetId))
            return "Нельзя удалить самого себя:)";
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(Employee.class).add(Restrictions.eq("id", targetId));
        Employee employee = (Employee) criteria.uniqueResult();
        if (employee == null)
            return "Этот пользователь уже удален.";
        dbSession.delete(employee);
        try {
            tx.commit();
        } catch (ConstraintViolationException ex) {
            return "Пользователь не может быть удален, т.к. в используется в других сущностях базы данных.";
        }
        return "Пользователь успешно удален.";
    }
    public String tryDelete(JsonBase json, Map<String, Object> session, Class type){
        if (!session.get("position").equals("Администратор"))
            return "Для удаления записи обратитесь к администратору.";
        int id = Integer.parseInt(json.getId());
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(type).add(Restrictions.eq("id", id));
        Object entity = criteria.uniqueResult();
        if (entity == null)
            return "Эта запись уже удалена.";
        dbSession.delete(entity);
        try {
            tx.commit();
        } catch (ConstraintViolationException ex) {
            return "Запись не может быть удалена, т.к. в используется в других сущностях базы данных.";
        }
        return "Запись успешно удалена.";
    }
}
