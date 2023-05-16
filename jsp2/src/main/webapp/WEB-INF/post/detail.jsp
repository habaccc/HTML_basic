<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
       <meta charset="UTF-8">
       <title>Post</title>
    </head>
    <body>
        <header>
            <h1>포스트 상세보기 페이지</h1>
            <h2>${ posts.title }</h2>
        </header>
        
        <nav>
            <ul>
                <li>
                    <c:url value="/user/signout" var="signOut"/>
                    <span>${ signedInUser }</span>
                    <a href="${ signOut }">로그아웃</a>
                </li>
                <li>
                    <c:url value="/" var="mainPage" />
                    <a href="${ mainPage }">메인 페이지</a>
                </li>
                <li>
                    <c:url value="/post" var="postList" />
                    <a href="${ postList }">포스트 목록 페이지</a>
                </li>
                <%-- 로그인 사용자 아이디와 글 작성자 아이디가 같은 경우에만 수정 메뉴를 보여줌. --%>
                <c:if test="${ signedInUser == posts.author }">
                    <li>
                        <c:url value="/post/modify" var="postModify">
                            <c:param name="id" value="${ posts.id }" ></c:param>
                        </c:url>
                        <a href="${ postModify }">포스트 수정</a>
                    </li>
                </c:if>            
            </ul>
        </nav>
        <main>
            <c:url value = "/post/detail" var = "postDetail" />
            <form action = "${ postDetail }" method = "posts">
                <div>
                    <input type="text" value="${ posts.id }" readonly />
                </div>
                <div>
                    <input type = "text" name = "title" value = "${ posts.title }" readonly />
                </div>
                <div>
                    <textarea rows="5" cols="80" name="content" readonly >${ posts.content }</textarea>
                </div>
                <div>
                    <input type = "text" name = "author" value = "${ posts.author }" readonly/>
                </div>
                <div>
                    <input type = "text" value = "${posts.createdTime}" readonly />
                </div>
                <div>
                    <input type = "text" value = "${posts.modifiedTime}" readonly />
                </div>
            </form>
        </main>
    </body>
</html>