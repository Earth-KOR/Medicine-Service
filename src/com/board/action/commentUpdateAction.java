package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.board.db.commentDAO;

public class commentUpdateAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	int comment_num = Integer.parseInt(request.getParameter("comment_num"));
	String com_con = request.getParameter("com_con");
	
	
	commentDAO cdao = new commentDAO();
	cdao.updateComment(comment_num, com_con);
	
	}
	
	
}
