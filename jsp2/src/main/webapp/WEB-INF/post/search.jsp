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
            <h1>포스트 검색 결과 페이지</h1>
        </header>
        <nav>
            <ul>
                <li>
                    <c:url var="mainPage" value="/"></c:url>
                    <a href="${ mainPage }">메인 페이지</a></li>
                <li>
                <li>
                    <c:url value="/post" var="postList" />
                    <a href="${ postList }">포스트 목록 페이지</a>
                </li>
            </ul>
        </nav>
        <main>
            <h2>검색 결과: ${ keyword }</h2>
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>수정시간</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ posts }" var="post">
                        <tr>
                            <td>${ post.id }</td>
                            <td><c:url value="/post/detail" var="postDetail">
                                    <c:param name="id" value="${ post.id }"></c:param>
                                </c:url> <a href="${ postDetail }">${ post.title }</a>
                            </td>
                            <td>${ post.author }</td>
                            <td>${ post.modifiedTime }</td>
                        </tr>
                    </c:forEach>
                </tbody>
    
            </table>
            <c:url value="/post/search" var="searchPage"></c:url>
            <form action="${ searchPage }">
                <select name="category">
                    <option value="t" ${ category == 't' ? 'selected' : '' }>제목</option>
                    <option value="c" ${ category == 'c' ? 'selected' : '' }>내용</option>
                    <option value="tc"
                        ${ category == 'tc' ? 'selected' : '' }>제목 + 내용</option>
                    <option value="a" ${ category == 'a' ? 'selected' : '' }>작성자</option>
                </select> <input type="text" name="keyword" value="${ keyword }"
                    placeholder="검색어" required autofocus /> 
                    <input type="submit" value="검색" />
            </form>
        </main>
    </body>
</html>