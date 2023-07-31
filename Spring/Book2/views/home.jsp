<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Hello World!</h1>
<p> The time on the server is ${serverTime}.</p>
</body>
</html>
<!-- 

root -context. xml
뷰( JSP 웹 페이지)와 관련 없는 빈 객체를 설정
서비스, 저장소, 데이터베이스, 로그 등 웹 애플리케이션의 비즈니스 로직을 위한 컨텍스트를 설정

servlet-context. xml
뷰( JSP 웹 페이지)와 관련 있는 빈 객체를 설정 )
 컨트롤러, NultipartResolver, Interceptor, URI와 관련 설정을 담는 클래스를 설정
 
prefix 속성
	JSp 파일이 위치한 경로를 나타내는 접두어
	별도 설정이 없는 경우에 접미어로 ". isp"를 사용.
	
suffiX 속성
	뷰 이름의 뒤에 . isp'를 붙여서 jsp 파일을 나타내는 접미어로 생략 가능
-->