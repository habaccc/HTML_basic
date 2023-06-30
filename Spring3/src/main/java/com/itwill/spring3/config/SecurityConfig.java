package com.itwill.spring3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration // 스프링 컨테이너에서 빈(bean)으로 생성, 관리 - 필요한 곳에 의존성 주입.
public class SecurityConfig {
    
    // Spring Security 5 버전부터는 비밀번호는 반드시 암호화를 해야 함.
    // 비밀번호를 암호화하지 않으면 HTTP 403(access denied, 접근 거부) 또는 HTTP 500(internal server error, 내부 서버 오류) 에러를 발생시킴.
    // 비밀번호 인코더(Password encoder) 객체를 bean으로 생성해야 함.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호가 똑같지않게 다르게 만들어주는 알고리즘.
    }
    
    // 로그인 할 때 사용할 임시 사용자 생성
    
    
}
