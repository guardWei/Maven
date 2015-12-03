package guard.test;

import guard.model.User;
import guard.service.UserServiceI;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

public class TestMybatis {

	private ApplicationContext ac;
	private UserServiceI userServiceI;
	
    private static final Logger logger = Logger.getLogger(TestMybatis.class);
    
	@Before
	public void statics() {
	    ac = new ClassPathXmlApplicationContext(new String[] { "spring.xml", "spring-mybatis.xml" });
	    userServiceI = (UserServiceI) ac.getBean("userService");
	}


	@Test
	public void testSpringMyBatis() {
		User u = userServiceI.getUserById(new Long(1));
		logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd"));
	}
}
