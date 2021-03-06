package com.jxdedu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jxdedu.biz.KidsBiz;
import com.jxdedu.biz.impl.KidsBizImpl;
import com.jxdedu.entity.Kid;
@WebServlet("/PagingShowKidsServlet")
public class PagingShowKidsServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//创建biz层对象
		KidsBiz kb = new KidsBizImpl();
		HttpSession session = request.getSession();
		
		int pageSize = Integer.parseInt(request.getParameter("pageSize").toString());
		int currentPage = Integer.parseInt(request.getParameter("currentPage").toString());

		
		System.out.println("get page:" + currentPage + ":" + pageSize);
		
		//调用获取全部幼儿的方法
		List<Kid> list = kb.getPage(currentPage, pageSize);
		request.setAttribute("list", list);
		
		session.setAttribute("pageCount", kb.getPageCount(pageSize));
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("pageSize", pageSize);

		//跳转到前台页面进行数据的展示，实际上是把后台获取的数据响应出去
		request.getRequestDispatcher("pagingShow.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
