package com.itwill.post.controller.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class PostListController
 */
@WebServlet(name = "postListController", urlPatterns = { "/post" }) // web.xml을 다 지우고 애너테이션으로 다시 만들어줌.
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    System.out.println("postListController.doGet()");
	    
	    // 톰캣이 doget을 호출하면서 request 아규먼트를 넣어줌.
	    request.getRequestDispatcher("/WEB-INF/post/list.jsp")
	            .forward(request, response);
	}

}
