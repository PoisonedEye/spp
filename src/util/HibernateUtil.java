package util;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import stubs.SessionFactoryStub;

public class HibernateUtil {

    public static boolean isTesting() {
        return testing;
    }
    public static void setTesting(boolean testing) {
        HibernateUtil.testing = testing;
    }
    public static boolean testing;

    private static final SessionFactory sessionFactory
            = configureSessionFactory();
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory configureSessionFactory()
            throws HibernateException {
        if (!testing) {
            Configuration configuration = new Configuration().configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).buildServiceRegistry();
            return configuration.buildSessionFactory(serviceRegistry);
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        if (!testing)
            return sessionFactory;
        else
            return new SessionFactoryStub();
    }
}
