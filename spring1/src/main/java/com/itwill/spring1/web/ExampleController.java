package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.spring1.dto.UserDto;

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
    public String home(Model model) { // 컨트롤러에서 뷰로 전달하려면 모델을 사용하면 됨.
        log.info("home()"); // lombok이라는 라이브러리가 로그라는 변수를 자동으로 만들어줌. 그래서 log를 따로 변수로 선언하지 않아도 사용할 수 있음.
        
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("now", now); // view에 전달할 데이터를 세팅.
        // el로 now(왼쪽) 변수값을 쓰면 옆의 now(오른쪽)을 사용할 수 있음.
        
        return "index"; // 어떤 jsp파일로 갈건지 파일이름만 리턴하면 됨. view의 이름
    } 
    
    @GetMapping("/ex1") // 디스패처 서블릿한테 /ex1 요청 주소를 등록하는 것.
    public void example1() {
        log.info("example1() 호출");
        // 컨트롤러 메서드가 void인 경우(뷰의 이름을 리턴하지 않는 경우)
        // 요청 주소의 이름이 뷰의 이름이 됨.
    }
    
    @GetMapping("/ex2")
    public void getParamEx(String username, int age) { // 리퀘스트 파라미터와 이름이 같아야 함.
        log.info("getParamEx(username={}, age={})", username, age);
        // 컨트롤러 메서드를 선언할 때 파라미터의 이름을 요청 파라미터 이름과 같게 선언하면,
        // DispatcherServlet이 컨트롤러 메서드를 호출하면서 요청 파라미터의 값들을
        // argument로 전달해줌!
    }
    
    @PostMapping("/ex3")
    public String getParamEx2(
            @RequestParam(name = "username") String name, // 변수 이름을 리퀘스트 파라미터의 값과 맞출 수 없다면 리퀘스트파라미터 애너테이션을 사용하면 됨.
            @RequestParam(defaultValue = "0") int age // int값이 비어지면 exception이 발생하는데 그것을 방지하기 위해서 디폴트로 처리함.
    ) {
     // 컨트롤러 메서드를 선언할 때, 파라미터 선언 앞에 @RequestParam 애너테이션을 사용하는 경우:
        // (1) 메서드 파라미터와 요청 파라미터 이름이 다를 때, name 속성으로 요청 파라미터 이름을 설정.
        // (2) 요청 파라미터 값이 없거나 비어있을 때, defaultValue 속성 설정.
        log.info("getParamEx2(name={}, age={})", name, age); 
        
        return "ex2"; // 리턴타입을 String으로 한 이유는. 만들어져있는 jsp를 활용할 수 있음.
    }
    
    @GetMapping("/ex4")
    public String getParamEx3(UserDto user) {
        log.info("getParamEx2(user={})", user);
        // DispatcherServlet은 컨트롤러의 메서드를 호출하기 위해서,
        // 1. 요청 파라미터들을 분석(request.getParameter()).
        // 2. UserDto의 기본 생성자를 호출해서 객체를 생성.
        // 3. 요청 파라미터들의 이름으로 UserDto의 setter 메서드를 찾아서 호출.
        // 4. UserDto 객체를 컨트롤러 메서드를 호출할 때 argument로 전달.
        
        return "ex2";
    }
    
    @GetMapping("/sample")
    public void sample() {
        log.info("sample()");
    }
    
    @GetMapping("/forward") //url에 value에 있는 값과 같아야함.
    public String forwardTest() {
        log.info("forwardTest()");
        
        // 컨트롤러 메서드에서 다른 페이지(요청 주소)로 forward하는 방법:
        // "forward:"으로 시작하는 문자열을 리턴하면,
        // DispatherServlet은 리턴값이 뷰의 이름이 아니라 포워드 방식으로 이동할 페이지 주소로 인식.
        return "forward:/sample";
    }
    
    @GetMapping("/redirect")
    public String redirectTest(RedirectAttributes attrs) { // 
        log.info("redirectTest()");
        
        // 컨트롤러 메서드에서 리다이렉트되는 페이지까지 유지되는 정보를 저장할 때:
        attrs.addFlashAttribute("redAttr", "테스트");
        
        // 컨트롤러 메서드에서 다른 페이지(요청 주소)로 redirect하는 방법:
        // "redirect:"으로 시작하는 문자열을 리턴하면,
        // DispatherServlet은 리턴값이 뷰의 이름이 아니라 리다이렉트 방식으로 이동할 페이지 주소로 인식.
        return "redirect:/sample";
    }
}   

