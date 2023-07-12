<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Tag</title>
</head>
<body>
   <h3>회원정보</h3>
   <p>아이디 : ${member.id}</p>
   <p>비밀번호 : ${member.passwd}</p>
   <p>거주지 : ${member.city}</p>
   <p>성 별 : ${member.sex}</p>
   <p>취 미 :
      <c:forEach items="${member.hobby}" var="hobby">
         [<c:out value="${hobby}" />]
      </c:forEach>
   </p>
</body>
</html>