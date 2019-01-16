package com.sevenXnetworks.treasure.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 19:29
 * @Version 1.0
 */
@Data
public class ActivityBean {
    private long id;
    private Long barId;
    private Long goodsId;
    private String goodsName;
    private Integer goodsType;
    private BigDecimal goodsPrdValueMon;
    private String name;
    private String content;
    private Integer count;
    private Integer orderNum;
    private Timestamp startTime;
    private Timestamp endTime;
    private String imgStr;
    private String delImgStr;
    private String mainImg;
    private String thumbImg;
}
