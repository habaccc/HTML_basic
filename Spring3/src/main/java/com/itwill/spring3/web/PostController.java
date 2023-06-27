package com.itwill.spring3.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring3.dto.PostCreateDto;
import com.itwill.spring3.dto.PostUpdateDto;
import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {
    
    private final PostService postService;
    
    @GetMapping
    public String read(Model model) {
        log.info("read()");
        
        // 포스트 목록 검색
        List<Post> list = postService.read();
        
        // Model의 검색 결과를 세팅:
        model.addAttribute("posts", list);
        
        return "/post/read";
    }
    
    @GetMapping("/create")
    public void create() {
        log.info("create() GET");
    }
    
        // 리턴값이 없는 경우 view의 이름은 요청 주소와 같음.
    
    @PostMapping("/create")
    public String create(PostCreateDto dto) {
        log.info("create(dto={}) POST", dto);
        
        // form에서 submit된 내용을 db테이블에 insert
        postService.create(dto);
        
        // db테이블 insert 후에는 포스트 목록 페이지로 redirect 이동.
        return "redirect:/post"; // PRG 패턴 (POST-> REDICRET -> GET 패턴)
    }
    
    // "/post/details", "/post/modify" 요청 주소들을 처리하는 컨트롤러 메서드.
    @GetMapping({"/details", "/modify"}) // 이 메서드는 요청주소 2개를 처리할 수 있게 함.
    public void read(Long id, Model model) {
        log.info("read(id={})", id);

        // POSTS 테이블에서 id에 해당하는 포스트를 검색.
        Post post = postService.read(id);

        // 결과를 model에 저장 -> 뷰로 전달됨.
        model.addAttribute("post", post);
        
        // view로 자동으로 전달(void 타입-요청주소와 같은 html을 찾음.)
        // 컨트롤러 메서드의 리턴값이 없는 경우(void인 경우).
        // 뷰의 이름은 요청주소와 같다!
        // details -> details.html, modify -> modify.html
    }
    
    @PostMapping("/modify")
    public String update(PostUpdateDto dto, long id) {
        
        postService.update(dto);
        
        return "redirect:/post";
    }
    
    @PostMapping("/delete")
    public String delete(long id) {

        postService.delete(id);

        return "redirect:/post/read";

    }
}
