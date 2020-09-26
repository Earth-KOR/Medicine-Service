package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.db.MemberDAO;
import com.member.db.MemberDTO;


public class MemberPwFindAction /*implements Action*/ {

	
	public /*ActionForward*/ void  execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
	
		boolean check ;
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		
		MemberDAO md = new MemberDAO();
		MemberDTO mdto = new MemberDTO();
		
		mdto.setId(id);
		mdto.setEmail(email);
        
		check = md.idCheck(id,email);
		System.out.println(check);
		
		//ActionForward forward = new ActionForward();
		//forward.setAjax(true);
		System.out.println("12354");
		int result = 0; // 0: 실패, 1: 성공, -1: 메일 발송에 실
	
		if(check == false) {
			response.getWriter();
		}else{
		if ( mdto.getEmail() != null) {
			System.out.println("123");
			String rePw = md.rePw();
			mdto.setPw(rePw);
			System.out.println(rePw);
			result = md.updatePw(mdto);
			System.out.println(result);
			if (result == 1) { 
				System.out.println("1234");
				String subject = "YAKBBAL 임시비밀번호 입니다.";
				String content = mdto.getId() + "님의 임시 비밀번호는" + rePw + "입니다. <br> ";//바로 연결주소를 쓸까고민이야. 문자로 임시비밀번호 보내는 서비스는 어떻게 하는 걸까? 
				result = md.sendEmail(email, subject, content); 
				if(result != 1) {
					System.out.println("1235");
					response.getWriter().println(-1);
				}
			}
		}
	}
		response.getWriter().println(result);
		
		//return forward;
	}
	
}
