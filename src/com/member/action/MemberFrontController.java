package com.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;


public class MemberFrontController extends HttpServlet{

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
		
		System.out.println("Member_controller 실행완료!");
		System.out.println("RequstURL : " + RequestURI);
		System.out.println("contextPath : " + contextPath);
		System.out.println("command : " + command);
		
		
		// header.jsp에서 join 눌렀을 때
		if ( command.equals("/main.me")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./main.jsp");
		}
		else if(command.equals("/MemberJoin.me")){
			forward = new ActionForward();
			
			forward.setRedirect(false);
			
			forward.setPath("./member/join.jsp");
			
		// join.jsp에서 회원가입 눌렀을 때
		}else if(command.equals("/MemberJoinAction.me")){
			
			action = new MemberJoinAction();
			
			try {
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		// heeder.jsp 에서 Login 클릭했을 때	
		}
		else if(command.equals("/MemberLogin.me")){
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/login.jsp");
		
		// login.jsp에서 로그인 버튼 눌렀을 때
		}else if(command.equals("/MemberLoginAction.me")){
			
			action = new MemberLoginAction();
			
			try {
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		// header.jsp에서 logout 눌렀을 때	
		} else if (command.equals("/MemberLogout.me")){
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/MemberUpdateAction.me")) {
			
			action = new MemberUpdateAction();
			
			try {
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			// duplicate
		} else if (command.equals("/MemberDuplicateAction.me")) {
			action = new MemberDuplicateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// findId
		} else if (command.equals("/MemberIdFindAction.me")) {
			MemberIdFindAction id = new MemberIdFindAction();
			try {
				id.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// findPw
		} else if (command.equals("/MemberPwFindAction.me")) {
			MemberPwFindAction pw = new MemberPwFindAction();
			try {
				pw.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// deleteMember
		}  else if (command.equals("/MemberDeleteAction.me")) {
			MemberDeleteAction delete = new MemberDeleteAction();
			
			try {
				delete.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/mypage.me")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("member/mypage.jsp"); 
			
		}else if (command.equals("/myModify.me")){
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("member/myModify.jsp"); 
			
		}else if (command.equals("/myDelete.me")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("member/myDelete.jsp"); 
			
			
		}else if (command.equals("/MemberMypageAction.me")) {
			action = new MemberMypageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/MemberModifyAction.me")) {
			action = new MemberModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/findId.me")){
			forward = new ActionForward();

			forward.setRedirect(false);

			forward.setPath("member/findId.jsp");
			
		}else if (command.equals("/findPw.me")){
			forward = new ActionForward();

			forward.setRedirect(false);

			forward.setPath("member/findPw.jsp");
			
		}else if(command.equals("/searchMain.me")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./search/searchMain.jsp");

		}
		else if(command.equals("/pharmacy.me")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./index.html");
			
		}
		else if(command.equals("/pill.me")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./pill/pill.jsp");
		}
		else if(command.equals("/board.me")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/board.jsp");
		}
		else if (command.equals("/Member_Mypage_ListAction.me")) {
			try {
				Member_Mypage_ListAction mypage = new Member_Mypage_ListAction();
				mypage.Mypage_getList(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/Member_HistoryListAction.me")) {
			try {
				Member_HistoryListAction history = new Member_HistoryListAction();
				history.history_getList(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/FillAction.me")) {

			try {
				
				Member_FillAction fill = new Member_FillAction();
				fill.fill(request, response);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/FillMoveAction.me")) {
			
			try {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./pill/pill.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/Member_Mypage_GraphAction.me")) {
			
			try {
				action = new Member_Mypage_GraphAction();
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/Member_Mypage_GraphAction_re.me")) {
			
			try {
				Member_Mypage_Graph_reAction re = new Member_Mypage_Graph_reAction();
				re.execute(request, response);
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./graph.me");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/Member_Mypage_deleteAction.me")) {
			
			try {
				Member_Mypage_deleteAction del = new Member_Mypage_deleteAction();
				del.Mypage_delete(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/graph.me")) {
			
			try {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./member/graph.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/mesg.me")) {
			
			try {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./member/a.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/MemberMypage.me")) {
			
			try {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./member/mypage.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/MemberHistory.me")) {
			
			try {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./member/history.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/changePw.me")){

			try {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./member/changePw.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/news.me")){
			
			try {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./board/news.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/RealChat.me")) {
			forward = new ActionForward();
			forward.setPath("/chat/chat.jsp");
			
		} 	else if (command.equals("/MemberPwCheckAction.me")) {
			try {
			MemberPwCheckAction memberpwcheckaction = new MemberPwCheckAction();
			memberpwcheckaction.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MemberPwChangeAction.me")){

			try {
				MemberPwChangeAction change = new MemberPwChangeAction();
				change.execute(request, response);
			
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
		System.out.println("Member_controller 실행 끝!");
		System.out.println("=====================================");
	}
	
	
	
}
