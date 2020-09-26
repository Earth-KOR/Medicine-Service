package com.member.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MyPageDAO;
import com.member.db.MypageBean;

public class Member_FillAction{
   
   public void fill(HttpServletRequest request, HttpServletResponse response) throws Exception{
      System.out.println("FillAction : fill() 실행!");
      
      request.setCharacterEncoding("UTF-8");
      HttpSession session = request.getSession();
      
      String UserID = (String)session.getAttribute("id");
      String item_name = request.getParameter("param");
      String cycle = request.getParameter("cycle");
      
      MyPageDAO dao = new MyPageDAO();
      
      int item_check = dao.Mypage_item_Cehck(UserID, item_name);
      
      if( cycle.equals("") || Integer.parseInt(cycle) < 10 || Integer.parseInt(cycle) > 100){
         response.getWriter().print(2);
      }
      else{
         if( item_check == 0){
            
            MypageBean bean = dao.serach_Medicine_Info_Effect_list(item_name, UserID, cycle);
            int check = dao.Mypage_insert(bean);
            dao.History_insert(UserID, item_name);
            response.getWriter().print(check);
            
            
            
         }
         else{
            response.getWriter().print(0);
            
         }
      }
      
      
      
      
   }
   
}