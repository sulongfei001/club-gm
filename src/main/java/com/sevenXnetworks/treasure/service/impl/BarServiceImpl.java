package com.sevenXnetworks.treasure.service.impl;

import com.sevenXnetworks.treasure.bean.BarBean;
import com.sevenXnetworks.treasure.bean.StatisticsBean;
import com.sevenXnetworks.treasure.config.ConfigProperties;
import com.sevenXnetworks.treasure.dao.ActivityDao;
import com.sevenXnetworks.treasure.dao.BarDao;
import com.sevenXnetworks.treasure.dao.BarSnapshotDao;
import com.sevenXnetworks.treasure.entity.BarEntity;
import com.sevenXnetworks.treasure.entity.BarSnapshotEntity;
import com.sevenXnetworks.treasure.exception.ResourceException;
import com.sevenXnetworks.treasure.model.CustomerErrorConstants;
import com.sevenXnetworks.treasure.model.Validator;
import com.sevenXnetworks.treasure.service.BarService;
import com.sevenXnetworks.treasure.utils.FileUtils;
import com.sevenXnetworks.treasure.utils.StringUtils;
import com.sevenXnetworks.treasure.vo.BarSnapshotVo;
import com.sevenXnetworks.treasure.vo.BarStatisticsVo;
import com.sevenXnetworks.treasure.vo.BarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 15:18
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BarServiceImpl implements BarService {
    @Autowired
    private BarDao barDao;
    @Autowired
    private ConfigProperties properties;
    @Autowired
    private BarSnapshotDao snapshotDao;

    @Override
    public List<BarVo> list() {
        List<BarEntity> barEntities = barDao.findAll();
        List<BarVo> barVos = new ArrayList<>();
        barEntities.forEach(barEntity -> {
            barVos.add(toVo(barEntity));
        });
        return barVos;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void create(BarBean barBean) {
        BarEntity barEntity = new BarEntity();
        toEntity(barEntity, barBean);
        barDao.saveOrUpdate(barEntity);
        if (StringUtils.isNotBlank(barBean.getImgStr())) {
            String[] strings = barBean.getImgStr().split(",");
            for (int i = 0; i < strings.length; i++) {
                FileUtils.moveFile(properties.getTempDir(), strings[i], properties.getActivityDir());
                BarSnapshotEntity snapshotEntity = new BarSnapshotEntity(barEntity.getId(), properties.getActivityDir() + "/" + strings[i]);
                snapshotDao.saveOrUpdate(snapshotEntity);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(BarBean barBean) {
        BarEntity barEntity = barDao.findOne(barBean.getId());
        Validator.notNull(barEntity, ResourceException.error(CustomerErrorConstants.BAR_NOT_EXIST));
        toEntity(barEntity, barBean);
        barDao.saveOrUpdate(barEntity);
        if (StringUtils.isNotBlank(barBean.getDelImgStr())) {
            String[] strings = barBean.getDelImgStr().split(",");
            for (int i = 0; i < strings.length; i++) {
                snapshotDao.deleteById(Long.valueOf(strings[i]));
            }
        }
        if (StringUtils.isNotBlank(barBean.getImgStr())) {
            String[] strings = barBean.getImgStr().split(",");
            for (int i = 0; i < strings.length; i++) {
                FileUtils.moveFile(properties.getTempDir(), strings[i], properties.getBarDir());
                BarSnapshotEntity snapshotEntity = new BarSnapshotEntity(barEntity.getId(), properties.getBarDir() + "/" + strings[i]);
                snapshotDao.saveOrUpdate(snapshotEntity);
            }
        }
    }

    @Override
    public BarVo get(long id) {
        BarEntity barEntity = barDao.findOne(id);
        Validator.notNull(barEntity, ResourceException.error(CustomerErrorConstants.BAR_NOT_EXIST));
        List<BarSnapshotEntity> snapshotEntities = snapshotDao.findByBarId(id);
        List<BarSnapshotVo> snapshotVos = new ArrayList<>();
        snapshotEntities.forEach(entity -> {
            snapshotVos.add(toVo(entity));
        });
        BarVo vo = toVo(barEntity);
        vo.setSnapshotVos(snapshotVos);
        return vo;
    }

    private BarSnapshotVo toVo(BarSnapshotEntity entity) {
        BarSnapshotVo vo = new BarSnapshotVo();
        vo.setId(entity.getId());
        vo.setUrl(properties.getUploadAddr() + entity.getUrl());
        return vo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(long id) {
        barDao.deleteById(id);
        snapshotDao.deleteByBarId(id);
    }

    @Override
    public List<BarStatisticsVo> dayStatistics(StatisticsBean bean) {
        List<Object[]> objects = null;
        if (bean.getDay() != null) {
            objects = barDao.dayStatistics(bean.getDay());
        } else if (bean.getMonth() != null) {
            objects = barDao.monthStatistics(bean.getMonth());
        }
        if (objects == null) return null;
        List<BarStatisticsVo> statisticsVos = new ArrayList<>();
        for (Object[] object : objects) {
            BarStatisticsVo vo = new BarStatisticsVo();
            vo.setBarName((String) object[0]);
            vo.setPayCount((BigInteger) object[1]);
            vo.setUserCount((BigInteger) object[2]);
            vo.setIncome((BigDecimal) object[3]);
            statisticsVos.add(vo);
        }
        return statisticsVos;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void batchExamine(Byte result, List<Long> barIds) {
        barDao.batchExamine(result, barIds);
    }

    @Override
    public List<BarVo> passList() {
        List<BarEntity> barEntities = barDao.findPass();
        List<BarVo> barVos = new ArrayList<>();
        barEntities.forEach(barEntity -> {
            barVos.add(toVo(barEntity));
        });
        return barVos;
    }

    private void toEntity(BarEntity barEntity, BarBean barBean) {
        barEntity.setBarName(barBean.getBarName());
        barEntity.setOrderNum(barBean.getOrderNum());
    }

    private BarVo toVo(BarEntity barEntity) {
        BarVo vo = new BarVo();
        vo.setId(barEntity.getId());
        vo.setAllianceId(barEntity.getAllianceId());
        vo.setBarId(barEntity.getBarId());
        vo.setState(barEntity.getState());
        vo.setBarName(barEntity.getBarName());
        vo.setOrderNum(barEntity.getOrderNum());
        return vo;
    }
}
