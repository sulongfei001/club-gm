package com.sevenXnetworks.treasure.dao.impl;

import com.sevenXnetworks.treasure.dao.BaseDao;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Repository
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
    protected Logger log = Logger.getLogger(this.getClass());

    public Class<T> getEntityClass() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class) params[0];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    /*保存*/
    public void saveOrUpdate(T t) {
        currentSession().saveOrUpdate(t);
    }

    /**
     * @param hql
     * @param params
     * @return
     */
    public int executeUpdate(String hql, String[] params) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(hql);

        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        return query.executeUpdate();
    }

    /*删除*/
    public void delete(T t) {
        currentSession().delete(t);
    }

    /* 按id删除 */
    public void deleteById(PK id) {
        String hql = "delete from " + getEntityClass().getSimpleName() + " where id = :id";
        Query query = currentSession().createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    /*查找*/
    public T findOne(PK id) {
        return (T) currentSession().get(getEntityClass(), id);
    }

    /* 查找所有对象 */
    public List<T> findAll() {
        String hql = "from " + getEntityClass().getSimpleName();
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    /* 分页查找 */
    public List<T> findByPage(Long beginId, Integer offset, Map<String, Object> map) {
        StringBuilder sbf = new StringBuilder();
        sbf.append("from " + getEntityClass().getSimpleName());
        if (map.size() > 0) {
            sbf.append(" where 1=1 ");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                sbf.append(" and " + entry.getKey() + " =:" + entry.getKey());
            }
        }
        Query query = currentSession().createQuery(sbf.toString());
        if (map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        query.setFirstResult(Math.toIntExact(beginId));
        query.setMaxResults(offset);
        return query.list();
    }
}
