package com.learn.learn13.mapper;

import com.learn.learn13.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    List<User> findAllUsers();
    User findUserById(String userId);
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(String userId);
    int insertUserInterest(Map map);
    int deleteUserInterest(String userId);
}
