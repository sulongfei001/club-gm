package com.sevenXnetworks.treasure.service.impl;

import com.sevenXnetworks.treasure.dao.ActivityDao;
import com.sevenXnetworks.treasure.dao.OrderDao;
import com.sevenXnetworks.treasure.entity.ActivityEntity;
import com.sevenXnetworks.treasure.entity.OrderEntity;
import com.sevenXnetworks.treasure.service.OrderService;
import com.sevenXnetworks.treasure.vo.UserDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description
 * @Author sulongfei
 * @Date 18-11-6 上午11:20
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ActivityDao activityDao;

    @Override
    public List<UserDataVo> userData() {
        List<OrderEntity> orderEntities = orderDao.findUserData();
        List<UserDataVo> userDataVos = toUserDataVo(orderEntities);
        return userDataVos;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void random(long id) {
        List<OrderEntity> orderEntities = orderDao.findIsWin(id);
        if (orderEntities != null && orderEntities.size() > 0) return;
        List<OrderEntity> orderEntityList = orderDao.findByActivityId(id);
        if (orderEntityList != null && orderEntityList.size() > 0) {
            OrderEntity orderEntity = orderEntityList.get(new Random().nextInt(orderEntityList.size()));
            orderEntity.setIsWin((byte) 1);
            orderEntity.setWinCode("you win");
            orderDao.saveOrUpdate(orderEntity);
        }
    }

    private List<UserDataVo> toUserDataVo(List<OrderEntity> orderEntities) {
        List<UserDataVo> userDataVos = new ArrayList<>();
        orderEntities.forEach(entity -> {
            ActivityEntity activityEntity = activityDao.findOne(entity.getActivityId());
            UserDataVo vo = new UserDataVo();
            vo.setOrderId(entity.getOrderId());
            vo.setUserName(entity.getUserName());
            vo.setCellPhone(entity.getCellPhone());
            vo.setActivityName(activityEntity.getName());
            vo.setGoodsName(activityEntity.getGoodsName());
            vo.setCreateDate(entity.getCreateDate());
            vo.setIsWin(entity.getIsWin());
            vo.setTradeCount(entity.getTradeCount());
            vo.setTradeDate(entity.getTradeDate());
            vo.setTradeMoney(entity.getTradeMoney());
            userDataVos.add(vo);
        });
        return userDataVos;
    }

}
