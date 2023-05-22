package com.itwill.spring2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostDetailDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// application-context.xml에서 <context:component-scan> 설정에서
// com.itwill.spring2.service 패키지와 그 하위 패키지를 스캔(검색)
@Service // -> 스프링 컨테이너에서 서비스 컴포넌트 객체를 생성하고 관리(필요한 곳에 주입).
@RequiredArgsConstructor // 2. (2) lombok에서 자동으로 postrepository를 아규먼트로 갖는 postservice 생성자를 자동으로 만들어줌. final로 선언된 field를 초기화하는 생성자.
@Slf4j
public class PostService {
    
    // 의존성 주입(DI: Dependency Injection):
    // 1. 필드에 의한 의존성 주입 - @Autowired 애너테이션을 사용하는 방법.
    // 2. 생성자에 의한 의존성 주입 -
    //  (1) 필드를 final로 선언 (반드시 초기화를 해야하기 때문에)
    //  (2) final 변수를 초기화할 수 있는 생성자를 작성
    
//    @Autowired private PostRepository postRepository; // 1. 필드에 의한 의존성 주입
    
    private final PostRepository postRepository; // 2. (1) 생성자에 의한 의존성 주입
    
    // 포스트 목록 페이지
    public List<PostListDto> read() {
        log.info("read()");
        
        List<Post> list = postRepository.selectOrderByIdDesc();
//        return postRepository.selectOrderByIdDesc(); // 우리가 직접 리파지토리를 생성하는게 아니라 포스트리파지토리에 메서드만 부르면 됨.
        
//        List<PostListDto> result = new ArrayList<>();
//        for(Post p : list) {
//            PostListDto dto = PostListDto.fromEntity(p);
//            result.add(dto);
//        }
//        return result;
        
        // 위에 코드를 간단하게 쓴 것
        return list.stream().map(PostListDto::fromEntity).toList(); // map에는 람다표현식을 사용하면 됨. // 이 문장은 반복문임.
    }
    
    // 포스트 상세보기 페이지
    public PostDetailDto read(long id) {
        log.info("read(id={})", id);
        
        Post entity = postRepository.selectById(id);

        return PostDetailDto.fromEntity(entity); // entity에서 dto를 만들어줌.
    }
    
    // 새 포스트 작성 페이지
    public int create(PostCreateDto dto) {
        log.info("create({})", dto);
        
        // PostCreateDto 타입을 Post 타입으로 변환해서 Repository 계층의 메서드를 호출(DB insert를 하기 위해서.) 
        return postRepository.insert(dto.toEntity());
    }
    
    // 포스트 업데이트
    public int update(PostUpdateDto post) {
        log.info("update({})", post);
        
        return postRepository.updateTitleAndUpdateContent(post.updateEntity());
    }
    
    // 포스트 삭제
    public int delete(long id) {
        log.info("delete(id={})", id);
        
        return postRepository.deleteById(id);
    }
   
}
