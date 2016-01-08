package guard.mybatis.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guard.mybatis.dao.UserMapper;
import guard.mybatis.model.User;
import guard.mybatis.service.UserServiceI;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	
	
	public User getUserById(Long id) {
		return userMapper.selectByPrimaryKey(id);
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
