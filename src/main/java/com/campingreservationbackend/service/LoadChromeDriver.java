package com.campingreservationbackend.service;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @title ChromeDriver configuration
 *
 * */


public class LoadChromeDriver{

    public final static String DRIVE_PATH = "/Users/sangwookim/Documents/workspace/CampingReservationBackEnd/src/main/resources/chromeDriver/chromedriver";

    protected ChromeDriver chromeDriver;

    private final static Logger LOGGER = LoggerFactory.getLogger(LoadChromeDriver.class);

    public ChromeDriver getChromeDriver(){
        if(chromeDriver == null){
            setChromeDriver();
        }
        return chromeDriver;
    }

    public void setChromeDriver() {
        LOGGER.info("============= LoadChromeDriver =============");
        System.setProperty("webdriver.chrome.driver", DRIVE_PATH);

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");		//창 안띄우고 실행
        options.addArguments("--no-sandbox");				//창 안띄우고 실행

        // WebDriver 객체 생성
        ChromeDriver driver = new ChromeDriver( options );
        //drivers.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // 기본앱 사용안함
        options.addArguments("--headless");

        chromeDriver = driver;
    }
}
