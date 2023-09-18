package com.campingreservationbackend.vo;

import com.campingreservationbackend.constant.API_RESPONSE;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ErrorResponse {

    private int status;

    private String code;

    private String msg;

    public ErrorResponse(API_RESPONSE api_response){
        this.status = api_response.getStatus();
        this.code = api_response.getCode();
        this.msg = api_response.getMsg();
    }

}
