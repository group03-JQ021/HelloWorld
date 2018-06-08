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
@WebServlet("/ShowKidServlet")
public class ShowKidServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//创建biz层对象
		KidsBiz kb = new KidsBizImpl();
		//调用获取全部幼儿的方法
		Kid kid = kb.getKidById(Integer.parseInt(request.getParameter("kidId")));

		request.setAttribute("kid", kid);
		//跳转到前台页面进行数据的展示，实际上是把后台获取的数据响应出去
		request.getRequestDispatcher("kidsInfo.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
