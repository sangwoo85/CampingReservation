package com.campingreservationbackend.seviceImpl;

import com.campingreservationbackend.constant.ServiceName;
import com.campingreservationbackend.dao.ReservationDao;
import com.campingreservationbackend.exception.ParamException;
import com.campingreservationbackend.service.ReservationService;
import com.campingreservationbackend.vo.ReservationCampSiteVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationBatchService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReservationBatchService.class);

    private ReservationService imgingakReservationService;

    private ReservationDao reservationDao;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    public void setReservationDao(ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }

    @Autowired
    public void setImgingakReservationService(ImgingakReservationServiceImpl imgingakReservationService){
       this.imgingakReservationService = imgingakReservationService;
    }


    public void resrvationBatchStart() {

        LOGGER.info("resrvationStart START");

        try {

            List<ReservationCampSiteVo> reservationList = reservationDao.selectReservationBatchList();

            for(ReservationCampSiteVo vo : reservationList) {

                LOGGER.info("======= Camp Name : " + vo.getCAMP_NAME() + " : Reservation Date : " + vo.getRESERV_DATE());

                ServiceName.SERVICE_NAME targetService = ServiceName.SERVICE_NAME.getIdBySerivceName(vo.getCAMP_ID());

                LOGGER.info("START SERVICE : "+targetService +" Target Bean Name "+targetService.getServiceClass().getSimpleName().toLowerCase());

                Class reservationService =  targetService.getServiceClass();

                ReservationService _class = applicationContext.getBean(reservationService.getSimpleName().toLowerCase()
                                                                       ,ReservationService.class);

                _class.resrvationBatchStart(vo);

            }
        }catch(ParamException pe){
            pe.printStackTrace();

        }catch(Exception e){
            e.printStackTrace();

        }

        LOGGER.info("resrvationStart END");
    }
}
