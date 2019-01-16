package com.sevenXnetworks.treasure.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/27 10:49
 * @Version 1.0
 */
@Data
public class GoodsBean {
    private long id;
    private String goodsName;
    private Byte goodsType;
    private BigDecimal goodsPrdValueMon;
    private Integer orderNum;
}

