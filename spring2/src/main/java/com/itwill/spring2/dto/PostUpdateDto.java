package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 업데이트 요청에서 전송되는 요청 파라미터를 저장하기 위한 객체
// field 이름을 선언할 때는 요청 파라미터 이름(name 속성의 값)과 같게.
@NoArgsConstructor
@Getter
@Setter
@ToString
// 왜 Data를 사용하지 않고 getter, setter, tostring을 사용하는 지?
// 개발자는 필요에 따라 필드에 대한 접근을 제한하거나 특정 로직을 추가할 수 있기 때문에
// @Data 대신 개별적인 어노테이션인 @Getter, @Setter, @ToString을 사용하는 것
public class PostUpdateDto {
    
    private long id;
    private String title;
    private String content;
    
    // PostUpdateDto 객체를 Post 타입 객체로 변환
    public Post updateEntity() {
        
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
