package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter, Setter, toString, eqals, hashCode
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 아규먼트로 갖는 생성자
@Builder
public class PostCreateDto {
    
    private String title; // jsp 폼안에 name속성과 맞추면 됨.
    private String content;
    private String author;
    
    // PostCreateDto 타입의 객체를 Post 타입의 객체로 변환해서 리턴. // 멤버변수를 사용해야하니까 static이면 안됨.
    public Post toEntity() {
        // return new Post(0, title, content, author, null, null); 이렇게 해도되는데 밑에처럼 build를 사용해서 씀.
        
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
    
}
