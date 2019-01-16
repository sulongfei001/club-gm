package com.sevenXnetworks.treasure.model;

import java.io.Serializable;

/**
 * Created by lance on 16-10-20.
 */
public class CommonResult implements Serializable {

    private boolean success;

    private Object data;

    private String info;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static CommonResult success(Object o) {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(true);
        commonResult.setData(o);
        return commonResult;
    }

    public static CommonResult success() {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(true);
        commonResult.setInfo("请求成功");
        return commonResult;
    }

    public static CommonResult fail(String s) {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(false);
        commonResult.setData(null);
        commonResult.setInfo(s);
        return commonResult;
    }

}
