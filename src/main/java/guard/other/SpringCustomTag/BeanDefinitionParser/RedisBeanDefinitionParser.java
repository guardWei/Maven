package guard.other.SpringCustomTag.BeanDefinitionParser;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;
import redis.clients.jedis.Jedis;

/**
 * 解析对应的redis的标签
 */
public class RedisBeanDefinitionParser extends AbstractSingleBeanDefinitionParser{
    @Override
    protected Class<?> getBeanClass(Element element) {
        return Jedis.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String id = element.getAttribute("id");
        String ip = element.getAttribute("ip");
        String port = element.getAttribute("port");

        //Jedis里面加入IP，Port属性
        builder.addConstructorArgValue(ip);
        builder.addConstructorArgValue(Integer.valueOf(port));
    }
}
