package com.campingreservationbackend.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CampSiteVo {

    private String CAMP_NAME;

    private String CAMP_ID;

    private String CAMP_URL;

    private String CAMP_STATE;

    private String CAMP_CITY;

    private String CAMP_DETAIL_ADDRESS;

    private String CAMP_TOPIC_KEY;

    @Override
    public String toString() {
        return "CampSiteVo{" +
                "CAMP_NAME='" + CAMP_NAME + '\'' +
                ", CAMP_ID='" + CAMP_ID + '\'' +
                ", CAMP_URL='" + CAMP_URL + '\'' +
                ", CAMP_STATE='" + CAMP_STATE + '\'' +
                ", CAMP_CITY='" + CAMP_CITY + '\'' +
                ", CAMP_DETAIL_ADDRESS='" + CAMP_DETAIL_ADDRESS + '\'' +
                ", CAMP_TOPIC_KEY='" + CAMP_TOPIC_KEY + '\'' +
                '}';
    }
}
