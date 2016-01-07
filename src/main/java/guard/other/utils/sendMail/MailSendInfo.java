package guard.other.utils.sendMail;

import java.util.Properties;
/**
 * 邮件发送的消息实体类
 * @author guard
 * @version 2015年11月2日13:37:21
 */
public class MailSendInfo {
	// 发送邮件的服务器验证需要的信息
	public String mailServerHost;// 邮件服务器的IP地址
	public String mailServerPort;// 端口号

	public String fromAddress;// 邮件的发送地址
	public String userName; // 发送者（用户名）
	public String password; // 发送者密码
	public String toAddress;// 邮件的接收者
	public String subject; // 邮件主题
	public String content;// 邮件正文

	/**
	 * 获得邮件回话属性
	 * @return
	 */
	public Properties getProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", this.mailServerHost);// 发件人的邮箱服务器主机地址
		properties.put("mail.smtp.port", this.mailServerPort);// 端口号
		properties.put("mail.smtp.auth", "true");// 设置发送授权认证
		properties.put("mail.smtp.ssl", true);
		return properties;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
