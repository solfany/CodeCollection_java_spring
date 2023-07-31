<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Tag - 회원가입</title>
</head>
<body>
   <h3>회원가입</h3>
   <form:form modelAttribute="member" method="post">
      <p>아이디: <form:input path="id" name="id" /></p>
      <p>비밀번호: <form:password path="passwd" /></p>
      <p>거주지: <form:select path="city">
         <form:option value="서울시">서울시</form:option>
         <form:option value="경기도">경기도</form:option>
         <form:option value="인천시">인천시</form:option>
         <form:option value="충청도">충청도</form:option>
         <form:option value="전라도">전라도</form:option>
         <form:option value="경상도">경상도</form:option>
      </form:select></p>
      <p>성별:
         <form:radiobutton path="sex" value="남성" />남성
         <form:radiobutton path="sex" value="여성" />여성
      </p>
      <p>취미:
         독서<form:checkbox path="hobby" value="독서" />
         운동<form:checkbox path="hobby" value="운동" />
         영화<form:checkbox path="hobby" value="영화" />
      </p>
      <p>
         <input type="submit" value="가입하기" />
         <input type="reset" value="초기화" />
      </p>
   </form:form>
</body>
</html>
<!-- 스프링 폼 태그
스프링 웹 MVC와 연동되는 태그 라이브러리 
스프링 폼 태그를 사용하는 경우 
폼에서 전달되는 파라미터 이름으로 Setter() 메서드를 작성한 클래스의 프로퍼티에 접근 가능 
컨트롤러가 다루는 데이터를 참조할 수 있어 동적 웹 앱플케이션을 더 쉽고 편리하게 개발하고 유지 가 -->