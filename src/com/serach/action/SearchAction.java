package com.serach.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.action.Action;
import com.action.ActionForward;
import com.search.db.serachDAO;

public class SearchAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./test.do");
		
		return forward;
	}

	public void serach_medicine(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		serachDAO dao = new serachDAO();
		
		
		System.out.println("SerachAction : search_name()메소드 실행 !");
		ArrayList<String> test_name = dao.search_medicine(request.getParameter("param"));
		
		
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(test_name);
	}
	
	public void serach_medicineName(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		serachDAO dao = new serachDAO();
		
		
		System.out.println("SerachAction : search_name()메소드 실행 !");
		int check = dao.search_medicineName(request.getParameter("param"));
		
		
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(check);
	}
	
}
