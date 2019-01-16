package com.sevenXnetworks.treasure.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;


public class RestClient {

    private static final Log logger = LogFactory.getLog(RestClient.class);


    public static RestReturn post(CommonRequest request, CredentialsProvider credentials, Integer timeout) {

        HttpPost httpPost = new HttpPost();
        httpPost.setURI(URI.create(request.getPrefix() + request.getUri()));
        request.getHeaders().forEach((key, value) -> httpPost.addHeader(key, value));

        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(timeout != null ? timeout : 30000).build();
        httpPost.setConfig(config);

        HttpEntity requestEntity = new StringEntity(request.getBody(), request.getContentType());
        httpPost.setEntity(requestEntity);


        logger.info("Start Send Command!");
        logger.info("URI:	" + httpPost.getURI().toString());
        logger.info("Request Body:	" + request.getBody());
        logger.info("Request Headers:   " + httpPost.getAllHeaders());


        return request(httpPost, request.getContentType(), credentials);
    }


    public static RestReturn put(CommonRequest request, CredentialsProvider credentials, Integer timeout) {

        HttpPut httpPut = new HttpPut();
        httpPut.setURI(URI.create(request.getPrefix() + request.getUri()));
        request.getHeaders().forEach((key, value) -> httpPut.addHeader(key, value));
        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(timeout != null ? timeout : 30000).build();
        httpPut.setConfig(config);

        HttpEntity requestEntity = new StringEntity(request.getBody(), request.getContentType());
        httpPut.setEntity(requestEntity);

        logger.info("Start Send Command!");
        logger.info("URI:	" + httpPut.getURI().toString());
        logger.info("Request Body:	" + request.getBody());
        logger.info("Request Headers:   " + httpPut.getAllHeaders());

        return request(httpPut, request.getContentType(), credentials);
    }


    public static RestReturn patch(CommonRequest request, CredentialsProvider credentials, Integer timeout) {

        HttpPatch httpPatch = new HttpPatch();
        httpPatch.setURI(URI.create(request.getPrefix() + request.getUri()));
        request.getHeaders().forEach((key, value) -> httpPatch.addHeader(key, value));
        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(timeout != null ? timeout : 30000).build();
        httpPatch.setConfig(config);

        HttpEntity requestEntity = new StringEntity(request.getBody(), request.getContentType());
        httpPatch.setEntity(requestEntity);

        logger.info("Start Send Command!");
        logger.info("URI:	" + httpPatch.getURI().toString());
        logger.info("Request Body:	" + request.getBody());
        logger.info("Request Headers:   " + httpPatch.getAllHeaders());

        return request(httpPatch, request.getContentType(), credentials);
    }


    public static RestReturn get(CommonRequest request, CredentialsProvider credentials, Integer timeout) {

        HttpGet httpGet = new HttpGet();
        httpGet.setURI(URI.create(request.getPrefix() + request.getUri()));
        request.getHeaders().forEach((key, value) -> httpGet.addHeader(key, value));
        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(timeout != null ? timeout : 30000).build();
        httpGet.setConfig(config);

        logger.info("Start Send Command!");
        logger.info("URI:	" + httpGet.getURI().toString());
        logger.info("Request Headers:   " + httpGet.getAllHeaders());

        return request(httpGet, request.getContentType(), credentials);
    }


    public static RestReturn delete(CommonRequest request, CredentialsProvider credentials, Integer timeout) {

        HttpDelete httpDelete = new HttpDelete();
        httpDelete.setURI(URI.create(request.getPrefix() + request.getUri()));
        request.getHeaders().forEach((key, value) -> httpDelete.addHeader(key, value));
        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(timeout != null ? timeout : 30000).build();
        httpDelete.setConfig(config);

        logger.info("Start Send Command!");
        logger.info("URI:	" + httpDelete.getURI().toString());
        logger.info("Request Headers:   " + httpDelete.getAllHeaders());

        return request(httpDelete, request.getContentType(), credentials);
    }


    private static RestReturn request(HttpUriRequest httpUriRequest, ContentType contentType, CredentialsProvider credentials) {
        RestReturn result = new RestReturn();
        CloseableHttpClient httpClient = null;
        try {
            // 配置请求的HEADER部分
            contentType = (null == contentType) ? ContentType.APPLICATION_JSON : contentType;
            httpUriRequest.addHeader(HttpHeaders.ACCEPT, contentType.getMimeType());

            // 认证部分
            if (credentials != null) {
                httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentials).build();
            } else {
                httpClient = HttpClientBuilder.create().build();
            }

            // http方法
            logger.info("Method:	" + httpUriRequest.getMethod());

            HttpResponse response = httpClient.execute(httpUriRequest);

            // 返回响应状态信息
            logger.info("Status Code:	" + response.getStatusLine().getStatusCode());
            result.setStatusCode(response.getStatusLine().getStatusCode());

            // 获取响应消息实体
            HttpEntity entity = response.getEntity();

            // 判断响应实体是否为空
            if (entity != null) {
                String responseBody = EntityUtils.toString(entity, contentType.getCharset());
                logger.info("Response Body:	" + responseBody);
                result.setBody(responseBody);
            }
        } catch (Exception e) {
            logger.error("Connection Error:" + httpUriRequest.getURI(), e);
            result.setStatusCode(HttpStatus.SC_NOT_FOUND);
        } finally {
            // 关闭或释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("Connection Close Error", e);
            }
        }

        return result;
    }

}
