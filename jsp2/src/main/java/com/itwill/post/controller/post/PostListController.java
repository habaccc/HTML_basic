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
 * Servlet implementation class PostListController
 */
@WebServlet(name = "postListController", urlPatterns = { "/post" }) // web.xml을 다 지우고 애너테이션으로 다시 만들어줌.
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostListController.class);
	
	private final PostService postService = PostService.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
//	    System.out.println("postListController.doGet()");
	    log.info("doGet()");
	    
	    // 서비스 계층의 메서드를 호출해서 포스트 목록을 불러옴.
	    List<Post> post = postService.read();
	    
	    // 포스트 목록을 JSP에게 전달.
	    request.setAttribute("posts", post);
	    
	    // 톰캣이 doget을 호출하면서 request 아규먼트를 넣어줌.
	    request.getRequestDispatcher("/WEB-INF/post/list.jsp")
	            .forward(request, response);
	}

}
