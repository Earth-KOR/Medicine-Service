package com.board.action;

import javax.mail.search.IntegerComparisonTerm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.board.db.boardBean;
import com.board.db.boardDAO;

public class boardUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("boardUpdateAction execute");
		
		request.setCharacterEncoding("utf-8");
		
		String pageNum=request.getParameter("pageNum");
		
		boardBean bean = new boardBean();
		
		bean.setId(request.getParameter("id"));
		bean.setContent(request.getParameter("content"));
		bean.setSubject(request.getParameter("subject"));
		bean.setNum(Integer.parseInt(request.getParameter("num")));
		
		boardDAO bdao = new boardDAO();
		
		bdao.updateBoard(bean);
		
		request.setAttribute("bean", bean);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("./boardListAction.bo");
		
		return forward;
	}

	
}
