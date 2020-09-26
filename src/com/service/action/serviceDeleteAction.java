package com.service.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.service.db.serviceDAO;

public class serviceDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("serviceDeledeAction 시작");
		String  num = request.getParameter("num");
		int lev = Integer.parseInt(request.getParameter("lev"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		int ref = Integer.parseInt(request.getParameter("ref"));
		int check = 3 ;
		int Page_num = Integer.parseInt(num);
		serviceDAO sdao = new serviceDAO();
		
		
		System.out.println("page_num : " + Page_num);
		System.out.println("lev : " + lev);
		System.out.println("seq : " + seq);
		
		
		check = sdao.deleteService(ref, lev, Page_num,seq);
		
		if (check == 0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시물 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			
		}else if (check == 1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시물 삭제 성공!')");
			out.println("location.href='./serviceList.se'");
			out.println("</script>");
			out.close();
		}

		System.out.println("serviceDeledeAction 끝");
		return null;
	}
	
	

}
