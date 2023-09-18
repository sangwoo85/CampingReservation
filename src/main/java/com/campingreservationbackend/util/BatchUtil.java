package com.campingreservationbackend.util;

import com.campingreservationbackend.service.ReservationService;
import com.campingreservationbackend.vo.ReservationCampSiteVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BatchUtil extends Thread{

    private final Logger LOGGER = LoggerFactory.getLogger(BatchUtil.class);

    private ReservationService service;
    private ReservationCampSiteVo vo;

    public BatchUtil(ReservationService service, ReservationCampSiteVo vo){
        this.service =  service;
        this.vo = vo;
    }

    @Override
    public void run() {
        LOGGER.info("================ BatchUtil "+service.getClass().getName()+"=============== START");
        service.resrvationBatchStart(vo);
        LOGGER.info("================ BatchUtil "+service.getClass().getName()+"=============== END");
    }


}
