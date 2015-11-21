package guard.test;

import guard.model.User;
import guard.service.UserServiceI;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMybatis {

	private ApplicationContext ac;
	private UserServiceI userServiceI;

	@Before
	public void statics() {
	    ac = new ClassPathXmlApplicationContext(new String[] { "spring.xml", "spring-mybatis.xml" });
	    userServiceI = (UserServiceI) ac.getBean("userService");
	}


	@Test
	public void testSpringMyBatis() {
		User u = userServiceI.getUserById(new Long(1));
		System.out.println(u.getName());
	}
}
