package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그 출력
@RequiredArgsConstructor // 생성자에 의한 의존성 주입
@RequestMapping("/post") // PostController 클래스의 메서드들은 요청 주소가 "/post"로 시작.
@Controller // DispatcherServlet에게 컨트롤로 컴포턴트로 등록.
public class PostController {
    
    private final PostService postService; // 생성자에 의한 의존성 주입.
    
    @GetMapping("/list") // GET 방식의 /post/list 요청 주소를 처리하는 메서드.
    public void list(Model model) { // 뷰에다가 목록을 전달하기 위해서는 모델
        log.info("list()");
        
        // 컨트롤러는 서비스 계층의 메서드를 호출해서 서비스 기능을 수행.
        List<Post> list = postService.read();
        
        // 서비스를 수행한 결과를 뷰에 보여줄 데이터를 Model에 저장.
        model.addAttribute("posts", list);
        
        // 리턴 값이 없는 경우 뷰의 이름은 요청주소와 같음.
        // /WEB-INF/views/post/list.jsp
    }
}