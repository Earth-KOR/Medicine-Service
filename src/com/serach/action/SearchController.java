package com.serach.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.member.action.Member_Mypage_GraphAction;
import com.member.action.Member_Mypage_ListAction;

public class SearchController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
		
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String RequestURI = request.getRequestURI();
		// 예)  http://localhost:8080/project/list.jsp
		// [return] /project/list.jsp
		
		String contextPath = request.getContextPath();
		// 예)  http://localhost:8080/project/list.jsp
		// [return] /project
		
		String command = RequestURI.substring(contextPath.length());
		
		Action action = null;
		ActionForward forward = null;
		
		
		System.out.println("Search_controller 실행완료!");
		System.out.println("RequestURI : " + RequestURI);
		System.out.println("contextPath : " + contextPath);
		System.out.println("command : " + command);
		
		if(command.equals("/search_medicine.do")){
			
			SearchAction ser = new SearchAction();
			
			try {
				
				ser.serach_medicine(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/search_medicineName.do")){
			
			SearchAction ser = new SearchAction();
			
			try {
				
				ser.serach_medicineName(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/info_medicine.do")){
			
			try{
				Medicine_Info_ListAction list = new Medicine_Info_ListAction();
				list.search_medicine_list(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/search_effect.do")){
			try{
				Medicine_Info_ListAction list = new Medicine_Info_ListAction();
				
				list.search_effect(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/search_move.do")){
			
			try {
				action = new Search_MoveAction();
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			
			}
		
		}
		else if(command.equals("/searchMain_move.do")){
			
			try {
				Search_MoveAction move = new Search_MoveAction();
				forward = move.searchMain_move(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
		}
		else if(command.equals("/service.do")){
			
			try {
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./service/service.jsp");
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
		}
		
		else if(command.equals("/service_write.do")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./service/write.jsp");
		}
		else if(command.equals("/service_content.do")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./service/content.jsp");
		}
		
		
		if(forward!=null){ //new ActionForward()객체가 존재 하고..
			if(forward.isRedirect()){//true -> sendRedirect() 방식일떄..
				response.sendRedirect(forward.getPath());
				
			}else{//false -> forward() 방식일때...
				
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}//if 
		System.out.println("Search_controller 실행 끝!");
		System.out.println("=====================================");
	}
	
	
}
