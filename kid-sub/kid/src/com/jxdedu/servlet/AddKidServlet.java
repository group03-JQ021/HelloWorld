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
@WebServlet("/AddKidServlet")
public class AddKidServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//璁剧疆璇锋眰浣撶殑缂栫爜瀛楃闆�
		request.setCharacterEncoding("utf-8");
		//鑾峰彇璇锋眰鍙傛暟
		String kname = request.getParameter("kname");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String hobby = request.getParameter("hobby");
		//鎶婃棩鏈熷瓧绗︿覆杞崲涓烘棩鏈熺被鍨�
		Date bir = Date.valueOf(birthday);
		//鍒涘缓涓�釜kids瀵硅薄锛屽埄鐢ㄧ敤鎴锋彁浜ょ殑鏁版嵁
		Kid kid = new Kid();
		kid.setKname(kname);
		kid.setSex(sex);
		kid.setBirthday(bir);
		kid.setHobby(hobby);
		//璋冪敤涓氬姟閫昏緫灞傝繘琛屽辜鍎跨殑娣诲姞
		KidsBiz kb = new KidsBizImpl();
		boolean isAdd = kb.addKid(kid);
		//瀵逛笟鍔￠�杈戝眰鐨勫鐞嗙粨鏋滆繘琛屽垽鏂�
		if(isAdd){
			//鐢ㄥ搷搴旀祦鏃犺鍝嶅簲鐨勫唴瀹瑰灏戦兘鏄粰瀹㈡埛绔竴涓柊鐨勯〉闈�
			//response.getWriter().print("add is success");
			//鍦ㄦ坊鍔犲畬涓�釜淇℃伅鍚庯紝缁х画鍥炲埌娣诲姞椤甸潰锛岃鐢ㄦ埛娣诲姞淇℃伅
			//鍚庡彴鍒╃敤璇锋眰瀵硅薄锛岃缃竴浜涙暟鎹�
			request.setAttribute("addMsg", "娣诲姞鎴愬姛");	
		}else{
			request.setAttribute("addMsg", "娣诲姞澶辫触");
			//response.getWriter().print("add is fail");
			//鍦ㄦ坊鍔犲畬涓�釜淇℃伅鍚庯紝鍗充娇澶辫触涔熷簲璇ュ洖鍒版坊鍔犻〉闈�
		}
		//鎶婅杞彂鍒扮殑椤甸潰浣滀负鍙傛暟锛岃幏鍙栬浆鍙戝璞★紝鍐嶅埄鐢ㄥ畠鐨刦orword鏂规硶杩涜杞彂銆�
		request.getRequestDispatcher("addKids.jsp").forward(request, response);
		
	}

}
