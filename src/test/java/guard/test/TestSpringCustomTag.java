package guard.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

public class TestSpringCustomTag {
    private ApplicationContext ac;

    private static final Logger logger = Logger.getLogger(TestSpringCustomTag.class);

    @Test
    public void test(){
        ac = new ClassPathXmlApplicationContext(new String[] {"spring-redis.xml"});
        logger.info(ac);
        Jedis jedis = (Jedis) ac.getBean("redis");
        System.out.println(jedis);
    }
}
