package org.worldfinder.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.worldfinder.domain.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:WEB-INF/spring/root-context.xml")
public class mapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void tests(){
        UserVO vo = mapper.getUser("admin1234");

       log.info(vo.toString());
    }
    
    
}

