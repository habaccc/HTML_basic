package com.itwill.spring3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration // 스프링 컨테이너에서 빈(bean)으로 생성, 관리 - 필요한 곳에 의존성 주입.
public class SecurityConfig {
    
    // Spring Security 5 버전부터는 비밀번호는 반드시 암호화를 해야 함.
    // 비밀번호를 암호화하지 않으면 HTTP 403(access denied, 접근 거부) 또는 HTTP 500(internal server error, 내부 서버 오류) 에러를 발생시킴.
    // 비밀번호 인코더(Password encoder) 객체를 bean으로 생성해야 함.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호가 똑같지않게 다르게 만들어주는 알고리즘.(암호화)
    }
    
    // 로그인 할 때 사용할 임시 사용자 생성
    @Bean
    public UserDetailsService inMemoryUserDetailsService() {
        // 웹서비스를 이용할 수 있는 사용자 상세정보
        UserDetails user1 = User
                .withUsername("user1") // 로그인할 때 사용할 사용자 이름
                .password(passwordEncoder().encode("1111")) // 로그인할 때 사용할 비밀번호
                .roles("USER") // 사용자 권한(USER, ADMIN, ...)ㄴ
                .build(); // UserDetails 객체 생성.
        
        UserDetails user2 = User
                .withUsername("user2") // 로그인할 때 사용할 사용자 이름
                .password(passwordEncoder().encode("2222")) // 로그인할 때 사용할 비밀번호
                .roles("USER", "ADMIN") // 사용자 권한(USER, ADMIN, ...)ㄴ
                .build(); // UserDetails 객체 생성.

        UserDetails user3 = User
                .withUsername("user3") // 로그인할 때 사용할 사용자 이름
                .password(passwordEncoder().encode("3333")) // 로그인할 때 사용할 비밀번호
                .roles("ADMIN") // 사용자 권한(USER, ADMIN, ...)ㄴ
                .build(); // UserDetails 객체 생성.
        
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
    
    // Security Filter 설정 bean
    // 로그인/로그아웃 설정
    // 로그인 페이지 설정, 로그아웃 이후 이동할 페이지.
    // 페이지 접근 권한 - 로그인해야만 접근 가능한 페이지, 로그인 없이 접근 가능한 페이지
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // Ajax POST/PUT/DELETE 요청에서 CSRF 토큰을 서버로 전송하지 않으면 403 에러가 발생.
        // 이러한 이유 때문에 CSRF 기능을 비활성화 시킴. (cross site request forgery)
        http.csrf((csrf) -> csrf.disable()); // csrf기능을 사용하지 않겠다는 뜻(csrf기능 비활성화)
        
        // 로그인 페이지 설정 - 스프링에서 제공하는 기본 로그인 페이지를 사용하겠다라는 뜻 
        http.formLogin(Customizer.withDefaults());
        
        // 로그아웃 이후 이동할 페이지 - 메인페이지
        http.logout((logout) -> logout.logoutSuccessUrl("/")); // 로그아웃하면 메인페이지로 가겠다고 설정함.
        
        // 페이지 접근 권한 설정
        /* @EnableWebSecurity 애너테이션을 사용할 경우.
        http.authorizeHttpRequests((authRequest) ->  // 람다 표현식
                // 접근권한을 설정할 수 있는 객체.
                authRequest 
                // 권한이 필요한 페이지들을 설정
                .requestMatchers("/post/create", "/post/details", "/post/delete", "/post/modify", "/post/update", "/api/reply/**")
                // 위에서 설정한 페이지들이 USER 권한을 요구함을 설정.
//                .authenticated() // 권한 여부 상관없이 아이디와 비밀번호만 일치한다면 접속 가능.
                .hasRole("USER") // 권한을 줌. user만 접속 가능하게.
                // 위페이지들 이외의 모든 페이지들은
                .anyRequest() // ** 별표 두개를 하면 모든 주소가 다 포함됨. = .requestMatchers("/**")
                .permitAll() // 권한없이 전부 접근 허용
                );*/
        
        // 첫번째 리퀘스트매쳐스는 create(글쓰기)만 로그인창이 나타나게하고(user 권한이 있어야만)
        // 두번째 리퀘스트매쳐스는 모든 주소를 허용하게 함(로그인안해도 접속 가능)
        
        // 위 방법의 단점: 새로운 요청 경로, 컨트롤러를 작성할 때마다 Config 자바 코드를 수정해야 함.
        // -> 컨트롤러 메서드를 작성할 때 애너테이션을 사용해서 접근 권한을 설정할 수도 있음.
        // (1) SecurityConfig 클래스에서 @EnableMethodSecurity 애너테이션 설정
        // (2) 각각의 컨트롤러 메서드에서 @PreAuthorize 또는 @PostAuthorize 애너테이션을 사용.
        
        return http.build();
    }
    
    
    
}
