package com.sevenXnetworks.treasure.model;

import java.util.Map;

public enum CustomerErrorConstants implements LocalError {


    //#####################业务错误码#####################
    DICTIONARY_NOT_EXIST("100001", "数据字典不存在"),

    ACCOUNT_SMS_CODE_NOT_EQUAL("100011", "验证码错误"),
    ACCOUNT_SMS_CODE_EXPIRE("100012", "验证码已失效"),

    ACCOUNT_INFO_NOT_EXIST("100021", "该账户资料不存在"),


    FILE_NOT_EXIST("100031", "文件不存在"),
    FILE_MOVE_FILE("100032", "文件移动失败"),

    BAR_NOT_EXIST("100041", "酒吧不存在"),
    ACTIVITY_NOT_EXIST("100042", "商品不存在"),

    USER_ACTIVITY_NO_RECORD("100051", "用户没有购买此商品"),

    ORDER_WIN_EXIST("100061", "中奖名单已存在"),

    SYNC_DATA_ERROR("100062", "数据同步失败"),

    GOODS_NOT_EXIST("100072", "商品不存在"),


    //#####################请求参数错误码#####################
    TOKEN_AUTHENTICATION_ERROR("200000", "用户认证失败"),

    UPLOAD_FILE_NULL("200001", "上传的文件为空"),

    UPLOAD_FILE_FAIL("200002", "上传文件失败"),

    ACCOUNT_SERVICE_CELLPHONE_NUMBER_EMPTY("200011", "手机号为空"),

    ACCOUNT_SERVICE_COUNTRY_CODE_EMPTY("200012", "国家代码为空"),

    ACCOUNT_SERVICE_SMS_CODE_EMPTY("200013", "短信验证码为空"),

    ACCOUNT_SERVICE_CELLPHONE_NUMBER_ILLEGAL("200014", "手机号非法"),

    ACCOUNT_SERVICE_CELLPHONE_BINDING_OTHER("200015", "手机号已绑定其他微信号"),

    WECHAT_CODE_INVALID("200021", "微信授权码失效");


    private String code;

    private String message;

    private Map<String, Object> errorParams;


    CustomerErrorConstants(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Map<String, Object> getErrorParams() {
        return errorParams;
    }

    public LocalError setErrorParams(Map<String, Object> errorParams) {
        this.errorParams = errorParams;
        return this;
    }

    public String toString() {
        return "[" + code + "] " + message;
    }

}
