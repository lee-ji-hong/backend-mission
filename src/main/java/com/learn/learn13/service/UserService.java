package com.learn.learn13.service;

import com.learn.learn13.mapper.UserMapper;
import com.learn.learn13.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }

    @Transactional
    public boolean insertUser(User user) {
        // 사용자 정보 등록
        int count = userMapper.insertUser(user);

        // 관심사 정보 저장 로직 (예시)
        if (user.getUserInterest() != null) {
            for (String interest : user.getUserInterest()) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("userId", user.getUserId());
                map.put("interest", interest);
                userMapper.insertUserInterest(map);
            }
        }
        return count > 0;
    }

    @Transactional
    public boolean updateUser(User user) {
        // 사용자 정보 수정
        int count = userMapper.updateUser(user);

        // 관심사 정보 저장 로직 (예시)
        if (user.getUserInterest() != null) {
            userMapper.deleteUserInterest(user.getUserId());
            for (String interest : user.getUserInterest()) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("userId", user.getUserId());
                map.put("interest", interest);
                userMapper.insertUserInterest(map);
            }
        }
        return count > 0;
    }

    @Transactional
    public boolean deleteUser(String userId) {
        userMapper.deleteUserInterest(userId);
        return userMapper.deleteUser(userId) > 0;
    }
}