package com.campingreservationbackend.dao;

import com.campingreservationbackend.vo.CampSiteVo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CampListDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(CampListDao.class);

    SqlSession sqlSession;

    @Autowired
    private void setSqlSession(SqlSession sqlSession){
        this.sqlSession =sqlSession;
    }

    public List<CampSiteVo> selectCampList(Map<String,Object> inParam){
        LOGGER.info("selectCampList START");
        return sqlSession.selectList("CampListMapper.selectCampList",inParam);
    }

    //@Cacheable(value="campSiteList")
    public String selectExistCampSite(String CAMP_ID){
        LOGGER.info("selectExistCampSite START");
        return sqlSession.selectOne("CampListMapper.selectExistCampSite",CAMP_ID);
    }



    public int insertReserveCampSite(Map<String,Object>inParam){
        LOGGER.info("insertReserveCampSite START");
        return sqlSession.insert("CampListMapper.insertReserveCampSite",inParam);
    }

    public int mergeInsertCamp(Map<String,Object>inParam){
        LOGGER.info("mergeInsertCamp");
        return sqlSession.insert("CampListMapper.mergeInsertCamp",inParam);
    }
}
