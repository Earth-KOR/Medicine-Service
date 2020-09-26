package com.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.board.db.boardBean;
import com.board.db.boardDAO;

public class boardSearchList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("boardSearchList execute");
		
		request.setCharacterEncoding("UTF-8");
		
		String search = request.getParameter("search");
		
		boardDAO bdao = new boardDAO();
		
		int count = bdao.getBoardCount(search);
		
		int pageSize = 5;
		
		String pageNum=request.getParameter("pageNum");
		
		if(pageNum == null){
			pageNum="1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		List<boardBean> boardList = null;
		
		if(count!=0){
			boardList = bdao.getBoardSearchList(startRow, pageSize, search);
		}
		int pageCount =count / pageSize+(count % pageSize == 0? 0:1);
		
		int pageBlock = 3;
		
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		
		int endPage = startPage + pageBlock - 1;
		
		if(endPage > pageCount){
			endPage = pageCount;
		}
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("search", search);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./boardSearch.bo");
		
		return forward;
	}

	
	
}
