package com.mengka.demo;

import com.mengka.demo.model.Account;
import io.specto.hoverfly.junit.core.HoverflyConfig;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mengka
 * @date 2017/09/18.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DemoApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountApiFullTest {

    @Autowired
    private TestRestTemplate template;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule
            .inCaptureOrSimulationMode("account.json", HoverflyConfig.configs().proxyLocalHost()).printSimulationData();

    @Test
    public void findAccountByNumberTest() {
        Account a = template.getForObject("/accounts/number/{number}", Account.class, "1234567890");
        Assert.assertNotNull(a);
        log.info("Found account: " + a.getId());
    }
}
