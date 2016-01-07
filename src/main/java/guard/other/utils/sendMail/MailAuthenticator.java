package guard.other.utils.sendMail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 身份验证类--邮箱账户和密码的认证
 * @author guard
 * @version 2015年11月2日13:47:28
 */
public class MailAuthenticator extends Authenticator {
	public String userName = null;
	public String passWord = null;

	public MailAuthenticator(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
    /**
     * 重写父类的方法，验证用户名和密码
     */
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, passWord);
	}
}
