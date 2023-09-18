package com.campingreservationbackend.constant;

/**
 * @title API or METHOD  response Definitions
 *        다른 목록 추가시 여기 추가
 * */
public enum API_RESPONSE {


    SUCCESS(200,"000","SUCCESS"),

    ERROR_DEFAULT(400,"999","System Error"),

    ERROR_PARAM_NULL(200,"900","Parameter is Null"),

    ERROR_PARAM_CAMPSITE_NULL(200,"901","등록되지 않은 캠핑장 입니다."),

    ERROR_PARAM_CAMP_RESERVATION_DATE_NOT_INVALID_LENGTH (200,"902","유효한 날짜가 아닙니다."),

    ERROR_INSERT_FAIL_RESERVATION_CAMP_SITE(200,"800","캠핑장 등록에 실패 하였습니다."),

    ERROR_INSERT_FAIL_RESERVATION_CAMP_BATCH(200,"803","캠핑장 배치 등록에 실패하였습니다."),

    ERROR_INSERT_FAIL(200,"801","DB 등록에 실패 하였습니다.")

    ;



    public final int status;

    public final String code;

    public final String msg;

    API_RESPONSE(int status,String code, String msg){
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public int getStatus(){
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
