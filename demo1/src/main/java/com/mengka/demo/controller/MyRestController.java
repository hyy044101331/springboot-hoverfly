package com.mengka.demo.controller;

import java.util.Date;
import java.util.UUID;
import com.mengka.demo.response.HoverflyServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mengka
 * @date 2017/09/18.
 */
@Slf4j
@RestController
public class MyRestController {

    /**
     *  http://127.0.0.1:8073/service/hoverfly
     *
     * @return
     */
    @RequestMapping(value = "/service/hoverfly")
    public HoverflyServiceResponse getSampleResponse() {
        log.info("Inside HoverflyActualServiceApplication::getSampleResponse()");
        return new HoverflyServiceResponse("returned value from HoverflyActualServiceApplication", new Date().toString(), UUID.randomUUID().toString());
    }
}
