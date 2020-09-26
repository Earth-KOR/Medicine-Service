package com.service.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;



public class serviceFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("serviceFrontController 시작");
		
		String RequestURI=request.getRequestURI(); //url
		System.out.println(RequestURI);
		
		String contextPath=request.getContextPath(); //Model, ContextPath 
		System.out.println(contextPath);
		System.out.println(contextPath.length());

		String command=RequestURI.substring(contextPath.length()); 
		System.out.println(command);
		
		ActionForward forward = null;
		Action action = null;
		System.out.println("forward : !!");
		System.out.println("action : @@");
		
		if(command.equals("/serviceList.se")){
			System.out.println("serviceList");
			action=new serviceSearchListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				System.out.println("serviceList 오류 "+e);
				e.printStackTrace();
			}
		
		}else if(command.equals("/serviceWrite.se")){
			System.out.println("serviceWrite.se");
			
			forward = new ActionForward();

			forward.setRedirect(false);

			forward.setPath("./service/write.jsp");
			System.out.println(forward+"write.jsp");
		
		}else if(command.equals("/serviceWriteAction.se")){
			action=new serviceWriteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				System.out.println("serviceWriteAction 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/serviceContent.se")){

			forward = new ActionForward();

			forward.setRedirect(false);

			forward.setPath("./service/content.jsp");
			System.out.println(forward+"content.jsp");
		
		}
		else if(command.equals("/checkAction.se")){

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./service/checkpw.jsp");
			
		}
		else if(command.equals("/checkPwAction.se")){
			action=new checkPwAction();
			try {
				forward=action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		else if(command.equals("/serviceContentAction.se")){
			action=new serviceContentAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				System.out.println("serviceContentAction 오류");
				e.printStackTrace();
			}
			
		
		}else if(command.equals("/serviceDelete.se")){
			action = new serviceDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				System.out.println("serviceContentAction 오류");
				e.printStackTrace();
			}	
		
		}else if(command.equals("/serviceUpdate.se")){
				 
				action=new serviceUpdate();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if(command.equals("/serviceUpdateAction.se")){
				
				action=new serviceUpdateAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
		}else if(command.equals("/serviceSearchList.se")){
			action=new serviceSearchListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/serviceReWrite.se")){
			
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./service/reWrite.jsp");
			
		}else if(command.equals("/serviceReWriteAction.se")){
		
			action=new serviceReWriteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			
		}
		
		if(forward!=null){
			//true
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
				
			//false	
			}else{
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				
				
				System.out.println("serviceFrontController 끝");
			}
		}
	}
	
}
