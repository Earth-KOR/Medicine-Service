package com.board.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.action.Action;
import com.action.ActionForward;
import com.board.db.commentDAO;
import com.board.db.commentDTO;

public class commentWriteAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String id = request.getParameter("id");
		String com_con = request.getParameter("com_con");
		int pageNum = Integer.parseInt(request.getParameter("pageNum") );
		
		//System.out.println(board_num);
       	//System.out.println(id);
		//System.out.println(com_con);
		
		commentDAO cdao = new commentDAO();
		
		commentDTO cdto = new commentDTO();
		
		cdto.setBoard_num(board_num);
		cdto.setId(id);
		cdto.setCom_con(com_con);
		
		cdao.insertComment(cdto);
		
		
		
       ActionForward forward = new ActionForward();
		
		forward.setRedirect(true);
		
		forward.setPath("./boardContentAction.bo?pageNum="+pageNum+"&num="+board_num);
	
		return forward;
	}
}
