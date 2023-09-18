package com.campingreservationbackend.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TestDao {

    final static Logger LOGGER = LoggerFactory.getLogger(TestDao.class);

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<Map<String,Object>> selectTest(){
        LOGGER.info("================== selectTest ==================== START");
        return sqlSessionFactory.openSession().selectList("TestMapper.testSelect");
        //return sqlSession.selectList("TestMapper.testSelect");
    }

}
