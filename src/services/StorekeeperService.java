package services;

import entities.Employee;
import entities.Position;
import jsonEntities.JsonProductType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.internal.Util;
import util.HibernateUtil;
import util.ServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StorekeeperService {

    public void beginNewReceiving(Integer[] acceptorIds, JsonProductType[] types,Map<String, Object> session) {
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(Employee.class);
        try {
            List<Integer> Ids = new ArrayList<Integer>();
            for (int i = 0; i < types.length; i++){
                ServiceUtil.getCreateService().tryCreateProductTypeWithId(types[i],session,Ids);
            }
            //return (Employee) criteria.add(Restrictions.eq("login", login)).uniqueResult();
        } finally {
            dbSession.close();
        }
    }

}
