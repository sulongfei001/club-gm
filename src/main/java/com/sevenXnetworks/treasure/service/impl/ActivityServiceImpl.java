package com.sevenXnetworks.treasure.service.impl;

import com.sevenXnetworks.treasure.bean.ActivityBean;
import com.sevenXnetworks.treasure.config.ConfigProperties;
import com.sevenXnetworks.treasure.dao.ActivityDao;
import com.sevenXnetworks.treasure.dao.ActivitySnapshotDao;
import com.sevenXnetworks.treasure.dao.BarDao;
import com.sevenXnetworks.treasure.dao.OrderDao;
import com.sevenXnetworks.treasure.entity.ActivityEntity;
import com.sevenXnetworks.treasure.entity.ActivitySnapshotEntity;
import com.sevenXnetworks.treasure.entity.BarEntity;
import com.sevenXnetworks.treasure.entity.OrderEntity;
import com.sevenXnetworks.treasure.exception.BusinessException;
import com.sevenXnetworks.treasure.exception.ResourceException;
import com.sevenXnetworks.treasure.model.Const;
import com.sevenXnetworks.treasure.model.CustomerErrorConstants;
import com.sevenXnetworks.treasure.model.Validator;
import com.sevenXnetworks.treasure.service.ActivityService;
import com.sevenXnetworks.treasure.service.OrderService;
import com.sevenXnetworks.treasure.utils.FileUtils;
import com.sevenXnetworks.treasure.utils.StringUtils;
import com.sevenXnetworks.treasure.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 15:39
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ActivitySnapshotDao snapshotDao;
    @Autowired
    private ConfigProperties properties;
    @Autowired
    private Timer timer;
    @Autowired
    private OrderService orderService;

    @Override
    public List<ActivityVo> list() {
        List<ActivityEntity> activityEntities = activityDao.findAll();
        List<ActivityVo> activityVos = new ArrayList<>();
        activityEntities.forEach(entity -> {
            activityVos.add(toVo(entity));
        });
        return activityVos;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void create(ActivityBean activityBean) {
        ActivityEntity entity = new ActivityEntity();
        toEntity(entity, activityBean);
        entity.setOnSale((byte) 0);
        activityDao.saveOrUpdate(entity);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                orderService.random(entity.getId());
            }
        }, entity.getEndTime());
        if (StringUtils.isNotBlank(activityBean.getImgStr())) {
            String[] strings = activityBean.getImgStr().split(",");
            for (int i = 0; i < strings.length; i++) {
                FileUtils.moveFile(properties.getTempDir(), strings[i], properties.getActivityDir());
                ActivitySnapshotEntity snapshotEntity = new ActivitySnapshotEntity(entity.getId(), Const.SNAPSHOT_TYPE_1, properties.getActivityDir() + "/" + strings[i]);
                snapshotDao.saveOrUpdate(snapshotEntity);
            }
        }
        if (StringUtils.isNotBlank(activityBean.getMainImg())) {
            FileUtils.moveFile(properties.getTempDir(), activityBean.getMainImg(), properties.getActivityDir());
            ActivitySnapshotEntity snapshotEntity = new ActivitySnapshotEntity(entity.getId(), Const.SNAPSHOT_TYPE_0, properties.getActivityDir() + "/" + activityBean.getMainImg());
            snapshotDao.saveOrUpdate(snapshotEntity);
        }
        if (StringUtils.isNotBlank(activityBean.getThumbImg())) {
            FileUtils.moveFile(properties.getTempDir(), activityBean.getThumbImg(), properties.getActivityDir());
            ActivitySnapshotEntity snapshotEntity = new ActivitySnapshotEntity(entity.getId(), Const.SNAPSHOT_TYPE_2, properties.getActivityDir() + "/" + activityBean.getThumbImg());
            snapshotDao.saveOrUpdate(snapshotEntity);
        }
    }

    @Override
    public ActivityVo get(long id) {
        ActivityEntity activityEntity = activityDao.findOne(id);
        Validator.notNull(activityEntity, ResourceException.error(CustomerErrorConstants.ACTIVITY_NOT_EXIST));
        List<ActivitySnapshotEntity> snapshotEntities = snapshotDao.findByActivityId(id);
        List<ActivitySnapshotVo> snapshotVos = toVo(snapshotEntities);
        ActivityVo vo = toVo(activityEntity);
        vo.setSnapshotVos(snapshotVos);
        return vo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(ActivityBean activityBean) {
        ActivityEntity activityEntity = activityDao.findOne(activityBean.getId());
        Validator.notNull(activityEntity, ResourceException.error(CustomerErrorConstants.ACTIVITY_NOT_EXIST));
        toEntity(activityEntity, activityBean);
        activityDao.saveOrUpdate(activityEntity);
        if (StringUtils.isNotBlank(activityBean.getDelImgStr())) {
            String[] strings = activityBean.getDelImgStr().split(",");
            for (int i = 0; i < strings.length; i++) {
                snapshotDao.deleteById(Long.valueOf(strings[i]));
            }
        }
        if (StringUtils.isNotBlank(activityBean.getImgStr())) {
            String[] strings = activityBean.getImgStr().split(",");
            for (int i = 0; i < strings.length; i++) {
                FileUtils.moveFile(properties.getTempDir(), strings[i], properties.getActivityDir());
                ActivitySnapshotEntity snapshotEntity = new ActivitySnapshotEntity(activityEntity.getId(), Const.SNAPSHOT_TYPE_1, properties.getActivityDir() + "/" + strings[i]);
                snapshotDao.saveOrUpdate(snapshotEntity);
            }
        }
        if (StringUtils.isNotBlank(activityBean.getMainImg())) {
            snapshotDao.deleteByTypeActivityId(Const.SNAPSHOT_TYPE_0, activityEntity.getId());
            FileUtils.moveFile(properties.getTempDir(), activityBean.getMainImg(), properties.getActivityDir());
            ActivitySnapshotEntity snapshotEntity = new ActivitySnapshotEntity(activityEntity.getId(), Const.SNAPSHOT_TYPE_0, properties.getActivityDir() + "/" + activityBean.getMainImg());
            snapshotDao.saveOrUpdate(snapshotEntity);
        }
        if (StringUtils.isNotBlank(activityBean.getThumbImg())) {
            snapshotDao.deleteByTypeActivityId(Const.SNAPSHOT_TYPE_2, activityEntity.getId());
            FileUtils.moveFile(properties.getTempDir(), activityBean.getThumbImg(), properties.getActivityDir());
            ActivitySnapshotEntity snapshotEntity = new ActivitySnapshotEntity(activityEntity.getId(), Const.SNAPSHOT_TYPE_2, properties.getActivityDir() + "/" + activityBean.getThumbImg());
            snapshotDao.saveOrUpdate(snapshotEntity);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(long id) {
        activityDao.deleteById(id);
        snapshotDao.deleteByActivityId(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void onSale(long id, Byte onSale) {
        activityDao.onSale(id, onSale);
    }

    @Override
    public ActivityConfigVo config(long id) {
        ActivityEntity activityEntity = activityDao.findOne(id);
        long saleCount = orderDao.countByActivityId(id);
        List<OrderEntity> orderEntities = orderDao.findByActivityId(id);
        List<HistoryOrderVo> orderVos = toOrderVo(orderEntities, activityEntity.getName(), activityEntity.getGoodsName());
        ActivityConfigVo vo = new ActivityConfigVo();
        vo.setCount(activityEntity.getCount());
        vo.setSaleCount(saleCount);
        vo.setOrderVos(orderVos);
        return vo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void configWin(long id, String cellPhone) {
        if (StringUtils.isBlank(cellPhone)) return;
        if (orderDao.findIsWin(id).size() > 0) {
            ResourceException.error(CustomerErrorConstants.ORDER_WIN_EXIST);
        }
        List<OrderEntity> orderEntities = orderDao.findByActivityIdCellPhone(id, cellPhone);
        Validator.notTrue(orderEntities == null || orderEntities.size() == 0, BusinessException.error(CustomerErrorConstants.USER_ACTIVITY_NO_RECORD));
        int random = new Random().nextInt(orderEntities.size());
        OrderEntity orderEntity = orderEntities.get(random);
        orderEntity.setIsWin((byte) 1);
        orderDao.saveOrUpdate(orderEntity);
    }

    @Override
    public List<HistoryActivityVo> history() {
        List<ActivityEntity> activityEntities = activityDao.findHistory();
        List<HistoryActivityVo> activityVos = new ArrayList<>();
        activityEntities.forEach(entity -> {
            BigDecimal income = orderDao.incomeByActivityId(entity.getId());
            List<OrderEntity> orderEntities = orderDao.findIsWin(entity.getId());
            List<UserDataVo> userDataVos = new ArrayList<>();
            orderEntities.forEach(orderEntity -> {
                UserDataVo vo = new UserDataVo();
                vo.setUserName(orderEntity.getUserName());
                vo.setCellPhone(orderEntity.getCellPhone());
                vo.setTradeCount(orderEntity.getTradeCount());
                vo.setTradeDate(orderEntity.getTradeDate());
                vo.setTradeMoney(orderEntity.getTradeMoney());
                userDataVos.add(vo);
            });
            HistoryActivityVo vo = new HistoryActivityVo();
            vo.setActivityName(entity.getName());
            vo.setIncome(income == null ? BigDecimal.valueOf(0) : income);
            vo.setUserVos(userDataVos);
            activityVos.add(vo);
        });
        return activityVos;
    }

    private List<HistoryOrderVo> toOrderVo(List<OrderEntity> orderEntities, String name, String goodsName) {
        List<HistoryOrderVo> orderVos = new ArrayList<>();
        orderEntities.forEach(entity -> {
            HistoryOrderVo vo = new HistoryOrderVo();
            vo.setUserName(entity.getUserName());
            vo.setTradeDate(entity.getTradeDate());
            vo.setTradeCount(entity.getTradeCount());
            vo.setActivityName(name);
            vo.setGoodsName(goodsName);
            orderVos.add(vo);
        });
        return orderVos;
    }

    private ActivityVo toVo(ActivityEntity entity) {
        ActivityVo vo = new ActivityVo();
        vo.setId(entity.getId());
        vo.setBarId(entity.getBarId());
        vo.setGoodsId(entity.getGoodsId());
        vo.setGoodsName(entity.getGoodsName());
        vo.setGoodsType(entity.getGoodsType());
        vo.setGoodsPrdValueMon(entity.getGoodsPrdValueMon());
        vo.setName(entity.getName());
        vo.setContent(entity.getContent());
        vo.setCount(entity.getCount());
        vo.setOnSale(entity.getOnSale());
        vo.setOrderNum(entity.getOrderNum());
        vo.setStartTime(entity.getStartTime());
        vo.setEndTime(entity.getEndTime());
        return vo;
    }

    private List<ActivitySnapshotVo> toVo(List<ActivitySnapshotEntity> entities) {
        List<ActivitySnapshotVo> snapshotVos = new ArrayList<>();
        entities.forEach(entity -> {
            ActivitySnapshotVo vo = new ActivitySnapshotVo();
            vo.setId(entity.getId());
            vo.setType(entity.getType());
            vo.setUrl(properties.getUploadAddr() + entity.getUrl());
            snapshotVos.add(vo);
        });
        return snapshotVos;
    }

    private void toEntity(ActivityEntity entity, ActivityBean activityBean) {
        entity.setBarId(activityBean.getBarId());
        entity.setGoodsId(activityBean.getGoodsId());
        entity.setGoodsName(activityBean.getGoodsName());
        entity.setGoodsType(activityBean.getGoodsType());
        entity.setGoodsPrdValueMon(activityBean.getGoodsPrdValueMon());
        entity.setName(activityBean.getName());
        entity.setContent(activityBean.getContent());
        entity.setCount(activityBean.getCount());
        entity.setOrderNum(activityBean.getOrderNum());
        entity.setStartTime(activityBean.getStartTime());
        entity.setEndTime(activityBean.getEndTime());
    }
}
