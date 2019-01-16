package com.sevenXnetworks.treasure.rest;

import org.apache.http.entity.ContentType;

import java.util.HashMap;
import java.util.Map;

public class CommonRequest {

    // url prefix
    private String prefix;

    // uri
    private String uri;


    private String body;

    private ContentType contentType;

    // request header
    private Map<String, String> headers;


    //初始化请求参数
    {
        headers = new HashMap<>();
    }


    public CommonRequest(String prefix, String uri, ContentType contentType) {
        this.prefix = prefix;
        this.uri = uri;
        this.contentType = contentType;
    }


    public CommonRequest(String prefix, String uri, String body, ContentType contentType) {
        this.prefix = prefix;
        this.uri = uri;
        this.body = body;
        this.contentType = contentType;
    }


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public static void main(String[] args) {
        CommonRequest request = new CommonRequest("http://freeapi.ipip.net", "/118.28.8.8", "", null);
        request.getHeaders().put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");

        RestReturn restReturn = RestClient.get(request, null, null);
        System.out.println(restReturn.getStatusCode());
        System.out.println(restReturn.getBody());
        if (restReturn.getStatusCode() == 200) {
            System.out.println(restReturn.getBody());
        }
    }
}
