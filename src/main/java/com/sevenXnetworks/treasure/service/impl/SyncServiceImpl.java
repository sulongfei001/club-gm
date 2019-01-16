package com.sevenXnetworks.treasure.service.impl;

import com.sevenXnetworks.treasure.dao.AllianceDao;
import com.sevenXnetworks.treasure.dao.BarDao;
import com.sevenXnetworks.treasure.dao.GoodsDao;
import com.sevenXnetworks.treasure.entity.AllianceEntity;
import com.sevenXnetworks.treasure.entity.BarEntity;
import com.sevenXnetworks.treasure.entity.GoodsEntity;
import com.sevenXnetworks.treasure.exception.ResourceException;
import com.sevenXnetworks.treasure.model.CustomerErrorConstants;
import com.sevenXnetworks.treasure.model.Validator;
import com.sevenXnetworks.treasure.rest.response.DataRes;
import com.sevenXnetworks.treasure.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 15:18
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class SyncServiceImpl implements SyncService {
    @Autowired
    private BarDao barDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private AllianceDao allianceDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void syncData(DataRes dataRes) {
        Validator.isTrue(dataRes.getHeader().getCode().equals("200") && dataRes.getResult().getGroupResult() != null, ResourceException.error(CustomerErrorConstants.SYNC_DATA_ERROR));
        dataRes.getResult().getGroupResult().forEach(orgGroupRes -> {
            AllianceEntity allianceEntity = allianceDao.findByAllianceId(orgGroupRes.getOrgGroupId());
            if (allianceEntity == null) {
                allianceEntity = new AllianceEntity(orgGroupRes.getOrgGroupId(), orgGroupRes.getOrgGroupName(), (byte) 0);
                allianceDao.saveOrUpdate(allianceEntity);
            }
            orgGroupRes.getGroupDetailPos().getOrgList().forEach(orgRes -> {
                BarEntity barEntity = barDao.findByBarId(orgRes.getOrgId());
                if (barEntity == null) {
                    barEntity = new BarEntity(orgGroupRes.getOrgGroupId(), orgRes.getOrgId(), orgRes.getOrgName(), (byte) 0);
                    barDao.saveOrUpdate(barEntity);
                }
            });
            orgGroupRes.getGroupDetailPos().getGoodsList().forEach(goodsRes -> {
                GoodsEntity goodsEntity = goodsDao.findByGoodsId(goodsRes.getGoodsId());
                if (goodsEntity == null) {
                    goodsEntity = new GoodsEntity(orgGroupRes.getOrgGroupId(), goodsRes.getGoodsId(), goodsRes.getName(), goodsRes.getType().byteValue(), goodsRes.getPrdValueMon(), (byte) 0);
                    goodsDao.saveOrUpdate(goodsEntity);
                }
            });
        });
    }
}
