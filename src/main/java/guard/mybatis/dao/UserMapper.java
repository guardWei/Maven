package guard.mybatis.dao;

import java.util.List;

import guard.mybatis.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(guard.mybatis.model.User record);

    int insertSelective(guard.mybatis.model.User record);

    guard.mybatis.model.User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> getAllUsers();
    
    List<User> getAllUsersWithCourses();
    
    User getUserByIdWithCourses(Long id);
}