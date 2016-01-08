package guard.mybatis.service;

import java.util.List;

import guard.mybatis.model.User;

public interface UserServiceI {
	/**
	 * 根据id查询用户
	 * @author guard
	 * @param id
	 * @return
	 */
   public User getUserById(Long id);
   /**
    * 根据id查询用户,同时查出课程
    * @author guard
    * @version 2015年12月22日17:46:39
    * @param id
    * @return
    */
   public User getUserByIdWithCourses(Long id);
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
   /**
    * 保存用户信息
    * @author guard
    * @version 2015年12月15日19:25:03
    */
   public void saveUser(User user);
}
