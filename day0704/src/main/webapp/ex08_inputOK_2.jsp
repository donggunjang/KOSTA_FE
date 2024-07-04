
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
		
		String sql = "INSERT INTO member(no, name, addr, age, email, pwd, hobby, gender, bloodType, job, food, info) VALUES(seq_member.nextval, '"+name+"', '"+addr+"',"+age+", '"+email+"', '"+pwd+"', '"+hobby2+"', '"+gender+"', '"+bloodType+"', '"+job+"','"+food+"','"+info+"')";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "c##kosta1234";
		String password = "kosta1234";
		
		try{
			Connection conn = null;
			Statement stmt = null;
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			stmt = conn.createStatement();
			int re = stmt.executeUpdate(sql);
			
			if(re>0){
				System.out.println("성공");
				%> <b>데이터베이스에 저장 되었습니다.</b><%
			}else{
				System.out.println("실패");
				%> <b>데이터베이스 저장에 실패 했습니다.</b><%
			}
			
			stmt.close();
			conn.close();
		}catch(Exception ex){
			System.out.println("예외: "+ex);
		}
	%>
	
</body>
</html>