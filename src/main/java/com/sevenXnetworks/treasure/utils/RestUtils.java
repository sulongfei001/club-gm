package com.sevenXnetworks.treasure.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

/**
 * @Description
 * @Author sulongfei
 * @Date 18-11-8 下午4:55
 * @Version 1.0
 */
public class RestUtils {
    public static void main(String[] args) {
        get_("test");
        //-1    系统繁忙，此时请开发者稍候再试
        //0     请求成功
        //40029 code 无效
        //45011	频率限制，每个用户每分钟100次
    }

    public static JSONObject get_(String code) {
        final String APPID = "";
        final String SECRET = "";
        if (StringUtils.isBlank(code)) return null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(URI.create(url));
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                JSONObject object = JSON.parseObject(EntityUtils.toString(entity));
                return object;
            }
        } catch (Exception e) {
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return null;
    }
}
