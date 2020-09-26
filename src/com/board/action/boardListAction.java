package com.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.board.db.boardBean;
import com.board.db.boardDAO;

public class boardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("boardListAction execute");
		
		boardDAO bdao = new boardDAO();
		
		int count = bdao.getboardCount();
		
		System.out.println(count);
		
		int pageSize = 5;
		
		String pageNum=request.getParameter("pageNum");
		
		System.out.println(pageNum);
		if(pageNum==null){
			
			pageNum="1";
			
		}
		
		int currentPage = Integer.parseInt(pageNum);
	
		int startRow = (currentPage-1) * pageSize + 1;
	
		int endRow = currentPage * pageSize;
		
		List<boardBean> boardList = null;
		
		if(count != 0){
			boardList = bdao.getboardList(startRow, pageSize);
		}
			// 전체 페이지 수
			int pageCount =count / pageSize + (count % pageSize == 0? 0:1);
					
			// 한 화면에 보여줄 페이지 수
			int pageBlock = 3;
			
			// 한 화면에 보여줄 시작 페이지
			int startPage=((currentPage-1)/pageBlock) * pageBlock + 1;
			
			// 한 화면에 보여줄 끝 페이지
			int endPage = startPage + pageBlock - 1;
			if(endPage > pageCount){
				endPage = pageCount;
			}
			
			boardList = bdao.getboardList(startRow, pageSize);
			
			request.setAttribute("boardList", boardList);
			request.setAttribute("count", count);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/board.jsp");
			
			return forward;
		
	}
	
}
