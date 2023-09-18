package com.campingreservationbackend.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReservationCampSiteVo {

    private String RESERV_ID;

    private String RESERV_DATE;

    private int RESERV_COUNT;

    private String CAMP_ID;

    private String CAMP_NAME;

    private String CAMP_URL;

    private String CAMP_STATE;

    private String CAMP_CITY;

    private String CAMP_DETAIL_ADDRESS;

    private String CAMP_TOPIC_KEY;

    private String REGISTRATION_DATE;

    private String REGISTRATION_ID;


}
