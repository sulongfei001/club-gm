package com.sevenXnetworks.treasure.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description
 * @Author sulongfei
 * @Date 18-11-9 下午1:56
 * @Version 1.0
 */
@Data
public class UserDataVo {
    private String orderId;
    private String activityName;
    private String goodsName;
    private String userName;
    private String cellPhone;
    private Timestamp createDate;
    private Timestamp tradeDate;
    private Integer tradeCount;
    private BigDecimal tradeMoney;
    private Byte isWin;
}
