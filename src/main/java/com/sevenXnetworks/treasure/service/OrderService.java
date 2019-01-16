package com.sevenXnetworks.treasure.service;

import com.sevenXnetworks.treasure.vo.UserDataVo;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 18-11-6 上午11:19
 * @Version 1.0
 */
public interface OrderService {
    List<UserDataVo> userData();

    void random(long id);
}
