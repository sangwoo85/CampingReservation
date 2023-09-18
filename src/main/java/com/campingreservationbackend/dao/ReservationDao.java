package com.campingreservationbackend.dao;

import com.campingreservationbackend.vo.ReservationCampSiteVo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReservationDao.class);

    private SqlSession sqlSession;

    @Autowired
    ReservationDao(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

    public List<ReservationCampSiteVo> selectReservationBatchList(){
        LOGGER.info("selectReservationBatchListDao START");
        return sqlSession.selectList("ReservationBatchMapper.selectReservationBatchList");
    }
}
