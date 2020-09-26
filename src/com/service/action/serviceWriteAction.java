package com.service.action;


import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.service.db.serviceBean;
import com.service.db.serviceDAO;

public class serviceWriteAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      System.out.println("serviceWriteAction");
      System.out.println("serviceWriteAction execute");
      
      request.setCharacterEncoding("UTF-8");
      
      MultipartRequest multi = null;
      
      int fileSize = 1000*1024*1024;
      
      

      ServletContext context = request.getSession().getServletContext();

      
      String savePath = context.getRealPath("/upload");
      
      
      System.out.println("save : " + savePath);
       
      try{
         multi = new MultipartRequest(request, 
                               savePath,
                               fileSize,
                               "UTF-8",
                               new DefaultFileRenamePolicy());
         System.out.println("try 통과");
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
      
      serviceBean sbean = new serviceBean();
            
      sbean.setService_id(multi.getParameter("id"));
      sbean.setService_pw(multi.getParameter("pw"));
      sbean.setService_subject(multi.getParameter("subject"));
      sbean.setService_content(multi.getParameter("content"));
      sbean.setService_file(file2);
      
      System.out.println(multi.getParameter("id"));
      System.out.println(multi.getParameter("pw"));
      System.out.println(multi.getParameter("subject"));
      System.out.println(multi.getParameter("content"));
      System.out.println(file2);
      
            
      serviceDAO sdao = new serviceDAO();
      sdao.insertService(sbean);
      
            
      ActionForward forward=new ActionForward();
      forward.setRedirect(true);
      forward.setPath("./serviceList.se");
      return forward;
   
   }
   
   

}