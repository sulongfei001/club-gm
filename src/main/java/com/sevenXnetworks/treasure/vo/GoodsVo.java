package com.sevenXnetworks.treasure.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 16:52
 * @Version 1.0
 */
@Data
public class GoodsVo {
    private Long id;
    private Long goodsId;
    private String goodsName;
    private Byte goodsType;
    private BigDecimal goodsPrdValueMon;
    private Integer orderNum;
    private Byte state;
}
