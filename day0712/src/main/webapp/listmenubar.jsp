<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원정보</h2>
	<hr>
	<%
		String name = "홍길동";
		int age = 20;
	%>
	
	<h1>이름: <%= name %></h1>
	<h2>나이: <%= age %></h2>

</body>
</html>