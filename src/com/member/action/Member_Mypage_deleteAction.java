package com.member.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MyPageDAO;

public class Member_Mypage_deleteAction{
	
	public void Mypage_delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Member_Mypage_deleteAction : Mypage_delete() 실행!");

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		 String UserID = (String)session.getAttribute("id");
		String item_name = request.getParameter("item_name");
		
		MyPageDAO dao = new MyPageDAO();
		int check = dao.Mypage_delete(UserID, item_name);
		
		response.getWriter().print(check);
	}

}
