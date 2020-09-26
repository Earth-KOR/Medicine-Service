package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.Action;
import com.action.ActionForward;
import com.board.db.commentDAO;

public class commentDeleteAction implements Action{

	//어노테이션을 해야할까? 
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		System.out.println(board_num);
		System.out.println(comment_num);
		System.out.println(pageNum);
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		commentDAO cdao = new commentDAO();
		cdao.deleteComment(id, comment_num, board_num);
		//cdao.deleteComment(re_seq, comment_num)
		//본글지워지면 지워지는 거니까 board_num이 아닐까?
		
		 ActionForward forward = new ActionForward();
			
		forward.setRedirect(false);
			
		forward.setPath("./boardContentAction.bo?pageNum="+pageNum+"&num="+board_num);
		
		return forward;
	}
	
}
