package com.serach.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;

public class Search_MoveAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		
		String ser = request.getParameter("ser");
		
		System.out.println("ser : " + ser);
		
		forward.setRedirect(false);
		forward.setPath("./search/search.jsp");
		
		return forward;
	}
	
	public ActionForward searchMain_move(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./search/searchMain.jsp");
		
		return forward;
	}

}
