package com.campingreservationbackend.scheduler;

import com.campingreservationbackend.seviceImpl.ReservationBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CampSiteReservationScheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(CampSiteReservationScheduler.class);

    private ReservationBatchService reservationBatchService;

    @Autowired
    private void setReservationBatchService(ReservationBatchService reservationBatchService){
        this.reservationBatchService = reservationBatchService;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void reservationAlertBatch(){
        LOGGER.info("================= reservationAlertBatch START =================");
        reservationBatchService.resrvationBatchStart();
        LOGGER.info("================= reservationAlertBatch END =================");
    }


}
