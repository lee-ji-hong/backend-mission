package com.learn.learn13.controller;

import com.learn.learn13.model.User;
import com.learn.learn13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    private final UserService userService;

    // 생성자를 통한 의존성 주입
    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        // UserService 의 findAllUsers 메서드를 호출하여 모든 사용자의 목록을 가져옵니다.
        List<User> users = userService.findAllUsers();
        // 가져온 사용자 목록을 ResponseEntity 객체에 담아 반환합니다.
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.findUserById(userId);
        if (user != null) {
            // 가져온 사용자 정보를 ResponseEntity 객체에 담아 반환합니다.
            return ResponseEntity.ok(user);
        } else {
            // 사용자를 찾을 수 없는 경우, NOT FOUND 상태 코드를 반환합니다.
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> setUser(@RequestBody User user) {
        try {
            // 사용자 등록 처리
            user.setPassword("1234"); // 초기 비밀번호 세팅(사용자가 최초 로그인 시 비밀번호를 변경한다고 가정)
            boolean isInserted = userService.insertUser(user);
            if (isInserted) {
                // 등록 성공 시, 생성된 사용자 정보와 함께 HTTP 상태 코드 201(CREATED) 반환
                return ResponseEntity.status(HttpStatus.CREATED).body(user);
            } else {
                // 등록 실패 시, HTTP 상태 코드 400(BAD REQUEST) 반환
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            // 서버 내부 오류 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> modifyUser(@PathVariable String userId, @RequestBody User user) {
        try {
            // 사용자 수정 처리
            user.setUserId(userId);
            boolean isUpdated = userService.updateUser(user);
            if (isUpdated) {
                // 수정 성공 시, 생성된 사용자 정보와 함께 HTTP 상태 코드 200(OK) 반환
                return ResponseEntity.ok().body(user);
            } else {
                // 수정 실패 시, HTTP 상태 코드 400(BAD REQUEST) 반환
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            // 서버 내부 오류 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable String userId) {
        try {
            // 사용자 삭제 처리
            boolean isDeleted = userService.deleteUser(userId);
            if (isDeleted) {
                // 삭제 성공 시, HTTP 상태 코드 204(NO CONTENT) 반환
                return ResponseEntity.noContent().build();
            } else {
                // 삭제 실패 시(예: 사용자를 찾을 수 없는 경우), HTTP 상태 코드 404(NOT FOUND) 반환
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // 서버 내부 오류 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
