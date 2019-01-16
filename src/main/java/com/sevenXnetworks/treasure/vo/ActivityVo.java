package com.sevenXnetworks.treasure.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 15:40
 * @Version 1.0
 */
@Data
public class ActivityVo {
    private long id;
    private Long barId;
    private Long goodsId;
    private String goodsName;
    private Integer goodsType;
    private BigDecimal goodsPrdValueMon;
    private String name;
    private String content;
    private Integer count;
    private Long saleCount;
    private Byte isPromotion;
    private Byte onSale;
    private Integer orderNum;
    private Timestamp startTime;
    private Timestamp endTime;
    private List<ActivitySnapshotVo> snapshotVos;
    private List<HistoryOrderVo> orderVos;
}
