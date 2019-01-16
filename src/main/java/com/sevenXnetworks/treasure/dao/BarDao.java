package com.sevenXnetworks.treasure.dao;

import com.sevenXnetworks.treasure.entity.BarEntity;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 15:22
 * @Version 1.0
 */
public interface BarDao extends BaseDao<BarEntity, Long> {

    List<Object[]> dayStatistics(Date day);

    List<Object[]> monthStatistics(Date month);

    BarEntity findByBarId(Long orgId);

    void batchExamine(Byte result, List<Long> barIds);

    List<BarEntity> findPass();
}
