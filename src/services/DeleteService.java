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
        if (!session.get("position").equals("Administrator")) {
            return "To delete record, contact the administrator.";
        }
        int targetId = Integer.parseInt(json.getId());
        if (session.get("id").equals(targetId))
            return "You can not remove yourself :)";
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(Employee.class).add(Restrictions.eq("id", targetId));
        Employee employee = (Employee) criteria.uniqueResult();
        if (employee == null)
            return "This user has deleted.";
        dbSession.delete(employee);
        try {
            tx.commit();
        } catch (ConstraintViolationException ex) {
            return "The user can not be removed, because it is used in other database entities.";
        }
        return "User successfully deleted.";
    }
    public String tryDelete(JsonBase json, Map<String, Object> session, Class type){
        if (!session.get("position").equals("Administrator"))
            return "To delete record, contact the administrator.";
        int id = Integer.parseInt(json.getId());
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(type).add(Restrictions.eq("id", id));
        Object entity = criteria.uniqueResult();
        if (entity == null)
            return "This entry has been removed.";
        dbSession.delete(entity);
        try {
            tx.commit();
        } catch (ConstraintViolationException ex) {
            return "Record can not be removed, because it is used in other database entities.";
        }
        return "Record deleted successfully.";
    }
}
