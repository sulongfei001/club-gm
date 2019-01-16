package com.sevenXnetworks.treasure.dao.impl;

import com.sevenXnetworks.treasure.dao.GoodsDao;
import com.sevenXnetworks.treasure.entity.GoodsEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 16:10
 * @Version 1.0
 */
@Repository
public class GoodsDaoImpl extends BaseDaoImpl<GoodsEntity, Long> implements GoodsDao {
    @Override
    public GoodsEntity findByGoodsId(Long goodsId) {
        String hql = "FROM GoodsEntity WHERE goodsId = :goodsId ";
        Query query = currentSession().createQuery(hql);
        query.setLong("goodsId", goodsId);
        return (GoodsEntity) query.uniqueResult();
    }

    @Override
    public List<GoodsEntity> findPass() {
        String hql = "FROM GoodsEntity WHERE state = 1 ORDER BY orderNum ";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public void batchExamine(Byte result, List<Long> ids) {
        String hql = "update GoodsEntity set state = :result where id in (:ids) ";
        Query query = currentSession().createQuery(hql);
        query.setByte("result", result);
        query.setParameterList("ids", ids);
        query.executeUpdate();
    }

    @Override
    public List<GoodsEntity> findAll() {
        String hql = "FROM GoodsEntity ORDER BY orderNum ";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }
}
