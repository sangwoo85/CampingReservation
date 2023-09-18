package com.campingreservationbackend.service;

import com.campingreservationbackend.vo.CampSiteVo;

import java.util.List;
import java.util.Map;

public interface CampSiteService {


    /**
     * @title 캠핑 사이트 리스트를 전달 해준다.
     * */
    public List<CampSiteVo> selectCampSite(Map<String,Object> inParam);

    public Map<String,Object> addReserveCampSite(Map<String,Object>inParam);
}
