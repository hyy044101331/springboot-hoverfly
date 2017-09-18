package com.mengka.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author mengka
 * @date 2017/09/18.
 */
@Slf4j
@Configuration
public class AppConfig {

    private static final int HOVERFLY_PORT = 8500;
    private static final String HOVERFLY_HOST = "localhost";
    private static final String PROXY = "proxy";

    @Bean
    public RestTemplate restTemplate() {

        String mode = System.getProperty("mode");
        log.info("##################### Mode ################# " + mode);

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(HOVERFLY_HOST, HOVERFLY_PORT));
        requestFactory.setProxy(proxy);
        RestTemplate template = null;

        if (mode != null && mode.equalsIgnoreCase(PROXY)) {
            log.info("######### Running application in PROXY mode so that we can use simulated hoverfly server !!!!");
            template = new RestTemplate(requestFactory);
        } else {
            log.info("######### Running application in PRODUCTION mode so that we can use simulated hoverfly server !!!!");
            template = new RestTemplate();
        }

        return template;
    }
}
