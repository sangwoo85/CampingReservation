package com.campingreservationbackend;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class NaverLineSendUtil {


    public static void main(String[] args) throws Exception {

        String lineUrl  = "https://notify-api.line.me/api/notify";

        String ROOM_TOKEN_KIM = "7YPpeAwicOBcv9MoZfCGdL4eHfBx9FoW2nmJrbWc8mG";

        String ROOM_TOKEN_IMGINGAK = "AdhTq2voezpmUwFB8ClfjgjpXkNZczERTecYvOfoPuT";

        URL url = new URL("https://notify-api.line.me/api/notify");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.addRequestProperty("Authorization", "Bearer "+ROOM_TOKEN_KIM);
        conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        //conn.connect();
        // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        StringBuilder sb = new StringBuilder();

        sb.append("message=");
        sb.append("테스트 메세지");

        System.out.println("Message : "+sb.toString());

        bw.write(sb.toString());

        bw.flush();

        // 결과 코드가 200이라면 성공
        int responseCode = conn.getResponseCode();
        System.out.println("responseCode : " + responseCode);

        // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line = "";
        String result = "";

        while ((line = br.readLine()) != null) {
            result += line;
        }

        System.out.println("response body : " + result);

        br.close();
        bw.close();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);

        String status = element.getAsJsonObject().get("status").getAsString();
        String responseMessage = element.getAsJsonObject().get("message").getAsString();


        System.out.println("Response status : "+status);
        System.out.println("Response Message : "+responseMessage);


    }

}
