package guard.hibernate.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guard.hibernate.dao.BaseDaoI;
import guard.hibernate.entity.User;
import guard.hibernate.service.UserServiceI;

@Service("userServiceH")
public class UserServiceImpl implements UserServiceI {

	private BaseDaoI<User> userDao;

	public BaseDaoI<User> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<User> userDao) {
		this.userDao = userDao;
	}


	@Override
	public User getUserById(Long id) {
		String hql = "from User where id= '"+id+"'";
		return userDao.get(hql);
	}

}
