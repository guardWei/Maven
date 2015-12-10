package guard.service;

import java.util.List;

import guard.model.User;

public interface UserServiceI {
	/**
	 * 根据id查询用户
	 * @author guard
	 * @param id
	 * @return
	 */
   public User getUserById(Long id);
   /**
    * 获取所有的用户
    * @author guard
    * @version 2015年12月10日15:16:16
    */
   public List<User> getAllUsers();
   /**
    * 查询用户时同时查询用户的课程
    * @author guard
    * @version 2015年12月10日15:57:37
    */
   public List<User> getAllUsersWithCourses();
}
