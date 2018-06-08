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


@WebServlet("/AutoLoginCheckServlet")
public class AutoLoginCheckServlet extends HttpServlet {
	
	/**
	 * 在给定的Cookie数组中搜索指定名字的Cookie, 并返回对应的value
	 * 如果数组为空或没有找到,则返回 null;
	 * @param cookies
	 * @param name
	 * @return
	 */
	static String charSet = "utf-8";
	private String searchCookie(Cookie[] cookies, String name){
		System.out.print("search-"+name);
		String value = null;
		if (cookies == null || name == null || name.length() == 0){
			return value;
		}
		
		for (Cookie c:cookies){
			if (c.getName().equals(name)){
				try {
					value = java.net.URLDecoder.decode(c.getValue(), charSet);
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		System.out.println(":"+value);
		return value;
	}
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException{
		
		// 检验是否有记住用户名与密码的Cookie
		String name = searchCookie(request.getCookies(), LoginServlet2.unameCookieName);
		String pwd = searchCookie(request.getCookies(), LoginServlet2.pwdCookieName);
		
		if (name != null && pwd != null){
			System.out.println("remember you, auto login");
			name = java.net.URLDecoder.decode(name, "utf-8");
			pwd = java.net.URLDecoder.decode(pwd, "utf-8");
			if(LoginServlet2.validLogin(name, pwd)){
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
				response.sendRedirect("GetAllKidsServlet");
				
			}else{
				request.setAttribute("loginMsg", "用户名或密码错误");
				//转向登录页面继续登录
				request.getRequestDispatcher("login2.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("auto_login_check", "checked");
			request.getRequestDispatcher("/login2.jsp").forward(request,response);
//			response.sendRedirect("login2.jsp");
		}
		
	}
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException{
		this.doPost(request, response);
		
	}

}
