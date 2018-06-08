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
@WebServlet("/FuzzySearchServlet")
public class FuzzySearchServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//创建biz层对象
		KidsBiz kb = new KidsBizImpl();
		String name = request.getParameter("kidname");
		
		System.out.println("kid name is " + name);
		//调用获取全部幼儿的方法
		List<Kid> list;
		if (name == null){
			
			list = kb.getAllKids();
		}else{
			list = kb.fuzzySearch(name);
		}

		request.getSession().setAttribute("list", list);
		//跳转到前台页面进行数据的展示，实际上是把后台获取的数据响应出去
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
