package com.itwill.spring3.dto;

import com.itwill.spring3.repository.post.Post;

import lombok.Data;

@Data
public class PostUpdateDto {
    
    private String title;
    private String content;
    
    public Post updateEntity() {
       return Post.builder()
               .title(title)
               .content(content)
               .build();
    }

}
