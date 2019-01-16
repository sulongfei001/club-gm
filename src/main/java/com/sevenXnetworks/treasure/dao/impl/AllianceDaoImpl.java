package com.sevenXnetworks.treasure.dao.impl;

import com.sevenXnetworks.treasure.dao.AllianceDao;
import com.sevenXnetworks.treasure.entity.AllianceEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 15:57
 * @Version 1.0
 */
@Repository
public class AllianceDaoImpl extends BaseDaoImpl<AllianceEntity, Long> implements AllianceDao {
    @Override
    public AllianceEntity findByAllianceId(Long orgGroupId) {
        String hql = "FROM AllianceEntity where allianceId = :orgGroupId ";
        Query query = currentSession().createQuery(hql);
        query.setLong("orgGroupId", orgGroupId);
        return (AllianceEntity) query.uniqueResult();
    }
}
