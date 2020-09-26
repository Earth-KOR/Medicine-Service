package com.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.board.db.boardBean;
import com.board.db.boardDAO;
import com.board.db.commentDAO;
import com.board.db.commentDTO;

public class boardContentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("boardContentAction execute");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		String pageNum = request.getParameter("pageNum");
		
		boardDAO bdao = new boardDAO();
		commentDAO cdao = new commentDAO();
		
		bdao.updateReadcount(num);
		
		List<commentDTO> list = new ArrayList <commentDTO> () ;
		
		list = cdao.getAllComment(num);
		
		
		System.out.println("list : " + list);
		request.setAttribute("comment_list",list);
		
		
		boardBean bean = bdao.getBoard(num);
		
		request.setAttribute("bean", bean);
		
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./content.bo");
		
		return forward;
	}

	
}
