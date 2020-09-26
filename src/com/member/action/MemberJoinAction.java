package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.member.db.MemberDTO;
import com.action.Action;
import com.action.ActionForward;
import com.member.db.MemberDAO;

public class MemberJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("MemberJoinAction execute()");
		
		request.setCharacterEncoding("utf-8");
		
		MemberDTO mdto = new MemberDTO();
		mdto.setName(request.getParameter("name"));
		mdto.setId(request.getParameter("id"));
		mdto.setPw(request.getParameter("pw"));
		mdto.setPwCheck(request.getParameter("pwCheck"));
		mdto.setEmail(request.getParameter("email"));
		mdto.setBirth(request.getParameter("birth"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setMy_Pill(request.getParameter("my_Pill"));
		mdto.setMy_Bujakyong(request.getParameter("my_Bujakyong"));
		mdto.setMy_Hospital(request.getParameter("my_Hospital"));
		
		boolean result = false;
		
		MemberDAO mdao = new MemberDAO();
		
		result = mdao.insertMember(mdto);
		
		if(result == false){
			System.out.println("회원가입 실패");
			return null;
		}
		
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(true);
		
		forward.setPath("./MemberLogin.me");
		
		
		
		return forward;
	}

	
}
