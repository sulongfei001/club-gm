package com.sevenXnetworks.treasure.dao.impl;

import com.sevenXnetworks.treasure.dao.BarSnapshotDao;
import com.sevenXnetworks.treasure.entity.BarSnapshotEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/2 13:49
 * @Version 1.0
 */
@Repository
public class BarSnapshotDaoImpl extends BaseDaoImpl<BarSnapshotEntity, Long> implements BarSnapshotDao {
    @Override
    public List<BarSnapshotEntity> findByBarId(long id) {
        String hql = "FROM BarSnapshotEntity WHERE barId = :id ";
        Query query = currentSession().createQuery(hql);
        query.setLong("id", id);
        return query.list();
    }

    @Override
    public void deleteByBarId(long id) {
        String hql = "DELETE FROM BarSnapshotEntity WHERE barId = :id ";
        Query query = currentSession().createQuery(hql);
        query.setLong("id", id);
        query.executeUpdate();
    }
}
