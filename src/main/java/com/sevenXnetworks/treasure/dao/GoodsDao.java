package com.sevenXnetworks.treasure.dao;

import com.sevenXnetworks.treasure.entity.GoodsEntity;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 16:09
 * @Version 1.0
 */
public interface GoodsDao extends BaseDao<GoodsEntity, Long> {
    GoodsEntity findByGoodsId(Long goodsId);

    List<GoodsEntity> findPass();

    void batchExamine(Byte result, List<Long> ids);
}
