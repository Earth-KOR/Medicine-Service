package com.board.action;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.board.db.boardBean;
import com.board.db.boardDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class boardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("boardWriteAction() 실행");
		
		request.setCharacterEncoding("UTF-8");
		
		MultipartRequest multi = null;
		
		int fileSize = 1000*1024*1024;

		ServletContext context = request.getSession().getServletContext();

	      
	    String savePath = context.getRealPath("/upload");
	    System.out.println(savePath);
	    
	    try{
	         multi = new MultipartRequest(request, 
	                               savePath,
	                               fileSize,
	                               "UTF-8",
	                               new DefaultFileRenamePolicy());
	         
	      }catch (Exception e){
	         System.out.println("file upload 실패 : "+e);
	         e.printStackTrace();
	    }
	            
	      Enumeration<?> e = multi.getFileNames(); 
	      String file = "";
	      String file2 = "";
	      
	      while (e.hasMoreElements()) {
	         file = (String)e.nextElement();
	         
	         file2 = multi.getFilesystemName(file);
	    }
	      
	    
	    boardBean bean = new boardBean();
	    
	    bean.setId(multi.getParameter("id"));
	    bean.setSubject(multi.getParameter("subject"));
	    bean.setContent(multi.getParameter("content"));
	    bean.setFile(file2);
	    
	    boardDAO bdao = new boardDAO();
	    bdao.insertBoard(bean);

		ActionForward forward = new ActionForward();
		
		forward.setRedirect(true);
		
		forward.setPath("./boardListAction.bo");
		
		return forward;
	}

	
}
