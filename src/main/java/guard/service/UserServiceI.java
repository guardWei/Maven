package guard.service;

import guard.model.User;

public interface UserServiceI {
	/**
	 * 根据id查询用户
	 * @author guard
	 * @param id
	 * @return
	 */
   public User getUserById(Long id);
}
