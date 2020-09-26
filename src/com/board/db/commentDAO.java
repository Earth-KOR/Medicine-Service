package com.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class commentDAO {

   Connection con = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   String sql = "";
   
   //자원 해제
   public void resourcelClose(){
      
      try{
         if(pstmt != null) pstmt.close();
         if(rs != null) rs.close();
         if(con != null) con.close();
      } catch (Exception e) {
         System.out.println("자원해제 오류 : " + e);
      }
   }// resourceClose()
      
   public void getConnection() {
      
      try {
         Context init = new InitialContext();
         DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/medicine");         
         con = ds.getConnection();
         
      } catch (Exception e){
         e.printStackTrace();
         System.out.println("getConnectin 오류 : "+e);
      }
      
   }//getConnection()
   
   
   //insert
   public void insertComment(commentDTO cdto){
      
      getConnection();
      
//      int num = 0;
      
      try{
//         sql = "select max(comment_num) from comment";
//         pstmt = con.prepareStatement(sql);
//         rs = pstmt.executeQuery();
//      if(rs.next()){
//         num = rs.getInt(1)+1;
//      }
         String sql = "insert into comment value(null,?,?,?,now())";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, cdto.getBoard_num());
         pstmt.setString(2, cdto.getId());
         pstmt.setString(3, cdto.getCom_con());
         pstmt.executeUpdate();
         
         sql = "update board set comment_count=comment_count+? where num=?";
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setInt(1, 1);
         ps.setInt(2, cdto.getBoard_num());
         
         ps.executeUpdate();
         ps.close();
         
      }catch(Exception e){
         System.out.println("insertComment오류 "+e);
         e.printStackTrace();
      }finally{
         resourcelClose();
      }
      
   }
   //insert Comment End
   
   
   //Count Comment Start 
   public int commentCount(int board_num){
      getConnection();
      int count = 0;
      
      try{
         sql = "select count(*) from comment where board_num=?";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, board_num);
         rs = pstmt.executeQuery();
         
         if(rs.next()){
            count = rs.getInt(1);
         }
         
      }catch(Exception e){
         System.out.println("commentCount오류"+e);
         e.printStackTrace();
      }finally{
         resourcelClose();
      }
      return count;
   }
   //Count Comment End
   
   
   
   //Comment List Start 
   public ArrayList<commentDTO> getAllComment(int board_num){
      ArrayList<commentDTO> list = new ArrayList<>();
      getConnection();
   
      try{
         sql = "select * from comment where board_num = ? order by comment_num desc";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, board_num);
         rs = pstmt.executeQuery();
         
         while(rs.next()){
            commentDTO cdto = new commentDTO();
            cdto.setBoard_num(rs.getInt("board_num"));
            cdto.setComment_num(rs.getInt("comment_num"));
            cdto.setId(rs.getString("id"));
            cdto.setCom_con(rs.getString("com_con"));
            cdto.setDate(rs.getTimestamp("date"));
            
            list.add(cdto);
         }
      }catch(Exception e){
         System.out.println("Comment List오류 "+e);
         e.printStackTrace();
      }finally {
         resourcelClose();
      }
      return list;
   }
   //Comment List End 
   
   
   
   //Comment Update start
   public int updateComment(int comment_num, String com_con){
      getConnection();
      
      try{
         sql = "update comment set com_con = ? where comment_num = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, com_con);
         pstmt.setInt(2, comment_num);
         pstmt.executeUpdate();
         
      }catch(Exception e){
         System.out.println("update Comment 오류 "+e);
         e.printStackTrace();
      }finally{
         resourcelClose();
      }
      return 1;
   }
   //Comment Update End
   
   
   //Comment Delete Start
   public void deleteComment(String id, int comment_num, int board_num){
      getConnection();
   try{
      sql = "delete from comment where comment_num = ? AND id LIKE ? AND board_num = ?";
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, comment_num);
      pstmt.setString(2, id);
      pstmt.setInt(3, board_num);
      pstmt.executeUpdate();
      
      
      sql = "update board set comment_count=comment_count-? where num=?";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setInt(1, 1);
      ps.setInt(2, board_num);
      
      ps.executeUpdate();

      ps.close();
   }catch(Exception e){
      System.out.println("delete Comment 오류 "+e);
      e.printStackTrace();
   }finally{
      resourcelClose();
   }
   
   }
   //Comment Delete End
   
   
   
   //get Comment start
   public commentDTO getComment(int comment_num){
      getConnection();
      commentDTO cdto = new commentDTO();
      
      try{
         sql = "select * from comment where comment_num =?";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, comment_num);
         
         rs = pstmt.executeQuery();
         
         if(rs.next()){
            cdto.setBoard_num(rs.getInt("board_num"));
            cdto.setCom_con(rs.getString("com_con"));
            cdto.setComment_num(rs.getInt("comment_num"));
            cdto.setDate(rs.getTimestamp("date"));
            cdto.setId(rs.getString("id"));
         }
      }catch(Exception e){
         System.out.println("get Comment오류 "+ e);
         e.printStackTrace();
      }finally{
         resourcelClose();
      }
      return cdto;
      
   }
   
   
   //get Comment End
   
   
   
}