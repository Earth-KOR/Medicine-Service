


package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.member.db.MemberDAO;

public class MemberDuplicateAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		MemberDAO md = new MemberDAO();
		try {
			System.out.println("MemberDuplicateAction() 실행");
			resp.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = resp.getWriter();
			
			if(md.dupCheck(req.getParameter("id")) == 1) {
				out.println(1);
			} else if (md.dupCheck(req.getParameter("id")) == 0) {
				out.println(0);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null; //ajax로 요청을 하기 때문에 페이지 이동이 필요없으므로 null값 리턴
	}

}
