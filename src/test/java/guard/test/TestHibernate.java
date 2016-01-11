package guard.test;

import java.util.Date;

import org.hibernate.Session;

import guard.hibernate.entity.User;

public class TestHibernate {

	public static void main(String[] args) {

		createAndStorePerson();

		HibernateUtils.getSessionFactory().close();

	}

	private static void createAndStorePerson() {

		Session session = HibernateUtils.getSessionFactory().getCurrentSession();// 通过Session工厂获取Session对象
		session.beginTransaction(); // 开始事务

		User user = new User();

		user.setName("测试人");

		user.setAge(22);

		user.setBirthday(new Date());

		session.save(user);

		session.getTransaction().commit(); // 提交事务

	}
}
