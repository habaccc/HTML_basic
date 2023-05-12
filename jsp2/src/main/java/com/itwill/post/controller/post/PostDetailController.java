package com.itwill.post.controller.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.model.Post;
import com.itwill.post.service.PostService;

/**
 * Servlet implementation class PostDetailController
 */
@WebServlet(name = "postDetailController", urlPatterns = { "/post/detail" }) 
public class PostDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/*1*/
	// 로깅 기능
	private static final Logger log = LoggerFactory.getLogger(PostDetailController.class); 
    
	/*3*/
	private final PostService postService = PostService.getInstance();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    /*2*/
	    log.info("doGet()");
	    
	    // 요청 URL의 쿼리스틞에 포함된 요청 파라미터 id(포스트 번호, PK) 값을 찾음.
	    // getParameter는 문자열만 리턴함. id는 long 타입이니까 변환을 해주어야 함.
	    String postId = request.getParameter("id");
	    long id = Long.parseLong(postId); // 파라미터 값을 문자열에서 정수형으로 변환
	    log.info("id ={}", id); // 중괄호 쓰고 중괄호에 들어갈 변수를 넣어줌.
	    
	    // DB에서 화면에 보여줄 포스트 내용을 검색
	    Post posts = postService.readById(id); // 해당 ID의 게시글을 조회
	   
	    // 뷰(JSP)에 전달.
	    request.setAttribute("posts", posts);
	    
	    // 뷰로 포워드
	    request.getRequestDispatcher("/WEB-INF/post/detail.jsp")
	            .forward(request, response);
	}

}
