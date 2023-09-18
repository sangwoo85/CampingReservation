package com.campingreservationbackend;

import com.campingreservationbackend.service.CampSiteService;
import com.campingreservationbackend.seviceImpl.TestService;
import com.campingreservationbackend.util.FCMConnectionUtil;
import com.campingreservationbackend.util.MailSendUtil;
import com.campingreservationbackend.util.NaverLineSendUtil;
import com.campingreservationbackend.vo.CampSiteVo;
import com.campingreservationbackend.web.CampInfoController;
import com.campingreservationbackend.web.TestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class CampingReservationBackEndApplicationTests {

    @Autowired
    TestService testService;

    @Autowired
    CampSiteService campSiteService;

    @Autowired
    CampInfoController campInfoController;

    @Autowired
    FCMConnectionUtil fcmConnectionUtil;

    @Autowired
    TestController testController;

    @Autowired
    MailSendUtil mailSendUtil;

    @Autowired
    NaverLineSendUtil naverLineSendUtil;

    @Test
    public void testD(){

        Map<String,Object> inParam = new HashMap<String,Object>();

        inParam.put("USE_AT","Y");
        for(int i =0; i < 10; i ++) {
            List<CampSiteVo> list = campSiteService.selectCampSite(inParam);
            for(CampSiteVo vo : list) {
                System.out.println(vo.toString());
            }
        }
    }

    @Test
    public void testf(){

        fcmConnectionUtil.sendFcmMsg("imgingak_20211020","test Alarm","테스트 메세지 입니다.");

    }

    @Test
    public void tryCatch(){

        String result = testService.testTryFinally();
        System.out.println("!!!!!!!!"+result);

    }
    @Test
    public void testController(){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("1","1");
        param.put("2","2");

        testController.dbTest(param);
    }

    @Test
    public void testMail(){
        String[] emailList = {"ksswy79@gmail.com"};
        try {
            mailSendUtil.sendMail(emailList, "내용", "제목");
        }catch(MessagingException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testAddCampList(){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("CAMP_ID","202111160000");
        paramMap.put("RESERV_DATE","20211121");
        paramMap.put("DEVICE_ID","123");
       Map<String,Object> resultMap =  campSiteService.addReserveCampSite(paramMap);

        System.out.println(resultMap.toString());

    }

    @Test
    public void sendToLineContents(){

        naverLineSendUtil.sendToLineContents("7YPpeAwicOBcv9MoZfCGdL4eHfBx9FoW2nmJrbWc8mG","현재 테스트로 보내는 메세지");

    }

}
