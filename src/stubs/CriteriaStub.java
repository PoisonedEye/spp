package stubs;

import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.ResultTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Анастасия on 11.09.2016.
 */
public class CriteriaStub implements Criteria {

    public Object result;

    @Override
    public String getAlias() {
        return null;
    }

    @Override
    public Criteria setProjection(Projection projection) {
        return null;
    }

    @Override
    public Criteria add(Criterion criterion) {
        return this;
    }

    @Override
    public Criteria addOrder(Order order) {
        return null;
    }

    @Override
    public Criteria setFetchMode(String s, FetchMode fetchMode) throws HibernateException {
        return null;
    }

    @Override
    public Criteria setLockMode(LockMode lockMode) {
        return null;
    }

    @Override
    public Criteria setLockMode(String s, LockMode lockMode) {
        return null;
    }

    @Override
    public Criteria createAlias(String s, String s1) throws HibernateException {
        return null;
    }

    @Override
    public Criteria createAlias(String s, String s1, JoinType joinType) throws HibernateException {
        return null;
    }

    @Override
    public Criteria createAlias(String s, String s1, int i) throws HibernateException {
        return null;
    }

    @Override
    public Criteria createAlias(String s, String s1, JoinType joinType, Criterion criterion) throws HibernateException {
        return null;
    }

    @Override
    public Criteria createAlias(String s, String s1, int i, Criterion criterion) throws HibernateException {
        return null;
    }

    @Override
    public Criteria createCriteria(String s) throws HibernateException {
        return null;
    }

    @Override
    public Criteria createCriteria(String s, JoinType joinType) throws HibernateException {
        return null;
    }

    @Override
    public Criteria createCriteria(String s, int i) throws HibernateException {
        return null;
    }

    @Override
    public Criteria createCriteria(String s, String s1) throws HibernateException {
        return null;
    }

    @Override
    public Criteria createCriteria(String s, String s1, JoinType joinType) throws HibernateException {
        return null;
    }

    @Override
    public Criteria createCriteria(String s, String s1, int i) throws HibernateException {
        return null;
    }

    @Override
    public Criteria createCriteria(String s, String s1, JoinType joinType, Criterion criterion) throws HibernateException {
        return null;
    }

    @Override
    public Criteria createCriteria(String s, String s1, int i, Criterion criterion) throws HibernateException {
        return null;
    }

    @Override
    public Criteria setResultTransformer(ResultTransformer resultTransformer) {
        return null;
    }

    @Override
    public Criteria setMaxResults(int i) {
        return null;
    }

    @Override
    public Criteria setFirstResult(int i) {
        return null;
    }

    @Override
    public boolean isReadOnlyInitialized() {
        return false;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public Criteria setReadOnly(boolean b) {
        return null;
    }

    @Override
    public Criteria setFetchSize(int i) {
        return null;
    }

    @Override
    public Criteria setTimeout(int i) {
        return null;
    }

    @Override
    public Criteria setCacheable(boolean b) {
        return null;
    }

    @Override
    public Criteria setCacheRegion(String s) {
        return null;
    }

    @Override
    public Criteria setComment(String s) {
        return null;
    }

    @Override
    public Criteria addQueryHint(String s) {
        return null;
    }

    @Override
    public Criteria setFlushMode(FlushMode flushMode) {
        return null;
    }

    @Override
    public Criteria setCacheMode(CacheMode cacheMode) {
        return null;
    }

    @Override
    public List list() throws HibernateException {
        List list = new ArrayList<Object>();
        if (result != null)
            list.add(result);
        return list;
    }

    @Override
    public ScrollableResults scroll() throws HibernateException {
        return null;
    }

    @Override
    public ScrollableResults scroll(ScrollMode scrollMode) throws HibernateException {
        return null;
    }

    @Override
    public Object uniqueResult() throws HibernateException {
        return result;
    }
}
