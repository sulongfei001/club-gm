package com.sevenXnetworks.treasure.rest.interfaces;

import com.google.gson.Gson;
import com.sevenXnetworks.treasure.rest.CommonRequest;
import com.sevenXnetworks.treasure.rest.RestClient;
import com.sevenXnetworks.treasure.rest.RestReturn;
import com.sevenXnetworks.treasure.utils.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


public abstract class AbstractSyncIF<T extends RestReturn> {

    @Autowired
    private Gson gson;

    protected <T extends RestReturn> T get(String uri, Class<? extends RestReturn> classOfT) {
        CommonRequest request = new CommonRequest("", uri, null, ContentType.APPLICATION_JSON);

        // 发送get请求
        RestReturn restReturn = RestClient.get(request, null, null);
        return getInstance(restReturn, classOfT);
    }

    protected <T extends RestReturn> T post(String uri, Object body, Class<? extends RestReturn> classOfT) {
        CommonRequest request = new CommonRequest("", uri, gson.toJson(body), ContentType.APPLICATION_JSON);

        // 发送get请求
        RestReturn restReturn = RestClient.post(request, null, null);
        return getInstance(restReturn, classOfT);
    }


    private <T extends RestReturn> T getInstance(RestReturn restReturn, Class<? extends RestReturn> classOfT) {
        T instance;

        try {
            HttpStatus httpStatus = HttpStatus.valueOf(restReturn.getStatusCode());
            switch (httpStatus) {
                case OK:
                    instance = (StringUtils.isNotBlank(restReturn.getBody()))
                            ? (T) gson.fromJson(restReturn.getBody(), classOfT)
                            : (T) classOfT.newInstance();
                    instance.setStatusCode(restReturn.getStatusCode());
                    break;
                case NOT_FOUND:
                case BAD_GATEWAY:
                case UNAUTHORIZED:
                    instance = (T) classOfT.newInstance();
                    instance.setStatusCode(restReturn.getStatusCode());
                    instance.setBody(restReturn.getBody());
                    break;
                default:
                    instance = (T) classOfT.newInstance();
                    instance.setStatusCode(restReturn.getStatusCode());
                    instance.setBody(restReturn.getBody());
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return instance;
    }

}
