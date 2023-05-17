package com.itwill.spring1.dto;

import lombok.Data;

// DTO(Data Transfer Object): 계층 간의 데이터 전달을 위한 객체.
// DispatcherServlet <=== Data ===> Controller
// Controller <=== Data ===> Service

// VO(Value Object): 값을 저장하기 위한 객체.
// 데이터베이스의 테이블 구조를 자바 클래스로 표현한 객체.
@Data
public class UserDto {
    // 폼에서 전달한 요청 파라미터 값들을 저장하기 위한 클래스.
    
    private String username; 
    private int age;
 // 리퀘스트 파라미터와 이름이 다르면 호출 할 수 없으니 요청 파라미터 이름만 잘 맞춰주어서 변수만 잘 설정하면 dispatcherServlet이 다 해줌.
    
}
