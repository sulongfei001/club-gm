package com.sevenXnetworks.treasure.service;

import com.sevenXnetworks.treasure.bean.ActivityBean;
import com.sevenXnetworks.treasure.vo.ActivityConfigVo;
import com.sevenXnetworks.treasure.vo.ActivityVo;
import com.sevenXnetworks.treasure.vo.HistoryActivityVo;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 15:39
 * @Version 1.0
 */
public interface ActivityService {
    List<ActivityVo> list();

    void create(ActivityBean activityBean);

    ActivityVo get(long id);

    void update(ActivityBean activityBean);

    void delete(long id);

    void onSale(long id, Byte onSale);

    ActivityConfigVo config(long id);

    void configWin(long id, String cellPhone);

    List<HistoryActivityVo> history();
}
