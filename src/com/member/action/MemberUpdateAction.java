package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.Action;
import com.action.ActionForward;
import com.member.db.*;


public class MemberUpdateAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdateAction execute()");
		
		request.setCharacterEncoding("utf-8");
		
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String my_Pill = request.getParameter("my_Pill");
		String my_Bujakyong = request.getParameter("my_Bujakyong");
		String my_Hospital = request.getParameter("my_Hospital");
		
		MemberDTO mdto = new MemberDTO();
		
		mdto.setId(id);
		mdto.setPw(pw);
		mdto.setEmail(email);
		mdto.setPhone(phone);
		mdto.setMy_Pill(my_Pill);
		mdto.setMy_Bujakyong(my_Bujakyong);
		mdto.setMy_Hospital(my_Hospital);
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.updateMember(mdto);
		

		if (result == 1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정완료')");
			out.println("</script>");
			
			ActionForward forward = new ActionForward();
			
			forward.setRedirect(false);
			forward.setPath("./MemberMypageAction.me");
			
			return forward;
			
		}else{
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 오류')");
			out.println("history.back()");
			out.println("</script>");
		
			return null;
		}
	}
}
