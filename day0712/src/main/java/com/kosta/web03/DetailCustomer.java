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

/**
 * Servlet implementation class DetailCustomer
 */
@WebServlet("/DetailCustomer")
public class DetailCustomer extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int custid = Integer.parseInt(request.getParameter("custid"));
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(custid);
		
		String sql = "SELECT * FROM customer WHERE custid=?";
		String driver ="oracle.jdbc.driver.OracleDriver";
		String url ="jdbc:oracle:thin:@localhost:1521:XE";
		String username = "c##mdang";
		String password = "madang";
		
		String data ="";
		data += "<html>";
		data += "<body>";
		data += "<h2>고객 상세 목록</h2>";
		data += "<hr>";
		data += "<table border='1' width='80%'>";
		data += "<thead>";
		data += "<tr>";
		data += "<th>고객번호</th>";
		data += "<th>고객이름</th>";
		data += "<th>고객주소</th>";
		data += "<th>고객전화번호</th>";
		data += "</tr>";
		data += "</thead>";
		data += "<tbody>";
		data += "<tr>";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custid);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString(2);
				String addr = rs.getString(3);
				String phone = rs.getString(4);
				
				data += "<td>"+custid+"</td>";
				data += "<td>"+name+"</td>";
				data += "<td>"+addr+"</td>";
				data += "<td>"+phone+"</td>";
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception ex){
			System.out.println("예외발생: "+ex.getMessage());
		}
		data += "</tr>";
		data += "<tbody>";
		data += "</table>";
		
		data += "<br>";
		data += "<br>";
		data += "<br>";
		data += "<br>";
		data += "<br>";
		data += "<a href='UpdateCustomer?custid="+custid+"'>수정하기</a>";
		data += "</body>";
		data += "</html>";
		out.print(data);
		out.close();
	}
}
