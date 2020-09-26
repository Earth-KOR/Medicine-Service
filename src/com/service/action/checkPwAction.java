package com.service.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.Action;
import com.action.ActionForward;
import com.service.db.serviceDAO;

public class checkPwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("testPwAction");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String search = request.getParameter("search");
		HttpSession session = request.getSession();
		int check = 1;
		
		String id = (String)session.getAttribute("id");
		String pwd = request.getParameter("pw");
		

		System.out.println("num : "+num);
		System.out.println("pageNum : "+pageNum);
		System.out.println("search : "+search);
		System.out.println("id : "+id);
		System.out.println("pwd : "+pwd);
			
		serviceDAO sdao = new serviceDAO();
		
		
		check = sdao.serviceSecret(num, id, pwd);
		System.out.println(check);
		if(check == 1){
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("serviceContentAction.se?search="+search+"&pageNum="+pageNum);
			return forward;
		}else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호를 다시 입력해주세요!')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
	
	}
	
	

}
