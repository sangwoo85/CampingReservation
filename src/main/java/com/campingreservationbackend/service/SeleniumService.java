package com.campingreservationbackend.service;

import com.campingreservationbackend.util.FCMConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class SeleniumService{

    protected FCMConnectionUtil fcmConnectionUtil;

    private String SERVICE_NAME = "";

    @Autowired
    public void setFcmConnectionUtil(FCMConnectionUtil fcmConnectionUtil){
        this.fcmConnectionUtil = fcmConnectionUtil;
    }

}
