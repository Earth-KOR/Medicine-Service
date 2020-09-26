package com.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;






public class boardDAO {


   Connection con = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   String sql = "";
   
   // 자원 해제 하는 메소드
      public void resourceClose() {
         try {
            if (pstmt != null)
               pstmt.close();
            if (rs != null)
               rs.close();
            if (con != null)
               con.close();
         } catch (Exception e) {
            System.out.println("자원해제 실패 : " + e);
         }
      }// resourceClose()
   
   public void getConnection() {
         
         try {
            Context init = new InitialContext();
            DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/medicine");         
            con = ds.getConnection();
            
         } catch (Exception e){
            e.printStackTrace();
            System.out.println("getConnection() 내부에서 오류 : "+e);
         }
         
      }//getConnection()


   public void insertBoard(boardBean bean) {
   
      getConnection();
      
      int num = 0;
      
   try {
      sql="select max(num) from board";
      pstmt=con.prepareStatement(sql);
      rs=pstmt.executeQuery();
      if(rs.next()){
         num = rs.getInt(1)+1; //1번열   가장큰번호+1
      }
      System.out.println("num = "+num);
      
         sql = "insert into board values (null,?,?,?,?,?,?,?,?,?,now()) ";
         pstmt = con.prepareStatement(sql);
         
         pstmt.setString(1, bean.getId());
         pstmt.setString(2, bean.getSubject());
         pstmt.setString(3, bean.getContent());
         pstmt.setString(4, bean.getFile());
         pstmt.setInt(5, num);
         pstmt.setInt(6, 0);
         pstmt.setInt(7, 0);
         pstmt.setInt(8, 0);
         pstmt.setInt(9, 0);
         pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("insertBoard SQL 오류 : "+e);
         e.printStackTrace();
      }finally{
         resourceClose();
      }
      
   }// insertBoard   
   
   public List<boardBean> getboardList(int startRow,int pageSize){
         
         List<boardBean> boardList = new ArrayList<boardBean>();
         
         try {
            getConnection();
            sql = "select * from board order by num desc, re_seq asc limit ?,?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startRow-1);
            pstmt.setInt(2, pageSize);
            rs = pstmt.executeQuery();
                     
            while(rs.next()){
               boardBean bean = new boardBean();
               bean.setContent(rs.getString("content"));
               bean.setDate(rs.getTimestamp("date"));
               bean.setFile(rs.getString("file"));
               bean.setId(rs.getString("id"));
               bean.setNum(rs.getInt("num"));
               bean.setRe_lev(rs.getInt("re_lev"));
               bean.setRe_ref(rs.getInt("re_ref"));
               bean.setRe_seq(rs.getInt("re_seq"));
               bean.setReadcount(rs.getInt("readcount"));
               bean.setSubject(rs.getString("subject"));
               bean.setComment_count(rs.getInt("comment_count"));
               boardList.add(bean);
            }
         } catch (Exception e) {
            System.out.println("getBoardList SQL 오류 : " + e);
            e.printStackTrace();
         }finally{
            resourceClose();
         }
         
         return boardList;
         
      } // getboardList()
   
   public int getboardCount() {
         
         getConnection();
         
         int count = 0;
         
         try {
            sql = "select count(*) from board";
            pstmt=con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
               count = rs.getInt(1);
            }
         
         } catch (Exception e) {
            System.out.println("getboardCount SQL 오류 : "+e);
            e.printStackTrace();
         }finally{
            resourceClose();
         }
         return count;
         
      } // getboardCount()
   
   public void updateReadcount(int num){
      
      getConnection();
      
      try {
         sql = "update board set readcount=readcount+1 where num=?";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, num);
         pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("updateReadcount SQL 오류 : "+e);
         e.printStackTrace();
      }finally{
         resourceClose();
      }
      
   } // updateReadcount()
   
   public boardBean getBoard(int num){
      
      getConnection();
      boardBean bean = new boardBean();
      try {
         
         sql = "select * from board where num=?";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, num);
         
         rs = pstmt.executeQuery();
         
         if(rs.next()){
            bean.setContent(rs.getString("content"));
            bean.setFile(rs.getString("file"));
            bean.setId(rs.getString("id"));
            bean.setSubject(rs.getString("subject"));
            bean.setNum(rs.getInt("num"));
            bean.setRe_lev(rs.getInt("re_lev"));
            bean.setRe_ref(rs.getInt("re_ref"));
            bean.setRe_seq(rs.getInt("re_seq"));
            bean.setReadcount(rs.getInt("readcount"));
            bean.setDate(rs.getTimestamp("date"));
            bean.setComment_count(rs.getInt("comment_count"));
         }
         
      } catch (Exception e) {
         System.out.println("getBoard SQL 오류 : "+e);
         e.printStackTrace();
      }finally{
         resourceClose();
      }
      
      return bean;
      
   } // getBoard()

   public void updateBoard(boardBean bean){
      
      getConnection();
      
      try {
         
         sql = "update board set id=?,subject=?,content=? where num=?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, bean.getId());
         pstmt.setString(2, bean.getSubject());
         pstmt.setString(3, bean.getContent());
         pstmt.setInt(4, bean.getNum());
         pstmt.executeUpdate();   
         
      } catch (Exception e) {
         System.out.println("updateBoard SQL 오류 : "+e);
         e.printStackTrace();
      }finally{
         resourceClose();
      }
         
      
   } // updateBoard()
   
   public void deleteBoard(int num){
      
      getConnection();
      
      try {
         sql = "delete from board where num=?";
         pstmt=con.prepareStatement(sql);
         pstmt.setInt(1, num);
          pstmt.executeUpdate();
   
      } catch (Exception e) {
         System.out.println("deleteBoard SQL 오류 : "+e);
         e.printStackTrace();
      }finally{
         resourceClose();
      }
      
   } // deleteBoard()
   
   
   public int getBoardCount(String search) {
      
      getConnection();
      
      int count = 0;
      
      try {
         sql = "select count(*) from board where subject like ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, "%"+search+"%");
         rs = pstmt.executeQuery();
         if(rs.next()){
            count = rs.getInt(1);
         }
         
      } catch (Exception e) {
         System.out.println("getBoardCount SQL 오류 : "+e);
         e.printStackTrace();
      }finally{
         resourceClose();
      }
      return count;
   } // getBoardCount()
   
   
   public List<boardBean> getBoardSearchList(int startRow,int pageSize,String search){
      
      getConnection();
      List<boardBean> boardList=new ArrayList<boardBean>();
      try {
         sql = "select * from board where subject like ? order by re_ref desc, re_seq asc limit ?,?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, "%"+search+"%");
         pstmt.setInt(2, startRow-1);
         pstmt.setInt(3, pageSize);
         
         rs = pstmt.executeQuery();
         
         while (rs.next()) {
            boardBean bean = new boardBean();
            bean.setContent(rs.getString("content"));
            bean.setDate(rs.getTimestamp("date"));
            bean.setFile(rs.getString("file"));
            bean.setId(rs.getString("id"));
            bean.setNum(rs.getInt("num"));
            bean.setRe_lev(rs.getInt("re_lev"));
            bean.setRe_ref(rs.getInt("re_ref"));
            bean.setRe_seq(rs.getInt("re_seq"));
            bean.setReadcount(rs.getInt("readcount"));
            bean.setSubject(rs.getString("subject"));
            bean.setComment_count(rs.getInt("comment_count"));
            boardList.add(bean);
            
         }
         
      } catch (Exception e) {
         System.out.println("getBoardSearchList 오류 : "+e);
      }finally {
         resourceClose();
      }
      return boardList;
      
   } // getBoardSearchList()


}