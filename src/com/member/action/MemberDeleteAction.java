package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.Action;
import com.action.ActionForward;
import com.member.db.MemberDAO;
import com.member.db.MemberDTO;


public class MemberDeleteAction  {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		//System.out.println( request.getParameter("pw"));
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		String rePw = request.getParameter("pw");
		
		System.out.println(id);
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		
		
		dto.setId(id);
		dto.setPw(rePw);
		
		int result = dao.deleteMember(dto);
		
	
		
		if(result == 0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 오류입니다. \\다시 시도해주세요.');");
			out.println("history.back();");
			out.print("</script>");

			out.close();
			
			
			
		} else { // 성공했을 때
			session.invalidate();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('탈퇴되었습니다');");
			out.println("window.close()");
			out.println("location.href='./main.me'");
			out.println("</script>");
			out.close();
		}
		
	}
	
}
