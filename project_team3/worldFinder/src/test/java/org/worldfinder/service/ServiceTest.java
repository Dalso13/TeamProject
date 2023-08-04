package org.worldfinder.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.worldfinder.mapper.MainMapper;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:WEB-INF/spring/root-context.xml")
public class ServiceTest {

    @Autowired
    private MainService service;

    @Test
    public void tests(){
        service.readCountry();
    }
}
