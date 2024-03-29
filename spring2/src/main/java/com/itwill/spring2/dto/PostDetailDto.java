package com.itwill.spring2.dto;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostDetailDto {

    private long id;
    private String title;
    private String content;
    private String author;
    private Timestamp createdTime;
    private Timestamp modifiedTime;
    private long replyCount; // el에서 사용할 수 있음.
    
    // Post 타입 객체를 PostDetailDto 타입으로 변환해서 리턴.
    public static PostDetailDto fromEntity(Post entity) { // db에서 셀렉트한 포스트타입의 객체를 변환.
        
        return PostDetailDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .createdTime(Timestamp.valueOf(entity.getCreated_time()))
                .modifiedTime(Timestamp.valueOf(entity.getModified_time()))
                .build();
    }
}
