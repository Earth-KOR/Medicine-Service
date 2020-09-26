<%@page import="java.io.OutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<%
      request.setCharacterEncoding("UTF-8");
   
       String path  = request.getParameter("path");
       String name  = request.getParameter("name");
       byte[] buffer = new byte[1024*8];
       
   
      try{
         String realPath = getServletContext().getRealPath("/" + path);
          
          
         response.setContentType("Application/x-msdownload");


         response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(name,"UTF-8")+ "\";");
          
         File f = new File(realPath + "\\" + name);

         FileInputStream inputStream  = new FileInputStream(f);
         
         
         OutputStream outputStream = response.getOutputStream();
         
         
         out.clear(); 
         out = pageContext.pushBody();
         
         
         
         while(true){
            int count = inputStream.read(buffer);
            if(count == -1){
               break;
            }
            outputStream.write(buffer,0,count);
         }
         
         inputStream.close();
         outputStream.close();
         
      } catch (Exception e) {
         out.print("<script>alert('오류 발생 !'); history.go(-1);</script>");
      }
      
%>




