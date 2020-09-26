


package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.member.db.MemberDAO;
import com.member.db.MemberDTO;

//public class MemberPwCheckAction {
//	
//public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
//		
//		
//		boolean check;
//		String pw = request.getParameter("pw");
//		System.out.println("111111");
//		
//		MemberDAO md = new MemberDAO();
//		MemberDTO mdto = new MemberDTO();
//		
//		System.out.println("222222");
//		
//		mdto.setPw(pw);
//		
//		check = md.pwCheck(pw);
//		System.out.println(check);
//	
//		
//	}

public class MemberPwCheckAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		MemberDAO md = new MemberDAO();
		try {
			System.out.println("MemberPwCheckAction() 실행");
			resp.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = resp.getWriter();
			
			if(md.pwCheck(req.getParameter("pw")) == 1) {
				out.println(1);
			} else if (md.pwCheck(req.getParameter("id")) == 0) {
				out.println(0);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null; //ajax로 요청을 하기 때문에 페이지 이동이 필요없으므로 null값 리턴
	}


}
