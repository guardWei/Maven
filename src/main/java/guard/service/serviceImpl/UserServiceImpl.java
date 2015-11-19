package guard.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guard.dao.UserMapper;
import guard.model.User;
import guard.service.UserServiceI;

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

}
