package com.sevenXnetworks.treasure.rest;

public class RestReturn {

    // http状态码
    private int statusCode;

    // 响应内容
    private String body;


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}