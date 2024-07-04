<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String []hobby = request.getParameterValues("hobby");
		/* String.join(",", "hobby"): []hobby의 내용을 ,로 연결해주는 매소드  */
		String hobby2 = String.join(",", hobby);
		String gender = request.getParameter("gender");
		String bloodType = request.getParameter("bloodType");
		String job = request.getParameter("job");
		String food = request.getParameter("food");
		String info = request.getParameter("info");
	%>
	취미: <%= hobby2 %><br>
	name: <%= name %><br>
	addr: <%= addr %><br>
	age: <%= age %><br>
	email: <%= email %><br>
	pwd: <%= pwd %><br>
	hobby : <%
		for(String h: hobby){
			%>
			<%=h%>&nbsp;
			<%
		}
	%><br>
	gender: <%= gender %><br>
	bloodType: <%= bloodType %>형<br>
	job: <%= job %><br>
	food: <%= food %><br>
	info<br> <textarea readonly rows="10" cols="20"><%= info %></textarea><br>
	<hr>
	<h2><%= name %>님의 정보</h2>
	<hr>
	<table border="1" width="80%">
	<thead>
		<tr>
			<th>이름</th>
			<th>주소</th>
			<th>나이</th>
			<th>이메일</th>
			<th>비밀번호</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><%=name %></td>
			<td><%=addr %></td>
			<td><%=age %></td>
			<td><%=email %></td>
			<td><%=pwd %></td>
		</tr>
	</tbody>
	</table>
</body>
</html>