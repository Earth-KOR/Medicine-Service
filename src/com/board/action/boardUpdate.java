package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.board.db.boardBean;
import com.board.db.boardDAO;

public class boardUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("boardUpdate execute");
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		String pageNum=request.getParameter("pageNum");
		
		boardDAO bdao = new boardDAO();
		
		boardBean bean = bdao.getBoard(num);
		
		request.setAttribute("bean", bean);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward=new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("./update.bo");
		return forward;
	}

	
}
