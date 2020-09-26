package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.db.MemberDAO;

public class MemberIdFindAction {
	

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		
		MemberDAO md = new MemberDAO();
		
		String id = md.findid(name, email);
	
/*		request.setAttribute("mdto", 	md.findid(name, email));
		
		
	   ActionForward forward = new ActionForward();
		
		forward.setRedirect(true);
		
		forward.setPath("./MemberIdFindAction.me");*/

		
		
		if(id == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			response.getWriter().print("이메일을 형식에 맞게 바르게 작성해주세요!");
			out.print("<script>");
			out.print("alert('이메일 형식을 바르게 입력해 주세요');");
			out.print("</script>");
			out.close();
		}
		response.getWriter().print(id);
		
	}
	
}
