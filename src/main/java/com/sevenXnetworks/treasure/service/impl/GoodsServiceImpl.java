package com.sevenXnetworks.treasure.service.impl;

import com.sevenXnetworks.treasure.bean.GoodsBean;
import com.sevenXnetworks.treasure.dao.GoodsDao;
import com.sevenXnetworks.treasure.entity.GoodsEntity;
import com.sevenXnetworks.treasure.exception.ResourceException;
import com.sevenXnetworks.treasure.model.CustomerErrorConstants;
import com.sevenXnetworks.treasure.model.Validator;
import com.sevenXnetworks.treasure.service.GoodsService;
import com.sevenXnetworks.treasure.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 16:51
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<GoodsVo> list() {
        List<GoodsEntity> goodsEntities = goodsDao.findAll();
        List<GoodsVo> goodsVos = new ArrayList<>();
        goodsEntities.forEach(entity -> {
            goodsVos.add(toVo(entity));
        });
        return goodsVos;
    }

    @Override
    public List<GoodsVo> passList() {
        List<GoodsEntity> goodsEntities = goodsDao.findPass();
        List<GoodsVo> goodsVos = new ArrayList<>();
        goodsEntities.forEach(entity -> {
            goodsVos.add(toVo(entity));
        });
        return goodsVos;
    }

    @Override
    public GoodsVo get(long id) {
        GoodsEntity goodsEntity = goodsDao.findOne(id);
        Validator.notNull(goodsEntity, ResourceException.error(CustomerErrorConstants.GOODS_NOT_EXIST));
        GoodsVo vo = toVo(goodsEntity);
        return vo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(GoodsBean goodsBean) {
        GoodsEntity goodsEntity = goodsDao.findOne(goodsBean.getId());
        Validator.notNull(goodsEntity, ResourceException.error(CustomerErrorConstants.GOODS_NOT_EXIST));
        goodsEntity.setGoodsName(goodsBean.getGoodsName());
        goodsEntity.setGoodsType(goodsBean.getGoodsType());
        goodsEntity.setGoodsPrdValueMon(goodsBean.getGoodsPrdValueMon());
        goodsEntity.setOrderNum(goodsBean.getOrderNum());
        goodsDao.saveOrUpdate(goodsEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(long id) {
        goodsDao.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void batchExamine(Byte result, List<Long> ids) {
        goodsDao.batchExamine(result, ids);
    }

    private GoodsVo toVo(GoodsEntity entity) {
        GoodsVo vo = new GoodsVo();
        vo.setId(entity.getId());
        vo.setGoodsId(entity.getGoodsId());
        vo.setGoodsName(entity.getGoodsName());
        vo.setGoodsType(entity.getGoodsType());
        vo.setGoodsPrdValueMon(entity.getGoodsPrdValueMon());
        vo.setOrderNum(entity.getOrderNum());
        vo.setState(entity.getState());
        return vo;
    }
}
