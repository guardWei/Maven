package guard.dao;

import java.util.List;

import guard.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> getAllUsers();
    
    List<User> getAllUsersWithCourses();
    
    User getUserByIdWithCourses(Long id);
}