package com.kosta.web03;

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

@WebServlet("/InsertCustomer")
public class InsertCustomer extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = "";
		data += "<html>";
		data += "<body>";
		data += "<form action='InsertCustomer' method='post'>";
		data += "고객번호: <input type='text' name = 'custid'><br>";
		data += "고객이름: <input type='text' name = 'name'><br>";
		data += "고객주소: <input type='text' name = 'addr'><br>";
		data += "고객전화: <input type='text' name = 'phone'><br>";
		data += "<input type='submit' value='등록'>";
		data += "<input type='reset' value='다시입력'>";
		data += "</form>";
		data += "</body>";
		data += "</html>";
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(data);
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int custid = Integer.parseInt(request.getParameter("custid"));
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String phone = request.getParameter("phone");
		
		String sql ="INSERT INTO customer VALUES(?,?,?,?)";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "c##mdang";
		String password = "madang";
		int re = -1;
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custid);
			pstmt.setString(2, name);
			pstmt.setString(3, addr);
			pstmt.setString(4, phone);
			
			re = pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
			
		}catch(Exception ex) {
			System.out.println("예외발생: "+ex.getMessage());
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(re>0) {
			out.print("등록성공");
		}else {
			out.print("등록실패");
		}
		
	}
}
