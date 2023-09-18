package com.campingreservationbackend.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private final static int CONNECT_TIMEOUT = 3000;
    private final static int READ_TIMEOUT = 3000;

    private final static int DEFAULT_MAX_PER_ROUT = 10;
    private final static int MAX_TOTAL = 30;


    public RestTemplate setRestTemplate(int defaultMaxPerRoute, int maxTotal){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        connectionManager.setMaxTotal(maxTotal);

        HttpClient client = HttpClientBuilder.create().setConnectionManager(connectionManager).build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
        factory.setConnectTimeout(CONNECT_TIMEOUT);
        factory.setReadTimeout(READ_TIMEOUT);

        return new RestTemplate(factory);
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return setRestTemplate(DEFAULT_MAX_PER_ROUT,MAX_TOTAL);
    }

}
