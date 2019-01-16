package com.sevenXnetworks.treasure.dao;

import com.sevenXnetworks.treasure.entity.ActivityEntity;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 15:41
 * @Version 1.0
 */
public interface ActivityDao extends BaseDao<ActivityEntity, Long> {
    void onSale(long id, Byte onSale);

    List<ActivityEntity> findHistory();
}
