package com.sevenXnetworks.treasure.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 18-11-8 下午5:39
 * @Version 1.0
 */
@Data
public class ActivityConfigVo {
    private Integer count;
    private Long saleCount;
    private List<HistoryOrderVo> orderVos;
}
