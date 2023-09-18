package com.campingreservationbackend.exception;

import com.campingreservationbackend.constant.API_RESPONSE;

import java.io.Serializable;


public class ParamException extends RuntimeException implements Serializable {

    private API_RESPONSE api_response;

    public ParamException(API_RESPONSE api_response){
        super(api_response.getCode());
        this.api_response = api_response;
    }

    public API_RESPONSE getApi_response(){

        return this.api_response;
    }
}
