package com.mengka.demo.controller;

import java.util.Date;
import java.util.UUID;
import com.mengka.demo.response.HoverflyServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author mengka
 * @date 2017/09/18.
 */
@Slf4j
@RestController
public class MyRestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/service/hoverfly")
    public HoverflyServiceResponse getSampleResponse() {
        log.info("Inside HoverflyActualServiceApplication::getSampleResponse()");
        return new HoverflyServiceResponse("returned value from HoverflyActualServiceApplication", new Date().toString(), UUID.randomUUID().toString());
    }

    /**
     *  http://127.0.0.1:8080/invoke
     *
     * @return
     */
    @RequestMapping("/invoke")
    public String invoke() {
        System.out.println("inside TestController::invoke()");
        String url = "http://localhost:8073/service/hoverfly";
        String response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {
                }).getBody();
        System.out.println("Actual Response : " + response);
        return response;
    }
}
