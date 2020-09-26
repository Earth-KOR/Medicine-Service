package com.member.action;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MyPageDAO;

public class Member_Mypage_Graph_reAction{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Member_Mypage_Graph_reAction : execute() 실행!");

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		 String UserID = (String)session.getAttribute("id");
		
		String item_name = request.getParameter("item_name");
		
		MyPageDAO dao = new MyPageDAO();
		
		System.out.println("UserID : " + UserID);
		System.out.println("item_name : " + item_name);
		
		HashMap<String, String> list = dao.Mypage_GetGraph(UserID, item_name);
		
		
		int y = Integer.parseInt(request.getParameter("y"));
		String n = request.getParameter("n");
		
		dao.update_graphY(y, UserID, item_name);

		System.out.println("list : " + list);
		System.out.println("y : " + y);
		System.out.println("n : " + n);
		
		request.setAttribute("y", y);
		request.setAttribute("n", n);
		request.setAttribute("list", list);
		
			
		}
		

}
