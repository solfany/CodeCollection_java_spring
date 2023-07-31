<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" 
      rel="stylesheet">
<title>도서 목록</title>
</head>
<body>
    <nav class="navbar navbar-expand  navbar-dark bg-dark"> 
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="./home">Home</a>
            </div>
        </div>
    </nav>
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">도서 목록</h1>
        </div>
    </div>
    <div class="container">
        <div class="row" align="center">
			 <c:forEach items="${bookList}" var="book">
                 <div class="col-md-4">
                    <h3>${book.name}</h3>
                    <p>${book.author}
                        <br>${book.publisher} | ${book.releaseDate}
                    <p align=left>${fn:substring(book.description, 0, 100)}...
                    <p>${book.unitPrice}원
                    <p><a href="<c:url value="/books/book?id=${book.bookId}"/>" class="btn btn-primary" role="button">상세정보 &raquo;</a> 
                </div>
            </c:forEach>
        </div>
        <hr>
        <footer>
            <p>&copy; BookMarket</p>
        </footer>
    </div>
</body>
</html>
<!--  
➊ prefix="fn"은 JSTL의 function 태그를 JSP에서 사용함을 나타냅니다.

➋ 뷰 화면을 표현하려고 부트스트랩에서 제공하는 스타일시트(bootstrap.css)의 URL을 연결하여 적용한 것입니다.

➌ JSTL의 <for Each>...</for Each> 구문을 이용한 반복문으로, 모든 도서의 목록을 출력합니다.
   ${bookList}는 BookController 컨트롤러에서 전달된 모델 데이터를 var 속성 값인 book으로 다시 정의합니다.
   book을 이용하여 모델 데이터 name, author, publisher, releaseDate, description, unitPrice 등을 출력합니다.
-->