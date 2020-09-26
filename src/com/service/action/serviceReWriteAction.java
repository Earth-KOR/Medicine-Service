package com.service.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.service.db.serviceBean;
import com.service.db.serviceDAO;

public class serviceReWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("serviceReWriteAction 시작");
		
		request.setCharacterEncoding("utf-8");
		
		serviceBean sbean = new serviceBean();
		
		sbean.setService_num(Integer.parseInt(request.getParameter("num")));
		sbean.setService_re_ref(Integer.parseInt(request.getParameter("re_ref")));
		sbean.setService_re_lev(Integer.parseInt(request.getParameter("re_lev")));
		sbean.setService_re_seq(Integer.parseInt(request.getParameter("re_seq")));
		sbean.setService_id(request.getParameter("id"));
		sbean.setService_pw(request.getParameter("pw"));
		sbean.setService_subject(request.getParameter("subject"));
		sbean.setService_content(request.getParameter("content"));
		
		System.out.println("serviceReWriteAction sbean");
		System.out.println("num : "+Integer.parseInt(request.getParameter("num")));
		System.out.println("re_ref : "+Integer.parseInt(request.getParameter("re_ref")));
		System.out.println("re_lev : "+Integer.parseInt(request.getParameter("re_lev")));
		System.out.println("re_seq : "+Integer.parseInt(request.getParameter("re_seq")));
		System.out.println("id : "+request.getParameter("id"));
		System.out.println("pw : "+request.getParameter("pw"));
		System.out.println("subject : "+request.getParameter("subject"));
		System.out.println("content : "+request.getParameter("content"));
		System.out.println("------------------------------------------------");
		
		serviceDAO sdao = new serviceDAO();
		
		sdao.reInsertServics(sbean);
		
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./serviceList.se");		
		System.out.println("serviceReWriteAction 끝");
		return forward;
		
	}

}
