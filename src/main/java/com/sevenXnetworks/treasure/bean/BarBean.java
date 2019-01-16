package com.sevenXnetworks.treasure.bean;

import lombok.Data;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 18:54
 * @Version 1.0
 */
@Data
public class BarBean {
    private long id;
    private String barName;
    private Integer orderNum;
    private String imgStr;
    private String delImgStr;
}
