package com.kosta.web03;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateCustomer")
public class UpdateCustomer extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int custid = Integer.parseInt(request.getParameter("custid"));
		String sql = "SELECT * FROM customer WHERE custid=?";
		String driver ="oracle.jdbc.driver.OracleDriver";
		String url ="jdbc:oracle:thin:@localhost:1521:XE";
		String username = "c##mdang";
		String password = "madang";
		
		String name ="";
		String addr="";
		String phone="";
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				name = rs.getString(2);
				addr = rs.getString(3);
				phone = rs.getString(4);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception ex){
			System.out.println("예외발생: "+ex.getMessage());
		}
		
		String data = "";
		data += "<html>";
		data += "<body>";
		data += "<h2>고객수정</h2>";
		data += "<hr>";
		data += "<form action='UpdateCustomer' method='post'>";
		data += "고객번호: <span>"+custid+"</span><input type='hidden' name = 'custid' value='"+custid+"'><br>";
		data += "고객이름: <input type='text' name = 'name' value='"+name+"'><br>";
		data += "고객주소: <input type='text' name = 'addr' value='"+addr+"'><br>";
		data += "고객전화: <input type='text' name = 'phone' value='"+phone+"'><br>";
		data += "<input type='submit' value='수정'>";
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
		
		String sql ="UPDATE customer SET name=?, address=?, phone=? WHERE custid = ?";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "c##mdang";
		String password = "madang";
		int re = -1;
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, addr);
			pstmt.setString(3, phone);
			pstmt.setInt(4, custid);
			
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
