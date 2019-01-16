package com.sevenXnetworks.treasure.dao.impl;

import com.sevenXnetworks.treasure.dao.ActivitySnapshotDao;
import com.sevenXnetworks.treasure.entity.ActivitySnapshotEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/1 11:32
 * @Version 1.0
 */
@Repository
public class ActivitySnapshotDaoImpl extends BaseDaoImpl<ActivitySnapshotEntity, Long> implements ActivitySnapshotDao {
    @Override
    public List<ActivitySnapshotEntity> findByActivityId(long id) {
        String hql = "FROM ActivitySnapshotEntity WHERE activityId = :id ";
        Query query = currentSession().createQuery(hql);
        query.setLong("id", id);
        return query.list();
    }

    @Override
    public void deleteByActivityId(long id) {
        String hql = "DELETE FROM ActivitySnapshotEntity WHERE activityId = :id ";
        Query query = currentSession().createQuery(hql);
        query.setLong("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteByTypeActivityId(Byte type, Long id) {
        String hql = "DELETE FROM ActivitySnapshotEntity WHERE activityId = :id and type = :type ";
        Query query = currentSession().createQuery(hql);
        query.setLong("id", id);
        query.setByte("type", type);
        query.executeUpdate();
    }
}
