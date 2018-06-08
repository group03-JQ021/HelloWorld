package com.jxdedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxdedu.biz.KidsBiz;
import com.jxdedu.biz.impl.KidsBizImpl;
@WebServlet("/TestValidateServlet")
public class TestValidateServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		
		KidsBiz biz = new KidsBizImpl();
		PrintWriter out = response.getWriter();
		String msg = "出错了!";
		if (biz.getNameCount(name) == 0){
			msg = "姓名可用!";
		}else{
			msg = "姓名不可用!";
		}
				
		out.print(msg);
	}

}
