package com.member.action;

import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.Action;
import com.action.ActionForward;
import com.member.db.MemberDAO;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("MemberLoginAction execute()");
		
		// 사용자가 입력한 id, pw 파라미터 가져오기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println(id);
		// DB 작업 객체
		MemberDAO mdao = new MemberDAO();
		
		// DAO의 userCheck 메소드에서
		// 입력한 id,pw와 DB의 id,pw 비교
		int check = mdao.userCheck(id, pw);
		
		// check == 0 "비밀번호 틀림"
		if (check == 0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다. ')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			
			return null;
		
		// check == -1 "아이디 없음"
		}else if (check == -1){
			
		
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 오류')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		
		// check == 1
		// 입력한 id,pw와 DB의 id,pw가 일치할 때
		
		// 세션객체 생성
		HttpSession session = request.getSession();
		
		// 입력한 id를 세션에 저장
		session.setAttribute("id", id);
		System.out.println("세션저장");
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(true);
		
		forward.setPath("./FillMoveAction.me");
		
		return forward;
		
	}
	
	

}
