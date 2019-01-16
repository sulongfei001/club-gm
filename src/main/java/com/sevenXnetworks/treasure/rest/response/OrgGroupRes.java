package com.sevenXnetworks.treasure.rest.response;

import lombok.Data;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 15:08
 * @Version 1.0
 */
@Data
public class OrgGroupRes {
    private Long orgGroupId;
    private String orgGroupName;
    private GroupDetailPosRes groupDetailPos;
}
