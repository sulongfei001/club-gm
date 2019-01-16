package com.sevenXnetworks.treasure.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Description
 * @Author sulongfei
 * @Date 18-11-8 下午1:36
 * @Version 1.0
 */
@Data
public class HistoryOrderVo {
    private String userName;
    private Timestamp tradeDate;
    private Integer tradeCount;
    private String activityName;
    private String goodsName;

}
