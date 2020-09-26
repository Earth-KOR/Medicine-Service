package com.service.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.service.db.serviceBean;
import com.service.db.serviceDAO;

public class serviceListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("serviceListAction execute() 시작");
		
		serviceDAO sdao = new serviceDAO();
	
		
		int count = sdao.getserviceCount();
		
		int pageSize = 5;
		
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
	
		int startRow = (currentPage-1)*pageSize+1;
	
		int endRow = currentPage*pageSize;
			
		List<serviceBean> serviceList = null;
		
		if(count!=0){
			serviceList=sdao.getserviceList(startRow, pageSize);
		}
		//전체 페이지 수 구하기  50개 글  10개씩 보여주기 => 5+0
				//                 55개 글 10개씩 보여주기 => 5+1
		int pageCount =count/pageSize+(count%pageSize==0?0:1);
		//한화면에 보여줄 페이지수 설정
		int pageBlock=3;
		// 한화면에 보여줄 시작페이지 구하기  1~10  => 1  /  11~20 => 11
		int startPage=((currentPage-1)/pageBlock)*pageBlock+1;
		// 한화면에 보여줄 끝페이지 구하기
		int endPage=startPage+pageBlock-1;
		if(endPage > pageCount){
			endPage = pageCount;
		}
		
		
	    serviceList = sdao.getserviceList(startRow, pageSize);

		request.setAttribute("boardList", serviceList);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./service/service.jsp");
		System.out.println("serviceListAction 끝");
		return forward;
	}

}
