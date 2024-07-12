package com.kosta.web02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertBookOk")
public class InsertBookOk extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 폼 양식에서 새로운 도서등록을 위하여 입력한 내용을 갖고 doPost로 옵니다.
		// 이때 사용자의 요청정보는 request에 실려서 온다.
		// request를 통해서 사용자가 요청한 정보(도서번호,도서명,출판산,가격을 받아올 수 있다.)
		// 받아 오기전에 요청한 문자셋이 한글임을 먼저 설정한 후에 데이터를 받아와야 한다.
		
		// 요청한 문자셋이 한글임을 설정.
		request.setCharacterEncoding("UTF-8");
		
		// 새로운 도서 등록을 위하여 요청한 데이터를 받아와서 변수에 저장
		// 요청한 데이터는 모두 doPost메소드의 매개변수 request에 시려 있다.
		int bookid = Integer.parseInt(request.getParameter("bookid"));
		String bookname = request.getParameter("bookname");
		int price = Integer.parseInt(request.getParameter("price"));
		String pubhlisher = request.getParameter("pubhlisher");
		
		String sql = "insert into book values(?,?,?,?)";
		
		String driver ="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "c##mdang";
		String password = "madang";
		
		int re = -1;
		
		try {
			
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
			System.out.println(ex);
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(re>0) {
			out.print("도서등록 성공");
		}else {
			out.print("도서등록 실패");
		}
		
		out.close();
	}

}
