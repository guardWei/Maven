package guard.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import guard.model.User;
import guard.service.UserServiceI;

public class TestMybatis {
	
	@Before
	public void statics(){
		
	}
    @Test
	public void testSpringMyBatis() {
       ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
       UserServiceI userServiceI = (UserServiceI) ac.getBean("userService");
       User u = userServiceI.getUserById(new Long(1));
       System.out.println(u.getName());
	}
}
