<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
		//사용자가 요청한 문자셋은 한글입니다.
		request.setCharacterEncoding("UTF-8");
		//사용자가 요청한 고객의 이름을 받아 온다.
		String name = request.getParameter("name");
		
		String sql = "SELECT * FROM book WHERE bookid in (SELECT bookid FROM orders WHERE custid = (SELECT custid FROM customer WHERE name = '"+name+"'))";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url ="jdbc:oracle:thin:@localhost:1521:XE";
		String username = "c##mdang";
		String password ="madang";
	%>	
		<h2> <%= name %>님이 주문한 도서 목록</h2>
		<hr>
		<table border="1" width="80%">
		<thead>
			<tr>
				<th>도서목록</th>
				<th>도서명</th>
				<th>가격</th>
				<th>출판사</th>
			</tr>
		</thead>
		<tbody>
	<%
		try{
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				%>
					<tr>
						<td><%= rs.getInt(1) %></td>
						<td><%= rs.getString(2) %></td>
						<td><%= rs.getInt(3) %></td>
						<td><%= rs.getString(4) %></td>
					</tr>
				<%
				
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception ex){
			System.out.println("예외: "+ex);
		}
	%>
	</tbody>
	</table>
</body>
</html>