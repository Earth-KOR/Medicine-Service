package com.member.action;


import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.Action;
import com.action.ActionForward;
import com.member.db.MyPageDAO;
import com.member.db.MypageBean;

public class Member_Mypage_GraphAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Member_Mypage_GraphAction : execute() 실행!");

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		 String UserID = (String)session.getAttribute("id");
		String item_name = request.getParameter("item_name");
		
		MyPageDAO dao = new MyPageDAO();
		
		
		
		int check = dao.Mypage_get_item(item_name);
		
		
		if(check == 1){
			String db_time = dao.Mypage_getTime(UserID, item_name);
			String sub_time = dao.Mypage_getSubTime(db_time);
			HashMap<String, String> list = dao.Mypage_GetGraph(UserID, item_name);
			
			System.out.println("get :" + sub_time);
			
			list.put("time", sub_time);
			
//			dao.Mypage_time_update(UserID, item_name);
			
			request.setAttribute("list", list);

			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./graph.me");
			
			return forward;
		}
		else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('저장된 약 내역이 없습니다.');");
			out.println("location.href='./FillMoveAction.me';");
			out.println("</script>");
			out.close();
			return null;
		}
		
	}

}
