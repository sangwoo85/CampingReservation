package com.campingreservationbackend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.ParseException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class RestTemplateUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtil.class);

    private RestTemplate restTemplate;

    @Autowired
    private void setRestTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Map<String,Object> sendPost(String url, HttpHeaders headers, Map<String,Object>param){
        LOGGER.info("=========== sendPost =========== START");
        LOGGER.info("=========== sendPost =========== url : "+url);
        LOGGER.info("=========== sendPost =========== Header : "+headers);
        LOGGER.info("=========== sendPost =========== param : "+param);

        Map<String,Object> resultMap = new HashMap<String,Object>();

        try {
            HttpEntity<Map<String,Object>> httpEntity = new HttpEntity<>(param,headers);
            LOGGER.info(" send entity :"+httpEntity);
            ResponseEntity<JSONObject> response = restTemplate.postForEntity(url,httpEntity,JSONObject.class);
            LOGGER.info("response : "+response.toString());

            HttpStatus httpStatus = response.getStatusCode();
            JSONObject responseJsonObject = response.getBody();
            resultMap = new ObjectMapper().readValue(responseJsonObject.toString(), Map.class);
            resultMap.put("httpStatus",httpStatus.value());

        }catch(ParseException pe){
            pe.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }

        LOGGER.info("=========== sendPost =========== END");
        return resultMap;
    }

    public Map<String,Object> sendJsonPost(String url, HttpHeaders headers, JSONObject jsonObject){
        LOGGER.info("=========== sendPost =========== START");
        LOGGER.info("=========== sendPost =========== url : "+url);
        LOGGER.info("=========== sendPost =========== Header : "+headers);
        LOGGER.info("=========== sendPost =========== param : "+jsonObject);

        Map<String,Object> resultMap = new HashMap<String,Object>();

        try {
            HttpEntity<?> httpEntity = new HttpEntity<>(jsonObject.toString(),headers);
            LOGGER.info(" send entity :"+httpEntity);
            ResponseEntity<JSONObject> response = restTemplate.postForEntity(url,httpEntity,JSONObject.class);
            LOGGER.info("response : "+response.toString());

            HttpStatus httpStatus = response.getStatusCode();
            JSONObject responseJsonObject = response.getBody();
            resultMap = new ObjectMapper().readValue(responseJsonObject.toString(), Map.class);
            resultMap.put("httpStatus",httpStatus.value());

        }catch(ParseException pe){
            pe.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }

        LOGGER.info("=========== sendPost =========== END");
        return resultMap;
    }

}
