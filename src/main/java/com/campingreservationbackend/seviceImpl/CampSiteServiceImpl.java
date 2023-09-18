package com.campingreservationbackend.seviceImpl;

import com.campingreservationbackend.constant.API;
import com.campingreservationbackend.constant.API_RESPONSE;
import com.campingreservationbackend.constant.SQL_COMMON;
import com.campingreservationbackend.dao.CampListDao;
import com.campingreservationbackend.exception.ParamException;
import com.campingreservationbackend.service.CampSiteService;
import com.campingreservationbackend.util.StringUtils;
import com.campingreservationbackend.vo.CampSiteVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CampSiteServiceImpl implements CampSiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampSiteServiceImpl.class);

    private CampListDao campListDao;

    @Autowired
    private void setCampListDao(CampListDao campListDao){
        this.campListDao = campListDao;
    }

    @Override
    public List<CampSiteVo> selectCampSite(Map<String, Object> inParam) {
        LOGGER.info("selectCampSite ================= START");
        LOGGER.info("selectCampSite ================= inParam : "+inParam);

        inParam.put(SQL_COMMON.USE_AT._param,SQL_COMMON.USE._param);
        List<CampSiteVo> campSiteVoList = campListDao.selectCampList(inParam);

        for(CampSiteVo vo : campSiteVoList){
            LOGGER.info(vo.toString());
        }

        return campSiteVoList;
    }

    @Transactional
    @Override
    public Map<String, Object> addReserveCampSite(Map<String, Object> inParam) {

        Map<String,Object> resultMap = new HashMap<String,Object>();

        String CAMP_ID      = StringUtils.getString(inParam.get("CAMP_ID"));
        String RESERV_DATE  = StringUtils.getString(inParam.get("RESERV_DATE"));

        //현재 Validation Check 안함
        String DEVICE_ID    = StringUtils.getString(inParam.get("DEVICE_ID"));

        try {

            if (StringUtils.isNotEmpty(CAMP_ID)
                    && StringUtils.isNotEmpty(RESERV_DATE)) {
                /* length 체크*/
                if (RESERV_DATE.length() != 8) {
                    throw new ParamException(API_RESPONSE.ERROR_PARAM_CAMP_RESERVATION_DATE_NOT_INVALID_LENGTH);
                }

                String _SELECT_CAMP_ID = campListDao.selectExistCampSite(CAMP_ID);
                /* 존재하는 ID인지 체크*/
                if (StringUtils.isEmpty(_SELECT_CAMP_ID)) {
                    throw new ParamException(API_RESPONSE.ERROR_PARAM_CAMPSITE_NULL);
                }

                int insert_n = campListDao.insertReserveCampSite(inParam);

                /* 등록 실패 */
                if (insert_n < 1) {
                    throw new ParamException(API_RESPONSE.ERROR_INSERT_FAIL_RESERVATION_CAMP_SITE);
                }else {

                    /*
                    *   Batch DB에 Merge insert
                    * */

                    inParam.put(SQL_COMMON.USE_AT._param,SQL_COMMON.USE._param );
                    inParam.put("RESERV_COUNT",1);// 초기셋팅

                    int merge_insert_n = campListDao.mergeInsertCamp(inParam);

                    if(merge_insert_n < 1){
                        throw new ParamException(API_RESPONSE.ERROR_INSERT_FAIL_RESERVATION_CAMP_BATCH);
                    }

                }

                resultMap.put(API.RESPONSE_STATUS, API_RESPONSE.SUCCESS.code);
                resultMap.put(API.RESPONSE_MSG, API_RESPONSE.SUCCESS.msg);

            }else{
                throw new ParamException(API_RESPONSE.ERROR_PARAM_CAMPSITE_NULL);
            }
        }catch(ParamException pe){
            pe.printStackTrace();
            resultMap.put(API.RESPONSE_STATUS, pe.getApi_response().getCode());
            resultMap.put(API.RESPONSE_MSG, pe.getApi_response().getMsg());

        }catch (Exception e){
            e.printStackTrace();
            resultMap.put(API.RESPONSE_STATUS, API_RESPONSE.ERROR_DEFAULT.code);
            resultMap.put(API.RESPONSE_MSG, API_RESPONSE.ERROR_DEFAULT.msg);
        }

        return resultMap;
    }
}
