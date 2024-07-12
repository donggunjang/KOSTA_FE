package com.kosta.web02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InsertBook")
public class InsertBook extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = "";
		data += "<h2>도서등록</h2>";
		data += "<hr>";
		data += "<form action='InsertBookOk' method='post'>";
		data += "도서번호:";
		data += "<input type='text' name='bookid'><br>";
		data += "도서명:";
		data += "<input type='text' name='bookname'><br>";
		data += "가격:";
		data += "<input type='text' name='price'><br>";
		data += "출판사:";
		data += "<input type='text' name='pubhlisher'><br>";
		data += "<input type='submit' value = '등록'>";
		data += "<input type='reset' value = '취소'>";
		data += "</form>";
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(data);
		out.close();
		
	}
}
