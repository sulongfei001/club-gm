package com.sevenXnetworks.treasure.dao;

import com.sevenXnetworks.treasure.entity.SecurityUserEntity;

public interface SecurityUserDao extends BaseDao<SecurityUserEntity, Long> {

    /**
     * 根据name查询
     *
     * @param name
     * @return
     */
    SecurityUserEntity findByName(String name);
}