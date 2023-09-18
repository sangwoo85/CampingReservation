package com.campingreservationbackend.seviceImpl;

import com.campingreservationbackend.dao.TestDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class TestService {

    final static Logger LOGGER = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private TestDao testDao;

    public void testDB(){
        LOGGER.info("testDB =============== START");
        List<Map<String,Object>> resultLisr = testDao.selectTest();

        for(Map<String,Object> map : resultLisr){


            for(String key : map.keySet()){

                String value = (String)map.get(key);
                LOGGER.info("key "+key +"     Value : "+value);
            }
            LOGGER.info("========================================");


        }

    }

    public String testTryFinally(){

        try{

            System.out.println("in Try!!!");

            return "Complete";

        }finally {
            System.out.println("finally !!");
        }

    }
}
