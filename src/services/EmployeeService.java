package services;

import entities.Employee;
import entities.Position;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by konst on 4/19/2016.
 */
public class EmployeeService {

    public Employee getEmployeeByLogin(String login) {
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(Employee.class);
        try {
            return (Employee) criteria.add(Restrictions.eq("login", login)).uniqueResult();
        } finally {
            dbSession.close();
        }
    }

    public Employee getEmployeeByTin(long tin) {
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(Employee.class).add(Restrictions.eq("tin", tin));
        try {
            return (Employee) criteria.uniqueResult();
        } finally {
            dbSession.close();
        }
    }

    public List getAcceptors(){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        dbSession.beginTransaction();
        Position position = (Position) dbSession.createCriteria(Position.class).
                add(Restrictions.eq("name", "Acceptor")).uniqueResult();
        Criteria criteria = dbSession.createCriteria(Employee.class).add(Restrictions.eq("position", position));
        try {
            return criteria.list();
        } finally {
            dbSession.close();
        }
    }
}
