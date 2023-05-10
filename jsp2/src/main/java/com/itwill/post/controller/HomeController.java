package com.itwill.post.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(name = "homeController", urlPatterns = { "" }) // { "/" } 이라고 하면 / 뒤에 뭐가 붙어도 다 처리한다는 듰
// urlPatterns = { "" }는 웹의 url주소가 달라지기 때문에 "http://localhost:8081/post/" 까지를 처리하겠다는 뜻.
// "http://localhost:8081/post/" 요청주소(context root)를 처리하는 서블릿.
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		System.out.println("homeController.doGet() 호출");
		
		// View로 요청을 포워드:
		request.getRequestDispatcher("/WEB-INF/main.jsp")
		        .forward(request, response);
		// 받은 요청을 forward(전달)
	}
	//was가 동작 중인 상태이고 요청을 보냄. 요청이 감. 웹서버는 요청주소를 보고서 서블릿클래스를 찾음.
}
