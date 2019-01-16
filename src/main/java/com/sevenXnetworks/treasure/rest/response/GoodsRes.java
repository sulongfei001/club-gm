package com.sevenXnetworks.treasure.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 15:09
 * @Version 1.0
 */
@Data
public class GoodsRes {
    private Long goodsId;
    private String name;
    private Integer type;
    private BigDecimal prdValueMon;
}
