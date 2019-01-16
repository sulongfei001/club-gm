package com.sevenXnetworks.treasure.service;

import com.sevenXnetworks.treasure.bean.BarBean;
import com.sevenXnetworks.treasure.bean.StatisticsBean;
import com.sevenXnetworks.treasure.vo.BarStatisticsVo;
import com.sevenXnetworks.treasure.vo.BarVo;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 15:18
 * @Version 1.0
 */
public interface BarService {
    List<BarVo> list();

    void create(BarBean barBean);

    void update(BarBean barBean);

    BarVo get(long id);

    void delete(long id);

    List<BarStatisticsVo> dayStatistics(StatisticsBean bean);

    void batchExamine(Byte result, List<Long> asList);

    List<BarVo> passList();
}
