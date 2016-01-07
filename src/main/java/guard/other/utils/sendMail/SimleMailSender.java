package guard.other.utils.sendMail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件发送的核心类
 * @author guard
 * @version 2015年11月2日14:01:32
 */
public class SimleMailSender {
	/**
	 * 邮件发送的核心方法
	 * @param mailSendInfo
	 * @return
	 */
	public boolean sendHtmlMail(MailSendInfo mailSendInfo) {
		//校验身份
        MailAuthenticator mailAuthenticator = new MailAuthenticator(mailSendInfo.getUserName(), mailSendInfo.getPassword());
        Properties properties = mailSendInfo.getProperties();//装的是主机地址和端口号
        
        //根据邮件的会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(properties, mailAuthenticator);
        
        //发送邮件的主体
        
        try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			
			//创建发送者地址
			Address from = new InternetAddress(mailSendInfo.getFromAddress());
			//创建邮件消息的发送者
			mailMessage.setFrom(from);
			
			//创建接受者地址
			Address to = new InternetAddress(mailSendInfo.getToAddress());
			//创建邮件消息的接收者
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			
			//邮件发送时间
			mailMessage.setSentDate(new Date());
			
			//邮件主题
			mailMessage.setSubject(mailSendInfo.getSubject());
			
			//创建一个容器
			Multipart multipart = new MimeMultipart();
			
			//创建一个包含html的MimeMultipart
			BodyPart bodyPart = new MimeBodyPart();
			
			//设置html邮件的内容
			bodyPart.setContent(mailSendInfo.getContent(),"text/html;charset=utf-8");
			
			multipart.addBodyPart(bodyPart);//将html内容设置到容器里
			//设置邮件内容
			mailMessage.setContent(multipart);
			
			//发送
			Transport.send(mailMessage);
			return true;
		} catch (Exception e) {
           e.printStackTrace();
		}
		return false;
	}
}
