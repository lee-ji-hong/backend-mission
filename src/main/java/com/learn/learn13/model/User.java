package com.learn.learn13.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userName; // 사용자 이름
    private String userId; // 사용자 ID
    private String password; // 사용자 비밀번호
    private String email; // 사용자 이메일 주소
    private String tel; // 사용자 전화번호
    private String birth; // 사용자 생년월일
    private String gender; // 사용자 성별
    private String country; // 사용자 국가
    private boolean agreeTerms; // 사용자 약관 동의 여부
    private List<String> userInterest; // 사용자 관심사 목록
}
