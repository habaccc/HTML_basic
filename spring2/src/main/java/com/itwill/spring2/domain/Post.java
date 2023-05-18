package com.itwill.spring2.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // lombok 기본생성자를 자동으로 만들어주는 기능
@AllArgsConstructor // 모든 필드들을 아규먼트로 전달받아서 초기화. 생성자를 자동으로 만들어줌.
@Builder
@Getter // get메서드를 추가해줌.
@Setter // set메서드를 추가해줌.
@ToString // tostring을 추가해줌.
public class Post {
    
    private long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;
    
}
