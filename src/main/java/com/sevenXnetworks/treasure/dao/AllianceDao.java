package com.sevenXnetworks.treasure.dao;

import com.sevenXnetworks.treasure.entity.AllianceEntity;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 15:56
 * @Version 1.0
 */
public interface AllianceDao extends BaseDao<AllianceEntity, Long> {
    AllianceEntity findByAllianceId(Long orgGroupId);
}
