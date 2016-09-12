package services;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class DataService {

    public List getData(Class type) {
        if (type == null)
            return new ArrayList<>();
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
        if (type == null)
            return null;
        if (session ==null)
            return null;
        return session.createCriteria(type).add(Restrictions.eq("id",id)).uniqueResult();
    }
}
