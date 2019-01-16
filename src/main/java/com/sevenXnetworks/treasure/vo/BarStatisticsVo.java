package com.sevenXnetworks.treasure.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Description
 * @Author sulongfei
 * @Date 18-11-9 下午5:28
 * @Version 1.0
 */
@Data
public class BarStatisticsVo {
    private String barName;
    private BigInteger payCount;
    private BigInteger userCount;
    private BigDecimal income;
}
