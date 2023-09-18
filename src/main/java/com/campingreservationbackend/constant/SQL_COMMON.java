package com.campingreservationbackend.constant;

public enum SQL_COMMON {

    USE_AT("USE_AT"),

    USE("Y"),

    NOT_USE("N"),

    DELETE_AT("DELETE_AT"),

    DELETE_Y("Y"),

    DELETE_N("N");

    SQL_COMMON(String _param) {
        this._param = _param;
    }

    public String _param;

}
