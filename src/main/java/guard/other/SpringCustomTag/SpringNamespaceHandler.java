package guard.other.SpringCustomTag;

import guard.other.SpringCustomTag.BeanDefinitionParser.RedisBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 注册我们自定义标签的解析类
 */
public class SpringNamespaceHandler extends NamespaceHandlerSupport{
    @Override
    public void init() {
        this.registerBeanDefinitionParser("redis",new RedisBeanDefinitionParser());
    }
}
