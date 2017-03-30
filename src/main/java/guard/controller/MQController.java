package guard.controller;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import guard.mybatis.model.User;
import guard.mybatis.service.UserServiceI;

@Controller
@RequestMapping("/mq")
public class MQController {
	@Autowired
	private UserServiceI userServiceI;
	
	@Autowired
	private JmsTemplate jmsQueueTemplate;
	
	@Autowired
	private JmsTemplate jmsTopicTemplate;
	
	@Autowired
	private Destination userQueueDestination;
	
	@Autowired
	private Destination userTopicDestination;
	
	@RequestMapping("/sendMq")
	public void sendMQ(String id){
		final User u = userServiceI.getUserById(Long.parseLong(id));
		//发送Queue类型消息
		jmsQueueTemplate.send(userQueueDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(JSON.toJSONString(u));
			}
		});
		//发送Topic类型消息
		jmsTopicTemplate.send(userTopicDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(JSON.toJSONString(u));
			}
		});
	}
}
