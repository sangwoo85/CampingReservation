package com.campingreservationbackend.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @title Line Chatting 방에 메세지 보내기
 * */
@Component
public class NaverLineSendUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(NaverLineSendUtil.class);

    private final static String lineUrl  = "https://notify-api.line.me/api/notify";

    public void sendToLineContents(String token ,String msg){
        LOGGER.info("======= sendToLineContents =========== START");
        LOGGER.info("======= sendToLineContents =========== Token : "+token);
        LOGGER.info("======= sendToLineContents =========== Message : "+msg);

        try {

            URL url = new URL(lineUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.addRequestProperty("Authorization", "Bearer "+token);
            conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();

            sb.append("message=");
            sb.append(msg);

            LOGGER.info("Message : "+sb.toString());

            bw.write(sb.toString());

            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            LOGGER.info("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            LOGGER.info("response body : " + result);

            br.close();
            bw.close();

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            String status = element.getAsJsonObject().get("status").getAsString();
            String responseMessage = element.getAsJsonObject().get("message").getAsString();


            LOGGER.info("Response status : "+status);
            LOGGER.info("Response Message : "+responseMessage);

        }catch(Exception e){
            e.printStackTrace();
        }

        LOGGER.info("======= sendToLineContents =========== END");

    }


}
