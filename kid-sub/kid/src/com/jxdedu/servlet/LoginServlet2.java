package com.jxdedu.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
/**
 * 使用 Cookie 实现自动登录
 * @author xizhan
 *
 */

@WebServlet("/LoginServlet2")
public class LoginServlet2 extends HttpServlet {
	

	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException{
		//获取请求参数
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String rem = request.getParameter("remeber");
		System.out.println("-----------\nlog:" + name + pwd + rem);
		
		/*
		 * 1 判断用户名密码是否正确
		 * 2 如果正确跳转到欢迎页面去
		 * 3 如果错误回到登录页面继续登录
		 */
	
		if(validLogin(name, pwd)){
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

			if (rem!=null){ // 如果选中记住登录状态,则添加/更新 cookie
				remember(request, response, name,pwd);
			} 
			session.setAttribute("loginFlag", "loginSucceed");
			response.sendRedirect("GetAllKidsServlet");
			
	
		}else{
			request.setAttribute("loginMsg", "用户名或密码错误");
			//转向登录页面继续登录
			request.getRequestDispatcher("login2.jsp").forward(request, response);
		}
	}
	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException{
		this.doPost(request, response);
	}
	public static boolean validLogin(String name, String pwd){
		// 两个用户;
		return ("abc".equals(name) && "123".equals(pwd) 
				|| "谢静".equals(name) && "123".equals(pwd));
	}
	

	// 存放用户名和密码的cookie的名字
	static String unameCookieName = "name";
	static String pwdCookieName = "pwd";
	static void remember(HttpServletRequest request,
			HttpServletResponse response,String name, String pwd)throws IOException,ServletException{
		Cookie ckName = new Cookie (unameCookieName, URLEncoder.encode(name, "utf-8"));
		Cookie ckPwd = new Cookie(pwdCookieName, URLDecoder.decode(pwd, "UTF-8"));
		// 失效时间
		int expire = 30;
		ckName.setMaxAge(expire);
		ckPwd.setMaxAge(expire);
		System.out.println("remember:"+name+pwd);
		response.addCookie(ckName);
		response.addCookie(ckPwd);
	}
	
	

	
}
