package com.jxdedu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxdedu.biz.KidsBiz;
import com.jxdedu.biz.impl.KidsBizImpl;
import com.jxdedu.entity.Kid;
@WebServlet("/GetAllKidsServlet")
public class GetAllKidsServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//创建biz层对象
		KidsBiz kb = new KidsBizImpl();
		//调用获取全部幼儿的方法
		List<Kid> list = kb.getAllKids();
		//后台处理程序得到的数据，为了在前台展示，必须存到某个范围对象中
		//ServletContext，如果保存这个对象中，本应用的所有web资源共享
		//request,代表一次请求，把数据保存在request，它只在一次请求中有效
		//session,代表一次会话，用户打开了浏览器与服务器建立了连接，直到关闭浏览器，
		//在这期间的操作都属于本次会话。
		request.getSession().setAttribute("list", list);
		//跳转到前台页面进行数据的展示，实际上是把后台获取的数据响应出去
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
