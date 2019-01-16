package com.sevenXnetworks.treasure.exception;

import com.sevenXnetworks.treasure.model.LocalError;

import java.util.Map;

public enum CommonServerErrorConstants implements LocalError {

    HTTP_400("400", "错误的请求"),
    HTTP_404("404", "资源未找到"),
    HTTP_405("405", "方法不允许"),
    HTTP_406("406", "未接受的媒体类型"),
    HTTP_415("415", "不支持的媒体类型"),
    SERVER_INNER_ERROR("500", "服务器内部错误");

    private String code;
    private String message;

    CommonServerErrorConstants(String code, String message) {
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
        return null;
    }

    public String toString() {
        return "[" + code + "] " + message;
    }


}
