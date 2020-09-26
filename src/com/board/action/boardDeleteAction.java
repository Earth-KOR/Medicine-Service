package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.board.db.boardDAO;

public class boardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("boardDeleteAction execute");
		
		String num = request.getParameter("num");
		int Page_num = Integer.parseInt(num);
		
		boardDAO bdao = new boardDAO();
		bdao.deleteBoard(Page_num);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./boardListAction.bo");
		
		return forward;
		
	}

	
}
