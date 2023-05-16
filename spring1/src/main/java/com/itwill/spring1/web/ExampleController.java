package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


// POJO(Plain Old Java Object): 평범한 자바 객체
// 특정 클래스를 상속해야만 하거나, 상속 후에 메서드들을 override해야만 하는 클래스가 아님.
// 스프링 프레임워크는 POJO로 작성된 클래스를 컨트롤러로 사용할 수 있음.

@Slf4j // Logger객체(log)를 생성해줌.
@Controller // 이렇게 등록만 해주면 디스패쳐가 그다음부터는 알아서 다 해줌.
// Spring MVC 컴포넌트 애너테이션(@Component, @Controller, @Service, @Repository, ...)들 중 하나. 
// Controller 컴포넌트라는 것을 dispatcherServlet에게 알려줌.
public class ExampleController {
    
    @GetMapping("/") // GET방식의 요청 주소가 "/"(context root)로 들어오는 것을 처리하는 메서드.
    public String home(Model model) {
        log.info("home()"); // lombok이라는 라이브러리가 로그라는 변수를 자동으로 만들어줌. 그래서 log를 따로 변수로 선언하지 않아도 사용할 수 있음.
        
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("now", now);
        // el로 now(왼쪽) 변수값을 쓰면 옆의 now(오른쪽)을 사용할 수 있음.
        
        return "index"; // 어떤 jsp파일로 갈건지 파일이름만 리턴하면 됨. view의 이름
    } 
    
    @GetMapping("/ex1") // 디스패처 서블릿한테 /ex1 요청 주소를 등록하는 것.
    public void example1() {
        log.info("example1() 호출");
        // 컨트롤러 메서드가 void인 경우(뷰의 이름을 리턴하지 않는 경우)
        // 요청 주소의 이름이 뷰의 이름이 됨.
    }
    
}
