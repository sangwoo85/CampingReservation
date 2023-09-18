package com.campingreservationbackend.web;

import com.campingreservationbackend.seviceImpl.TestService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.ValidationRequest;
import com.twilio.type.PhoneNumber;
import com.twilio.type.Twiml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.AudioFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @RequestMapping(value="/test",method = RequestMethod.POST)
    public void dbTest(@RequestParam Map<String,Object> inParam){

        LOGGER.info("=_="+inParam);
        testService.testDB();
    }
    @RequestMapping(value="/testIndex",method = RequestMethod.POST)
    public Map<String,Object> indexTest(@RequestBody Map<String,Object>inParam){
        LOGGER.info("============= indexTest ========== "+inParam);
        Map<String,Object>resultMap = new HashMap<String,Object>();

        resultMap.put("responseCode","0");

        AudioFormat

        return resultMap;

    }

    public static void main(String[] args) {


        final String ACCOUNT_SID = "ACe742724b7cb0a607b0a7224c975fc120";
        final String AUTH_TOKEN = "40bd51b0eaa4d183d5a8992c1b403efb";


        String xml = "<Response>" +
                "<Play>" +
                "https://hpw.e9pay.co.kr/upload/audio/ars_file/052b8553-a_123372020.mp3"+
                "</Play>" +
                "</Response>";

        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);


       Call call = Call.creator(
          new PhoneNumber("+821077775465")
          ,new PhoneNumber("+18888395528")
          ,new Twiml(xml)
        ).create();



        System.out.println(call.getSid());

/*
        ValidationRequest validationRequest = ValidationRequest.creator(
                        new com.twilio.type.PhoneNumber("+821071883352"))
                .setFriendlyName("kim hyeonjin")
                .create();

        System.out.println(validationRequest.getFriendlyName());
        System.out.println(validationRequest.getValidationCode()); */
    }
}
