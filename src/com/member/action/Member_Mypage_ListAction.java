package com.member.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import com.member.db.MyPageDAO;

public class Member_Mypage_ListAction {

	
	public void Mypage_getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Member_Mypage_ListAction : Mypage_getList() 실행!");
	
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String UserID = (String)session.getAttribute("id");
		
		if(UserID != null){
			MyPageDAO dao = new MyPageDAO();
			
			JSONArray Mypage_list = dao.Mypage_medicine_getList(UserID);
			System.out.println("mypage : " + Mypage_list.size());
			
			
			if(Mypage_list.size() > 0){
				response.setContentType("application/x-json; charset=UTF-8");
				response.getWriter().print(Mypage_list);
				
			}
			else{
				response.getWriter().print(2);
			}
		}
		else{
			response.getWriter().print(1);
		}
		
		
	}
	
}
