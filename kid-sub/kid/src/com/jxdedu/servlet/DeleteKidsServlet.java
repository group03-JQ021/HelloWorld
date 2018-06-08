package com.jxdedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxdedu.biz.KidsBiz;
import com.jxdedu.biz.impl.KidsBizImpl;
import com.jxdedu.entity.Kid;
@WebServlet("/DeleteKidsServlet")
public class DeleteKidsServlet extends HttpServlet {


	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求体的编码字符集
		request.setCharacterEncoding("utf-8");
		//获取请求参数
		String checkIds = request.getParameter("checkedIds");
		
		// 分解字符串,获得id列表
		String[] ids_str = checkIds.split(",");
		//?? 如果servlet 出现未捕获异常,服务器会怎样处理?
		// 就比如,下方的字符串转数字就有可能失败
		int[] ids = new int[ids_str.length];
		for (int i = 0; i< ids.length; i++){
			ids[i] = Integer.parseInt(ids_str[i]);
		}
		//调用业务逻辑层进行幼儿的添加
		KidsBiz kb = new KidsBizImpl();
		
		// 删除
		//对业务逻辑层的处理结果进行判断
		if(kb.deleteKids(ids)){
			//用响应流无论响应的内容多少都是给客户端一个新的页面
			//response.getWriter().print("add is success");
			//在添加完一个信息后，继续回到添加页面，让用户添加信息
			//后台利用请求对象，设置一些数据
			request.setAttribute("msg", "删除成功");
			request.getRequestDispatcher("/GetAllKidsServlet").forward(request, response);
		}else{
			request.setAttribute("msg", "删除失败");
			//response.getWriter().print("add is fail");
			//在添加完一个信息后，即使失败也应该回到添加页面
		}
		//把要转发到的页面作为参数，获取转发对象，再利用它的forword方法进行转发。
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
		
	}


}
