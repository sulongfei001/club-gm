package com.sevenXnetworks.treasure.dao.impl;

import com.sevenXnetworks.treasure.dao.BarDao;
import com.sevenXnetworks.treasure.entity.BarEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 15:22
 * @Version 1.0
 */
@Repository
public class BarDaoImpl extends BaseDaoImpl<BarEntity, Long> implements BarDao {
    @Override
    public List<BarEntity> findAll() {
        String hql = "FROM BarEntity ORDER BY orderNum ";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<Object[]> dayStatistics(Date day) {
        String sql = "select distinct b.bar_name barName, count(*) payCount, count(distinct o.open_id) userCount, sum(o.trade_money) income  " +
                "from bar b left join activity a on b.bar_id = a.bar_id left join `order` o on a.id = o.activity_id " +
                "where to_days(o.trade_date) = to_days(?)  group by b.bar_name ";
        SQLQuery query = currentSession().createSQLQuery(sql);
        query.setDate(0, day);
        return query.list();
    }

    @Override
    public List<Object[]> monthStatistics(Date month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(month);
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH) + 1;
        String sql = "select distinct b.bar_name barName, count(*) payCount, count(distinct o.open_id) userCount, sum(o.trade_money) income  " +
                "from bar b left join activity a on b.bar_id = a.bar_id left join `order` o on a.id = o.activity_id " +
                "where year(trade_date) = ? and month(trade_date) = ? group by b.bar_name ";
        SQLQuery query = currentSession().createSQLQuery(sql);
        query.setString(0, String.valueOf(y));
        query.setString(1, String.valueOf(m));
        return query.list();
    }

    @Override
    public BarEntity findByBarId(Long orgId) {
        String hql = "FROM BarEntity WHERE barId = :orgId ";
        Query query = currentSession().createQuery(hql);
        query.setLong("orgId", orgId);
        return (BarEntity) query.uniqueResult();
    }

    @Override
    public void batchExamine(Byte result, List<Long> ids) {
        String hql = "update BarEntity set state = :result where id in (:ids) ";
        Query query = currentSession().createQuery(hql);
        query.setByte("result", result);
        query.setParameterList("ids", ids);
        query.executeUpdate();
    }

    @Override
    public List<BarEntity> findPass() {
        String hql = "FROM BarEntity WHERE state = 1 ORDER BY orderNum ";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }
}
