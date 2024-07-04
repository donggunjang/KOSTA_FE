<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>부서목록</h2>
	<hr>
	<table border="1" width="80%">
	<%
		try{
			
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String username = "c##mdang";
			String password = "madang";
			
			String sql = "SELECT * FROM dept";
			
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCnt = rsmd.getColumnCount();
			
			%>
			<thead>
				<tr>
					<%
						for(int i=1; i<=colCnt; i++){
							%><th><%= rsmd.getColumnName(i) %></th><%
						}
					%>
				</tr>
			</thead>
			<tbody>
			<%
			
			
			while(rs.next()){
				%>
					<tr>
							<td><%=rs.getString(1)%></td>
							<td><%=rs.getString(2)%></td>
							<td><%=rs.getString(3)%></td>
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