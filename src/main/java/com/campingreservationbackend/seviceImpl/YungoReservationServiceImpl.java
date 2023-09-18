package com.campingreservationbackend.seviceImpl;

import com.campingreservationbackend.service.ReservationService;
import com.campingreservationbackend.service.SeleniumService;
import com.campingreservationbackend.vo.ReservationCampSiteVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("yungoreservationserviceimpl")
public class YungoReservationServiceImpl extends SeleniumService implements ReservationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(YungoReservationServiceImpl.class);

    @Override
    public void resrvationBatchStart(ReservationCampSiteVo vo) {
        if(!vo.getCAMP_NAME().equals("")){
            return;
        }
    }
}
