package com.service.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.service.db.serviceBean;
import com.service.db.serviceDAO;

public class serviceUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("serviceUpdateAction 시작");
		
		request.setCharacterEncoding("utf-8");
		
		String pageNum=request.getParameter("pageNum");
		
		serviceBean sbean = new serviceBean();
		
		sbean.setService_id(request.getParameter("id"));
		System.out.println("id : "+request.getParameter("id"));
		
		sbean.setService_pw(request.getParameter("pw"));
		System.out.println("pw : "+request.getParameter("pw"));
		
		sbean.setService_content(request.getParameter("content"));
		System.out.println("content : "+request.getParameter("content"));
		
		sbean.setService_subject(request.getParameter("subject"));
		System.out.println("subject : "+request.getParameter("subject"));
		
		sbean.setService_num(Integer.parseInt(request.getParameter("num")));
		System.out.println("num : "+Integer.parseInt(request.getParameter("num")));
		
		serviceDAO sdao = new serviceDAO();
		
		int check = sdao.updateService(sbean);
		
		System.out.println(" if문 시작 : check : "+check);
		if(check==0){// 비번 틀림
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else if(check==-1){//num없음
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('num없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else{//수정 성공
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>"); 
		out.println("alert('수정성공');");
		out.println("location.href='./serviceList.se'");
		out.println("</script>");
		out.close();
		System.out.println("if문 끝 : check : "+check);
		System.out.println("serviceUpdateAction 끝");
		return null;
	}

		
	}
	
	
	
	
}
