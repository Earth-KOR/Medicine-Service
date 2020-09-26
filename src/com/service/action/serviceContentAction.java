package com.service.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.service.db.serviceBean;
import com.service.db.serviceDAO;

public class serviceContentAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("serviceContentAction 시작");
		
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("num : "+num);
		
		String pageNum = request.getParameter("pageNum");
		System.out.println("pagenum : "+pageNum);
		
		serviceDAO sdao = new serviceDAO();
		
		sdao.updateReadcount(num);
		
		serviceBean sbean = sdao.getService(num);
		
		request.setAttribute("sbean", sbean);
		System.out.println("sbean1 : "+ sbean);
		
		request.setAttribute("pageNum", pageNum);
		System.out.println("pageNum2 : "+ pageNum);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./serviceContent.se");
		System.out.println("serviceContentAction 끝");
		return forward;
	}

	
	
}
