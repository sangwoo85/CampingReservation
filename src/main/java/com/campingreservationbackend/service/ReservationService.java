package com.campingreservationbackend.service;

import com.campingreservationbackend.vo.ReservationCampSiteVo;

/**
 * 배치 서비스 등록 interface
 *
 * */
public interface ReservationService {


    /**
     * @title 실제 크롤링 하는 부분
     * */
    public void resrvationBatchStart(ReservationCampSiteVo vo);

}
