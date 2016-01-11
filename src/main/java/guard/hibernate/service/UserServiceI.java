package guard.hibernate.service;

import guard.hibernate.entity.User;

public interface UserServiceI {
	/**
	 * 根据id查询用户
	 * @author guard
	 * @version 2016年1月11日16:05:32
	 */
	public User getUserById(Long id);
}
