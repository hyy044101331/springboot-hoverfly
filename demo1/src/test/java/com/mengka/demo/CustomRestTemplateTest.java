package com.mengka.demo;

import io.specto.hoverfly.junit.core.SimulationSource;
import io.specto.hoverfly.junit.dsl.HttpBodyConverter;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.TimeUnit;

import static io.specto.hoverfly.junit.core.HoverflyConfig.configs;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

/**
 * @author mengka
 * @date 2017/09/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class CustomRestTemplateTest {

    @Autowired
    private RestTemplate customRestTemplate;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inSimulationMode(SimulationSource.dsl(
            service("www.test.com")
                    .get("/")
                    .willReturn(success(HttpBodyConverter.json("{}")).withDelay(10, TimeUnit.SECONDS))
    ), configs().destination("www.test.com"));

    @Test
    public void connectionTimeoutTest() {
        customRestTemplate.getForObject("www.test.com", Object.class);
    }
}
