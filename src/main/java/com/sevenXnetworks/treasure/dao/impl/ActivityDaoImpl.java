package com.sevenXnetworks.treasure.dao.impl;

import com.sevenXnetworks.treasure.dao.ActivityDao;
import com.sevenXnetworks.treasure.entity.ActivityEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 15:41
 * @Version 1.0
 */
@Repository
public class ActivityDaoImpl extends BaseDaoImpl<ActivityEntity, Long> implements ActivityDao {
    @Override
    public List<ActivityEntity> findAll() {
        String hql = "FROM ActivityEntity ORDER BY orderNum ";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public void onSale(long id, Byte onSale) {
        String hql = "UPDATE ActivityEntity SET onSale = :onSale WHERE id = :id ";
        Query query = currentSession().createQuery(hql);
        query.setByte("onSale", onSale);
        query.setLong("id", id);
        query.executeUpdate();
    }

    @Override
    public List<ActivityEntity> findHistory() {
        String hql = "FROM ActivityEntity WHERE endTime < now() ORDER BY orderNum ";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }
}
