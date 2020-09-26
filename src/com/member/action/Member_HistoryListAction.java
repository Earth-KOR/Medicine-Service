package com.member.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import com.member.db.MyPageDAO;
import com.member.db.MypageBean;

public class Member_HistoryListAction{
	
	public void history_getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Member_HistoryAction : history() 실행!");
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String UserID = (String)session.getAttribute("id");
		
		
		
		
		
		if(UserID != null){
			
			MyPageDAO dao = new MyPageDAO();
			JSONArray History_list = dao.Mypage_History_getList(UserID);
			
			if(History_list.size() > 0){
				response.setContentType("application/x-json; charset=UTF-8");
				response.getWriter().print(History_list);
				
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
