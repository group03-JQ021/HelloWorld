package com.jxdedu.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException{
			
		
		request.getSession().removeAttribute("uname");
		request.getSession().invalidate();
		//转向登录页面继续登录
		response.sendRedirect("login.jsp");
	}
	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException{
		this.doPost(request, response);
	}

}
