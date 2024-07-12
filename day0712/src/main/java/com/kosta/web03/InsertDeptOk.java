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

import org.apache.jasper.tagplugins.jstl.core.Out;

@WebServlet("/InsertDeptOk")
public class InsertDeptOk extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int dno = Integer.parseInt(request.getParameter("dno"));
		String dname = request.getParameter("dname");
		String dloc = request.getParameter("dloc");
		
		String sql = "insert into dept values(?,?,?)";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "c##mdang";
		String password = "madang";
		
		int re = -1;
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dno);
			pstmt.setString(2, dname);
			pstmt.setString(3, dloc);
			
			re = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch(Exception ex) {
			System.out.println("예외발생:"+ex.getMessage());
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(re>0) {
			out.print("등록 성공");
		}else {
			out.print("등록실패");
		}
	}

}
