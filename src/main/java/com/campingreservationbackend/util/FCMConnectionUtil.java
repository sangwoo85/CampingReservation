package com.campingreservationbackend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FCMConnectionUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(FCMConnectionUtil.class);

    private final String url = "https://fcm.googleapis.com/fcm/send";
    private final String token = "AAAAnRTA6U0:APA91bFbUs_KFOWJ1gVNqKwGfLUrbHDN05_z5Rgp3Ecp2fOVMTfkVGkTmAND0jfHxafeBjwJlE_apdT64D--qZJveIUfBG_EPbvA_fG12eGJqGQOUV1504-H6_ho-smHhrTda5RnAy_6";

    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private void setRestTemplateUtil(RestTemplateUtil restTemplateUtil){
        this.restTemplateUtil = restTemplateUtil;
    }


    /**
     * @title FCM Http connection
     *
     * @param topic : key word ,
     * @param title message title
     * @param msg : message contents
     * */
    public Map<String,Object> sendFcmMsg(String topic, String title, String msg){

        LOGGER.info("sendFcmMsg =========================== START");
        LOGGER.info("sendFcmMsg =========================== topic : "+topic);
        LOGGER.info("sendFcmMsg =========================== msg : "+ msg);

        Map<String,Object> headerMap = new HashMap<String,Object>();

        String tokenHeader = "key="+token;
        headerMap.put("Authorization",tokenHeader);
        headerMap.put("Content-Type","application/json");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",tokenHeader);
        httpHeaders.add("Content-Type","application/json");

        LOGGER.info("sendFcmMsg HEADER : "+httpHeaders);

        Map<String, Object> paramMap = new HashMap<String, Object>();

        String toTopic = "/topics/" + topic;

        paramMap.put("to", toTopic);
        paramMap.put("priority", "high");

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("title", "[" + title + "] 캠핑알람");
        dataMap.put("message", msg);

        Map<String, String> notificationMap = new HashMap<String, String>();
        notificationMap.put("title", "[" + title + "] 캠핑알람");
        notificationMap.put("body", msg);

        paramMap.put("data", dataMap);
        paramMap.put("notification", notificationMap);

        Map<String,Object> resultMap = restTemplateUtil.sendPost(url,httpHeaders,paramMap);

        LOGGER.info("ResultMap = "+resultMap.toString());
        return resultMap;


    }

}
