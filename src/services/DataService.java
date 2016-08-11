package services;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.util.List;

public class DataService {

    public List getData(Class type) {
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(type);
        try {
            return criteria.list();
        } finally {
            dbSession.close();
        }
    }

    public static Object getById(Class type, int id, Session session){
        return session.createCriteria(type).add(Restrictions.eq("id",id)).uniqueResult();
    }
}
