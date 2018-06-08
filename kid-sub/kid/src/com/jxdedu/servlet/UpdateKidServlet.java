package com.jxdedu.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxdedu.biz.KidsBiz;
import com.jxdedu.biz.impl.KidsBizImpl;
import com.jxdedu.entity.Kid;
@WebServlet("/UpdateKidServlet")
public class UpdateKidServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求体的编码字符集
		request.setCharacterEncoding("utf-8");
		//获取请求参数
		int kno = Integer.parseInt(request.getParameter("kno"));
		String kname = request.getParameter("kname");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String hobby = request.getParameter("hobby");
		//把日期字符串转换为日期类型
		Date bir = Date.valueOf(birthday);
		//创建一个kids对象，利用用户提交的数据
		Kid kid = new Kid();
		kid.setKno(kno);
		kid.setKname(kname);
		kid.setSex(sex);
		kid.setBirthday(bir);
		kid.setHobby(hobby);
		//调用业务逻辑层进行幼儿的添加
		KidsBiz kb = new KidsBizImpl();
		boolean flag = kb.updateKid(kid);
		//对业务逻辑层的处理结果进行判断
		if(flag){
			//用响应流无论响应的内容多少都是给客户端一个新的页面
			//response.getWriter().print("add is success");
			//在添加完一个信息后，继续回到添加页面，让用户添加信息
			//后台利用请求对象，设置一些数据
			request.setAttribute("msg", "更新成功");		
//			response.sendRedirect("ShowKidServlet?kidId="+kno);
//			response.sendRedirect("GetAllKidsServlet");
		}else{
			request.setAttribute("msg", "更新失败");
//			request.getRequestDispatcher("showKid.jsp").forward(request, response);
			//response.getWriter().print("add is fail");
			//在添加完一个信息后，即使失败也应该回到添加页面
		}
		//把要转发到的页面作为参数，获取转发对象，再利用它的forword方法进行转发。
		request.getRequestDispatcher("GetAllKidsServlet").forward(request, response);
		
	}

}
