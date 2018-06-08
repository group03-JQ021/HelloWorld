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


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException{
		//获取请求参数
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		
		/*
		 * 1 判断用户名密码是否正确
		 * 2 如果正确跳转到欢迎页面去
		 * 3 如果错误回到登录页面继续登录
		 */
		
		if(isValid(name, pwd)){
			//获取会话
			HttpSession session = request.getSession();
			
			//在欢迎页面需要管理员的名字，后台需要存放一下
			session.setAttribute("uname", name);
			//在欢迎页面需要管理员的登录时间，后台需要把当前登录时间存到request中
			Date date = new Date();//利用当前时间创建日期对象
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String loginTime = sf.format(date);
			//把时间存到请求中
			session.setAttribute("loginTime", loginTime);
			
			session.setAttribute("loginFlag", "loginSucceed");
//			response.sendRedirect("GetAllKidsServlet");
			
			// paging
			session.setAttribute("currentPage", 1);
			session.setAttribute("pageSize", 2);
			response.sendRedirect("PagingShowKidsServlet?pageSize=2&currentPage=1");
	
		}else{
			request.setAttribute("loginMsg", "用户名或密码错误");
			//转向登录页面继续登录
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	static boolean isValid(String name, String pwd){
		return "abc".equals(name) && "123".equals(pwd);
	}

}
