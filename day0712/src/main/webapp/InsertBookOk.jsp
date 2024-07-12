<%@page import="java.sql.PreparedStatement"%>
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

	//새로운 도서등록을 위하여 사용자가 입력한 도서의 정보는
	//jsp내장 객체인 request에 실려서 온다.

	// 사용자가 요청한 문자셋은 한글임을 설정
	request.setCharacterEncoding("UTF-8");

	// 사용자가 입력한 도서번호,도서명,가격,출판사를 받아와서 변수에 저장
	
	int bookid = Integer.parseInt(request.getParameter("bookid"));
	String bookname = request.getParameter("bookname");
	int price = Integer.parseInt(request.getParameter("price"));
	String pubhlisher = request.getParameter("pubhlisher");
	
	//데이터베이스에 연결하여 실행시킬 데이터베이스 명령어 sql을 만들기.
	String sql = "insert into book value(?,?,?,?)";
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String username = "c##mdang";
	String password = "madang";
	int re = -1;
	
	try{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,username,password);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, bookid);
		pstmt.setString(2, bookname);
		pstmt.setInt(3, price);
		pstmt.setString(4, pubhlisher);
		
		re = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}catch(Exception ex){
		System.out.println("예외발생:"+ex);
		out.print("예외발생:"+ex.getMessage());
	}
	
	if(re > 0){
		System.out.println("등록성공");
		out.print("<h2>등록성공</h2>");
	}else{
		System.out.println("등록실패");
		out.print("<h2>등록실패</h2>");
	}
%>
</body>
</html>