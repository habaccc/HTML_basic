package com.itwill.spring3.repository.post;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.spring3.dto.PostUpdateDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

//    @Test
    public void testSelectAll() {
        List<Post> list = postRepository.findByOrderByIdDesc();
        for (Post p : list) {
            log.info(p.toString());
        }
    }

//    @Test
    public void testSave() {
        // DB 테이블에 insert할 레코드(엔터티)를 생성:
        Post entity = Post.builder()
                .title("JPA 테스트")
                .content("JPA 라이브러리를 사용한 INSERT")
                .author("admin")
                .build();
        log.info("insert 전: {}", entity);
        log.info("created: {}, modified: {}",
                entity.getCreatedTime(), entity.getModifiedTime());
        
        // DB 테이블에 insert:
        postRepository.save(entity);
        //-> save 메서드는 테이블에 삽입할 엔터티를 파라미터에 전달하면,
        // 테이블에 저장된 엔터티 객체를 리턴.
        //-> 파라미터에 전달된 엔터티 필드들을 변경해서 리턴.
        
        log.info("insert 후: {}", entity);
        log.info("insert 후: {}, modified: {}", 
                entity.getCreatedTime(), entity.getModifiedTime());
    }
    
//    @Test
    public void update() {
        // 업데이트하기 전의 엔터티를 검색:
        Post entity = postRepository.findById(2L).orElseThrow(); 
        // orElseThrow - 아이디로 검색하는 것은 WHERE구문이 들어감. 일치하는 아이디가 있으면 null이 아니고 없으면 null이기 
        // 때문에(값이 있을 수도있고 없을수도 있기 때문에 optional) post타입이 아니고 optional이기 때문에 post타입으로 바꿔줘야함.
        // (바꿔주려면 orElseThrow를 써야함) exception을 던질지 말지
        log.info("update 전: {}", entity);
        log.info("update 전 수정 시간: {}", entity.getModifiedTime());
        
        // 엔터티를 변경할 내용을 가지고 있는 객체 생성:
        PostUpdateDto dto = new PostUpdateDto();
        dto.setTitle("JPA update 테스트");
        dto.setContent("JPA Hibernate를 사용한 DB 테이블 업데이트");
        
        // 엔터티를 수정:
        entity.update(dto);
        
        // DB 테이블 업데이트:
        // JPA에서는 insert와 update 메서드가 구분되어 있지 않음.
        // save() 메서드의 아규먼트가 DB에 없는 엔터티이면 insert, DB에 있는 엔터티이면 update를 실행.
        postRepository.saveAndFlush(entity);
    }
    
    @Test
    public void testDelete() {
        long count = postRepository.count(); // DB 테이블
        log.info("삭제 전 count = {}", count);
        
        postRepository.deleteById(81L); // return 타입이 void이기 때문에 결과를 저장할 수 없음.
        
        count = postRepository.count();
        log.info("삭제 후 count = {}", count);
    }

}
