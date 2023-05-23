package com.itwill.spring2.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.dto.ReplyCreateDto;
import com.itwill.spring2.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reply")
public class ReplyController {
    
//    private final ReplyService replyService = null;
    private final ReplyService replyService; // RequiredArgsConstructor 를 사용해도 됨.
    
//    @AllArgsConstructor @Data
//    public class Test {
//        private int age;
//        private String name;
//    }
//    
//    @GetMapping
//    public Test hello() {
//        log.info("hello()");
//        return new Test(16, "test");
//    } Rest 컨트롤러가 리턴하는 값은 view가 아니라 클라이언트를 리턴함.
    
    @PostMapping
    public ResponseEntity<Integer> createReply(@RequestBody ReplyCreateDto dto) {
           log.info("createReply(dto={})", dto);
           
           int result = replyService.create(dto);
           
           return ResponseEntity.ok(result);
    }
}
