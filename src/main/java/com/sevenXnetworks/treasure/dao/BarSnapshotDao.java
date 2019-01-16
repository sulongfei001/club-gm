package com.sevenXnetworks.treasure.dao;

import com.sevenXnetworks.treasure.entity.BarSnapshotEntity;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/2 13:49
 * @Version 1.0
 */
public interface BarSnapshotDao extends BaseDao<BarSnapshotEntity, Long> {
    List<BarSnapshotEntity> findByBarId(long id);

    void deleteByBarId(long id);
}
