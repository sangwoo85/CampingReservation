package com.campingreservationbackend.seviceImpl;

import com.campingreservationbackend.constant.LineToken;
import com.campingreservationbackend.service.LoadChromeDriver;
import com.campingreservationbackend.service.ReservationService;
import com.campingreservationbackend.service.SeleniumService;
import com.campingreservationbackend.util.NaverLineSendUtil;
import com.campingreservationbackend.vo.ReservationCampSiteVo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("imgingakreservationserviceimpl")
public class ImgingakReservationServiceImpl extends SeleniumService implements ReservationService {

    private final static String url = "https://imjingakcamping.co.kr/resv/res_01.html?checkdate=";

    private final static Logger LOGGER = LoggerFactory.getLogger(ImgingakReservationServiceImpl.class);

    private final String CAMP_ENG_NAME = "imgingak";

    private final String SEND_MSG = " 일 임진각 평화누리 캠핑장 예약 알림";

    private NaverLineSendUtil naverLineSendUtil;

    @Autowired
    private void setNaverLineSendUtil(NaverLineSendUtil naverLineSendUtil){
        this.naverLineSendUtil = naverLineSendUtil;
    }

    @Async
    @Override
    public void resrvationBatchStart(ReservationCampSiteVo vo) {

        LOGGER.info("=============== resrvationBatchStart ================= START");
        LOGGER.info("=============== resrvationBatchStart ================= Param : "+vo.toString());

        ChromeDriver chromeDriver = new LoadChromeDriver().getChromeDriver();

        String _date = vo.getRESERV_DATE();
        String topicKeyword = vo.getCAMP_TOPIC_KEY();
        String _title = vo.getCAMP_NAME();
        String fcm_message = _date + SEND_MSG;

        String targetURL = url + _date;

        // 탭 목록 가져오기
       // List<String> tabs = new ArrayList<String>(chromeDriver.getWindowHandles());

        try {

         //   chromeDriver.switchTo().window(tabs.get(0));
            chromeDriver.get(targetURL);

            Thread.sleep(2000);

            List<WebElement> elementList = chromeDriver.findElements(By.className("fwb"));

            for(int i = 1; i <3; i++){

                String _text = elementList.get(i).getText();

                int _num = Integer.parseInt(_text);
                LOGGER.info("Number "+_num);

                /* 자리가 있을 경우 PUSH 발송 */
                if(_num > 0){
                    fcmConnectionUtil.sendFcmMsg(topicKeyword,_title,fcm_message);

                    for(String token : LineToken.getNameByToekns(CAMP_ENG_NAME)) {
                        naverLineSendUtil.sendToLineContents(token,fcm_message);
                    }
                }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(chromeDriver != null){
                chromeDriver.close();
            }
            chromeDriver.quit();
        }
    }
}
