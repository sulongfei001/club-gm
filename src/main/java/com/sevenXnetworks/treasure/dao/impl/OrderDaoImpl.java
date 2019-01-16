package com.sevenXnetworks.treasure.dao.impl;

import com.sevenXnetworks.treasure.dao.OrderDao;
import com.sevenXnetworks.treasure.entity.OrderEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 18-11-6 上午11:22
 * @Version 1.0
 */
@Repository
public class OrderDaoImpl extends BaseDaoImpl<OrderEntity, Long> implements OrderDao {
    @Override
    public List<OrderEntity> list(String memberId, long beginId, int offset) {
        String hql = "FROM OrderEntity WHERE memberId = :memberId ";
        Query query = currentSession().createQuery(hql);
        query.setString("memberId", memberId);
        query.setFirstResult(Math.toIntExact(beginId));
        query.setMaxResults(offset);
        return query.list();
    }

    @Override
    public List<OrderEntity> findByActivityId(long activityId) {
        String hql = "FROM OrderEntity WHERE activityId = :activityId ORDER BY tradeDate ";
        Query query = currentSession().createQuery(hql);
        query.setLong("activityId", activityId);
        return query.list();
    }

    @Override
    public long countByActivityId(Long activityId) {
        String hql = "SELECT SUM(tradeCount) FROM OrderEntity WHERE activityId = :activityId ";
        Query query = currentSession().createQuery(hql);
        query.setLong("activityId", activityId);
        Object obj = query.uniqueResult();
        return obj == null ? 0 : (long) obj;
    }

    @Override
    public List<OrderEntity> findByActivityIdCellPhone(long activityId, String cellPhone) {
        String hql = "FROM OrderEntity WHERE activityId = :activityId AND cellPhone = :cellPhone ORDER BY tradeCount DESC ";
        Query query = currentSession().createQuery(hql);
        query.setLong("activityId", activityId);
        query.setString("cellPhone", cellPhone);
        return query.list();
    }

    @Override
    public List<OrderEntity> findUserData() {
        String hql = "FROM OrderEntity ORDER BY memberId ";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public BigDecimal incomeByActivityId(long activityId) {
        String hql = "SELECT SUM(tradeMoney) FROM OrderEntity WHERE activityId = :activityId ";
        Query query = currentSession().createQuery(hql);
        query.setLong("activityId", activityId);
        Object obj = query.uniqueResult();
        return obj == null ? BigDecimal.valueOf(0) : (BigDecimal) obj;
    }

    @Override
    public List<OrderEntity> findIsWin(Long activityId) {
        String hql = "FROM OrderEntity WHERE activityId = :activityId AND isWin = 1 ORDER BY tradeDate DESC ";
        Query query = currentSession().createQuery(hql);
        query.setLong("activityId", activityId);
        return query.list();
    }

}
