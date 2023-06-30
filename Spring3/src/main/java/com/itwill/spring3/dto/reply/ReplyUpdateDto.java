package com.itwill.spring3.dto.reply;

import lombok.Data;

@Data
public class ReplyUpdateDto {
    
    private String replyText; // 객체가 가지고 있는 key 값과 변수이름을 맞춰주면 됨. ( 클라이언트의 payload에 나타나는 키값)
    
}
