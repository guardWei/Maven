package guard.other.utils.sendMail;

public class TestMial {
    public static void main(String[] args) {
		MailSendInfo mailSendInfo = new MailSendInfo();
		
		mailSendInfo.setMailServerHost("smtp.163.com");//发送者邮件服务器主机
		mailSendInfo.setMailServerPort("25");
		
		mailSendInfo.setUserName("guard_wei");//邮箱名字（18064082246也可）
		mailSendInfo.setPassword("zww373257919");//明文密码
		mailSendInfo.setFromAddress("18064082246@163.com");//必须为正确的密码
		
		mailSendInfo.setToAddress("guard_huan@163.com");
		
		mailSendInfo.setSubject("绑定邮箱验证");
		String content ="<h1>尊敬的用户，您好：您正在申请绑定邮箱，请点以下链接<a href='www.baidu.com'>请点击</a></h1>";
		mailSendInfo.setContent(content);
		
		SimleMailSender simleMailSender = new SimleMailSender();
		boolean flag = simleMailSender.sendHtmlMail(mailSendInfo);
		if(flag){
			System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}
}
