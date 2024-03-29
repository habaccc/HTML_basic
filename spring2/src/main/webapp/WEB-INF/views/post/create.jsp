<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	 <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1">
       <title>Spring 2</title>
       <link 
           href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" 
           rel="stylesheet" 
           integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" 
           crossorigin="anonymous">
    </head>
    <body>
    <div class="container-fluid">
        <header class="my-2 p-5 text-center text-bg-dark">
            <h1>포스트 작성</h1>
        </header>
        
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <ul class="navbar-nav bg-light">
                <li class="nav-item">
                    <c:url var="mainPage" value="/" />
                    <a class="nav-link" href="${ mainPage }">메인 페이지</a>
                </li>
                <li class="nav-item">
                    <c:url var="postListPage" value="/post/list" />
                    <a class="nav-link" href="${ postListPage }">포스트 목록</a>
                </li>
            </ul>
        </nav>
        
        <main class="my-2">
            <div class="card">
                <form method="post">
                    <div class="card-body">
                        <div class="my-2">
                            <label for="title" class="form-label">제목</label>
                            <input type="text" class="form-control" id="title" name="title" required autofocus />
                        </div>
                        <div class="my-2">
                            <label for="content" class="form-label">내용</label>
                            <textarea id="content" class="form-control" name="content" required></textarea>
                        </div>
                        <div class="my-2">
                            <label for="author" class="form-label">작성자</label>
                            <input type="text" class="form-control" id="author" name="author" required/>
                        </div>
                    </div>
                    <div class="card-footer">
                        <input type="submit" class="form-control btn btn-outline-primary" value="작성 완료" />
                    </div>
                    
                </form>
            </div>
        </main>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" 
        	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" 
        	crossorigin="anonymous"></script>
    </div>
    </body>
</html>