package com.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.member.action.MemberJoinAction;

public class boardFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 가상 요청 주소 가져오기
		String RequestURI = request.getRequestURI();
				
		// 프로젝트 Team4 얻기
		String contextPath = request.getContextPath();
				
		// /MemberJoin.me 얻기
		String command = RequestURI.substring(contextPath.length());
				
		ActionForward forward = null;
		Action action = null;
				
		System.out.println("board_controller 실행완료!");
		System.out.println("RequstURL : " + RequestURI);
		System.out.println("contextPath : " + contextPath);
		System.out.println("command : " + command);
		
		if(command.equals("/board.bo")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/board.jsp");
		}

		else if(command.equals("/write.bo")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/write.jsp");
		}
		
		else if(command.equals("/content.bo")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/content.jsp");
		}
		else if(command.equals("/update.bo")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/update.jsp");
		}
		else if(command.equals("/boardSearch.bo")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/boardSearch.jsp");
		}
		else if(command.equals("/boardWriteAction.bo")){
			
			action = new boardWriteAction();
			
			try {
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/boardListAction.bo")){
			
			action = new boardListAction();
			
			try {
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/boardContentAction.bo")){
			
			action = new boardContentAction();
			
			try {
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/boardUpdate.bo")){
			
			action = new boardUpdate();
			
			try {
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/boardUpdateAction.bo")){
			
			action = new boardUpdateAction();
			
			try {
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/boardDeleteAction.bo")){
			
			action = new boardDeleteAction();
			
			try {
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/boardSearchList.bo")){
			
			action = new boardSearchList();
			
			try {
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//comment
				else if(command.equals("commentWirte.bo")){
					forward = new ActionForward();
					forward.setRedirect(false);
					forward.setPath("./board/content.jsp");
				}
				
				else if(command.equals("commentUpdate.bo")){
					forward = new ActionForward();
					forward.setRedirect(false);
					forward.setPath("./board/content.jsp");
				}
				
				else if(command.equals("commentDelete.bo")){
					forward = new ActionForward();
					forward.setRedirect(false);
					forward.setPath("./board/content.jsp");
				}
				
				else if(command.equals("commentList.bo")){
					forward = new ActionForward();
					forward.setRedirect(false);
					forward.setPath("./board/content.jsp");
				}
		
				else if(command.equals("/commentWriteAction.bo")){
					System.out.println("writeaction 실행");
					action = new commentWriteAction();
					
					try {
						
						forward = action.execute(request, response);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
		else if(command.equals("/commentUpdateAction.bo")){
					
					commentUpdateAction update = new commentUpdateAction();
			
					
					try {
						
						update.execute(request, response);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			else if(command.equals("/commentDeleteAction.bo")){
				
				action = new commentDeleteAction();
				
				try {
					
					forward = action.execute(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
			else if(command.equals("/commentListAction.bo")){
				
				
				commentListAction list = new commentListAction();
				
				try {
					
					list.execute(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		

		
		
		
		
		if (forward != null){
			if(forward.isRedirect()){
				
				response.sendRedirect(forward.getPath());
				
			}else{
				
				RequestDispatcher dispatcher 
					= request.getRequestDispatcher(forward.getPath());
				
				dispatcher.forward(request, response);
				
			}
		}
		
		System.out.println("board_controller 실행 끝!");
		System.out.println("=====================================");
	
	}
	
}
