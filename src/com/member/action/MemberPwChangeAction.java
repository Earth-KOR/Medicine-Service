package com.member.action;

import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.Action;
import com.action.ActionForward;
import com.member.db.MemberDAO;
import com.member.db.MemberDTO;

public class MemberPwChangeAction {


	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberPwChangeAction execute()");
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		String newPw = request.getParameter("newPw");
		
		System.out.println(id);
		System.out.println(newPw);
		
		
		MemberDTO mdto = new MemberDTO();
		mdto.setId(id);
		mdto.setPw(newPw);
		
		
		MemberDAO dao = new MemberDAO();
		
		
		int result = dao.changePw(mdto);
		System.out.println(result);
		
		if (result == 1){
			session.invalidate();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 변경되었습니다.')");
			out.println("location.href='./MemberLogin.me'");
			out.println("</script>");
			
				
		
			
		}else{
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 변경에 실패했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		
		
		}
	}
	
/*	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		System.out.println(id);
		MemberDAO dao = new MemberDAO();
		
		MemberDTO mdto = dao.getMember(id);
		
		request.setAttribute("mdto", mdto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("/myModify.me");
		
		return forward;
	}*/
	
	
}
