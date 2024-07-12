package com.kosta.web03;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ListCustomer")
public class ListCustomer extends HttpServlet {
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "SELECT custid, name FROM customer";
		String data = "";
		data += "<html>";
		data += "<body>";
		data += "<h2>고객목록</h2>";
		data += "<hr>";
		data += "<ul>";
		
		String driver ="oracle.jdbc.driver.OracleDriver";
		String url ="jdbc:oracle:thin:@localhost:1521:XE";
		String username = "c##mdang";
		String password = "madang";
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int custid = rs.getInt(1);
				String name = rs.getString(2);
				data+="<li><a href='DetailCustomer?custid="+custid+"'>"+name+"</a></li>";
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception ex) {
			System.out.println("예외발생: "+ex.getMessage());
		}
		
		data += "</ul>";
		data += "</body>";
		data += "</html>";
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(data);
		out.close();
	}

}
