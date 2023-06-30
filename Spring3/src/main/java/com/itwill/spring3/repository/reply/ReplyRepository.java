package com.itwill.spring3.repository.reply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.spring3.repository.post.Post;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	// jpa를 이용하는 이유는 레파지토리를 만들기 귀찮아서임. jpa에 없는 것들만 따로 만들어주면 됨.
    
//	// Post id로 검색하기:
//	List<Reply> findByPostId(Long postId);
	
	// Post로 검색하기:
//	List<Reply> findByPost(Post post);
	List<Reply> findByPostOrderByIdDesc(Post post);
	
	// Post에 달린 댓글 개수:
	long countByPost(Post post);

}
