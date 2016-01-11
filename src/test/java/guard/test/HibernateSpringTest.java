package guard.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

import guard.hibernate.service.UserServiceI;

public class HibernateSpringTest {
	private ApplicationContext ac;
	
	private static final Logger logger = Logger.getLogger(HibernateSpringTest.class);
	
	/*@Before
	public void statics() {
	    ac = new ClassPathXmlApplicationContext(new String[] { "spring.xml", "spring-hibernate.xml" });
	    userServiceI = (UserServiceI) ac.getBean("userService");
	}*/
	@Test
	public void test(){
		/*
		 * spring-mybatis.xml需要放在后面否则它的配置将覆盖Hibernate的事务配置
		 */
		ac = new ClassPathXmlApplicationContext(new String[] { "spring.xml","spring-mybatis.xml","spring-hibernate.xml"});
		logger.info(ac);
		UserServiceI userService = (UserServiceI) ac.getBean("userServiceH");
		logger.info(JSON.toJSONStringWithDateFormat(userService.getUserById(2L),"yyyy-MM-dd"));
	}
	
}
