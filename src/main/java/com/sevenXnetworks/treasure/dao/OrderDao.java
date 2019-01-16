package com.sevenXnetworks.treasure.dao;

import com.sevenXnetworks.treasure.entity.OrderEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 18-11-6 上午11:21
 * @Version 1.0
 */
public interface OrderDao extends BaseDao<OrderEntity, Long> {
    List<OrderEntity> list(String memberId, long beginId, int offset);

    List<OrderEntity> findByActivityId(long activityId);

    long countByActivityId(Long activityId);

    List<OrderEntity> findByActivityIdCellPhone(long id, String cellPhone);

    List<OrderEntity> findUserData();

    BigDecimal incomeByActivityId(long activityId);

    List<OrderEntity> findIsWin(Long id);

}
