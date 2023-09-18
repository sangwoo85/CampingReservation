package com.campingreservationbackend.web;

import com.campingreservationbackend.constant.API;
import com.campingreservationbackend.constant.API_RESPONSE;
import com.campingreservationbackend.service.CampSiteService;
import com.campingreservationbackend.vo.CampSiteVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CampInfoController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CampInfoController.class);

    private CampSiteService campSiteService;

    @Autowired
    private void setCampSiteService(CampSiteService campSiteService){
        this.campSiteService = campSiteService;
    }

    @RequestMapping(value="/campList",method = RequestMethod.POST)
    public List<CampSiteVo> infomationCampSite(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object>inParam){
        List<CampSiteVo> campSiteVoList = null;
        try {

            campSiteVoList = campSiteService.selectCampSite(inParam);

        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(403);
        }
        return campSiteVoList;
    }


    /**
     * @title 앱에서 캠핑장 요청을 받는 api
     * @param inParam :CAMP_ID : 캠핑장 ID
     *                 RESERV_DATE 예약 날짜 8자리 20211113
     *                 DEVICE_ID   예약하는 Device ID
     *                 CAMP_NAME   Log용 캠프 ID
     *
     * */
    @RequestMapping(value="/camp/addReservCampSite",method = RequestMethod.POST)
    public Map<String,Object> addCampReservation(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object>inParam){

        Map<String,Object> resultMap = new HashMap<String,Object>();

       if(inParam.isEmpty()){
           resultMap.put(API.RESPONSE_STATUS, API_RESPONSE.ERROR_PARAM_NULL.code);
           resultMap.put(API.RESPONSE_MSG,    API_RESPONSE.ERROR_PARAM_NULL.msg);
       }

       resultMap = campSiteService.addReserveCampSite(inParam);

        return resultMap;
    }

}
