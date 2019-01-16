package com.sevenXnetworks.treasure.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 18-11-9 下午2:40
 * @Version 1.0
 */
@Data
public class HistoryActivityVo {
    private String activityName;
    private BigDecimal income;
    private List<UserDataVo> userVos;

}
