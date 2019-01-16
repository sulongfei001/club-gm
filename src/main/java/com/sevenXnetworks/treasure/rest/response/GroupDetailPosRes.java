package com.sevenXnetworks.treasure.rest.response;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 15:12
 * @Version 1.0
 */
@Data
public class GroupDetailPosRes {
    private List<OrgRes> orgList;
    private List<GoodsRes> goodsList;
}
