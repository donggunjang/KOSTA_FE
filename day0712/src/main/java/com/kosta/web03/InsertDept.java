package com.kosta.web03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertDept")
public class InsertDept extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = "";
		
		data += "<h2>부서등록</h2>";
		data += "<hr>";
		data += "<form action='InsertDeptOk' method='post'>";
		data += "부서번호: <input type='text' name ='dno'><br>";
		data += "부서이름: <input type='text' name ='dname'><br>";
		data += "지역: <input type='text' name ='dloc'><br>";
		data += "<input type='submit' value='등록'>";
		data += "<input type='reset' value='다시 쓰기'>";
		data += "</from>";
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(data);
		out.close();
	}
	
}
