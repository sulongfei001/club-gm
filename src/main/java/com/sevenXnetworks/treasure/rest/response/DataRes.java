package com.sevenXnetworks.treasure.rest.response;

import com.sevenXnetworks.treasure.rest.RestReturn;
import lombok.Data;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 15:05
 * @Version 1.0
 */
@Data
public class DataRes extends RestReturn {
    private HeaderRes Header;
    private ResultRes Result;
}
