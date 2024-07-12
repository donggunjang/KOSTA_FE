package com.kosta.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Hello")
public class Hello extends HttpServlet {    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = "<h1> Hello Servlet</h1>";
		data += "<hr>";
		data += "<h1> 안녕 서블릿 </h1>";
		
		//응답방식을 설정 해 준다.
		response.setContentType("text/html;charset=UTF-8");
		
		//동적으로 생성한 html을 요청한 사용자의 브라우저에 응답(출력)하기 위하여 스트림을 얻어 온다.
		PrintWriter out = response.getWriter();
		
		//출력스트림 out을 통해서 html을 응답한다.(출력한다)
		out.print(data);
		
		//스트림을 닫아 준다.
		out.close();
	}
}