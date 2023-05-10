<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
       <meta charset="UTF-8">
       <title>포스트</title>
    </head>
    <body>
       <header>
            <h1>포스트 목록 페이지</h1>
        </header> 
        
        <nav>
            <ul>
                <li>
                    <c:url var="mainPage" value="/"></c:url>
                    <%-- 
                    상대 경로에서 현재 폴더(./)의 의미: context root까지의 주소 
                    http://localhost:8081/post/
                    --%>
                    <a href="${ mainPage }">메인 페이지</a>
                </li>
                <li>
                    <c:url var="postCreate" value="/post/create"></c:url>
                    <a href="${ postCreate }">새 포스트 작성</a>
                </li>
            </ul>
        </nav>
    </body>
</html>
<%-- 
web-inf에 파일을 만들어 주는 이유
: webapp 밑에다가 만들었을 때는 우리가 파일의 이름을 알기때문에 입력해서 쓸 수 있지만 jsp 자바파일로 루트를 만들어내면 해킹 문제가 생김.
근데 web-inf는 직접접근을 하지 못하고 서버의 웹 어플리케이션(톰캣)(프로세서(서블릿))들만 접근할 수 있는 폴더이기때문에 파일이름을 입력해서 서버에 들어가면 들어가지지 않음.
보안문제 때문에 web-inf에서 파일을 만듦.
이미지, 자바스크립트, css 같은 건 webapp에 넣어야함. 이유는 이미지 한장마다 controller를 만들 수 없기 때문에 그냥 webapp에 넣음.
 --%>