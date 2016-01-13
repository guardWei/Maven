package guard.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guard.hibernate.dao.BaseDaoI;
import guard.mybatis.dao.UserMapper;
import guard.mybatis.model.User;
import guard.service.UserServiceI;


@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private UserMapper userMapper;
    private BaseDaoI<guard.hibernate.entity.User> userDao;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public BaseDaoI<guard.hibernate.entity.User> getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(BaseDaoI<guard.hibernate.entity.User> userDao) {
		this.userDao = userDao;
	}
	
	
	public User getUserById(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	public guard.hibernate.entity.User getUserHById(Long id) {
		return userDao.get("from User where id= '"+id+"'");
	}
	
	public User getUserByIdWithCourses(Long id) {
		return userMapper.getUserByIdWithCourses(id);
	}
	
	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}

	public List<User> getAllUsersWithCourses() {
		return userMapper.getAllUsersWithCourses();
	}

	public void saveUser(User user) {
		userMapper.insert(user);
	}

}
