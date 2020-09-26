package com.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MyPageDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void getCon() {
		
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/medicine");			
			con = ds.getConnection();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public MypageBean serach_Medicine_Info_Effect_list(String item_name, String userid, String cycle){

		MypageBean bean = new MypageBean();
		String material_spilt = "";
		String material_final ="";
		
		try {
			getCon();

			String sql = "select ITEM_SEQ, ITEM_NAME, ENTP_NAME, MATERIAL_NAME, VALID_TERM"
					+ " from medicine_info"
					+ " where item_name LIKE ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, item_name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bean.setITEM_SEQ(rs.getString("ITEM_SEQ"));
				bean.setITME_NAME(rs.getString("ITEM_NAME"));
				bean.setENTP_NAME(rs.getString("ENTP_NAME"));
				bean.setMATERIAL_NAME(rs.getString("MATERIAL_NAME"));
				material_spilt = rs.getString("MATERIAL_NAME");
				bean.setVALID_TERM(rs.getString("VALID_TERM"));
			
			}
			
			con.close();
			pstmt.close();
			rs.close();
			
			getCon();
			
			
			String[] mmaterialt = material_spilt.split(",");
			
			for(int i = 0; i< mmaterialt.length; i++){
				
				sql = "select MATERIAL_NAME,"
						+ " MATERIAL_NAME_ENG"
						+ " from medicine_side_effect"
						+ " where ITEM_NAME LIKE ? or ITEM_NAME_ENG LIKE ?";
				
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, mmaterialt[i]);
				pstmt.setString(2, mmaterialt[i]);
				
				rs = pstmt.executeQuery();
				if(rs.next()){
					while(rs.next()){
						
						material_final += rs.getString("MATERIAL_NAME");
						material_final += ", ";
					}
					material_final = material_final.substring(0, material_final.length()-2);
					bean.setSide_Effect(material_final);
					
				}
				else{
					bean.setSide_Effect("정보없음");
					
				}
				
			}
			
			bean.setUserID(userid);
			bean.setCycle(cycle);
			
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println("serach_medicine_list 메소드 오류 " + e);
		} 

		return bean;

	}
	
	public int Mypage_insert(MypageBean bean){
		
		int check = 0;
		
		try {
			getCon();
			
			String sql = "insert into medicine_mypage values"
					+ "(?,?,?,?,?,?,?,?,now(),'0')";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bean.getUserID());
			pstmt.setString(2, bean.getENTP_NAME());
			pstmt.setString(3, bean.getITME_NAME());
			pstmt.setString(4, bean.getITEM_SEQ());
			pstmt.setString(5, bean.getMATERIAL_NAME());
			pstmt.setString(6, bean.getVALID_TERM());
			pstmt.setString(7, bean.getSide_Effect());
			pstmt.setString(8, bean.getCycle());
			
			
			check = pstmt.executeUpdate();
			
			
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println("Mypage_insert 메소드 오류 " + e);
		} 
		
		return check;
		
	}
	public void History_insert(String id, String name){
		
		MypageBean bean = new MypageBean();
		
		try {
			getCon();
			
			String sql = "select * from medicine_mypage"
					+ " where UserID=? AND ITEM_NAME=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bean.setUserID(rs.getString("UserID"));
				bean.setITEM_SEQ(rs.getString("ITEM_SEQ"));
				bean.setITME_NAME(rs.getString("ITEM_NAME"));
				bean.setENTP_NAME(rs.getString("ENTP_NAME"));
				bean.setMATERIAL_NAME(rs.getString("MATERIAL_NAME"));
				bean.setVALID_TERM(rs.getString("VALID_TERM"));
				bean.setSide_Effect(rs.getString("Side_Effect"));
				bean.setCycle(rs.getString("cycle"));
				bean.setDate(rs.getTimestamp("time"));
			}
			
			
			
			con.close();
			pstmt.close();
			rs.close();
			
			getCon();
			
			sql = "insert into medicine_mypage_history"
					+ " values(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, bean.getUserID());
			pstmt.setString(2, bean.getITEM_SEQ());
			pstmt.setString(3, bean.getITME_NAME());
			pstmt.setString(4, bean.getENTP_NAME());
			pstmt.setString(5, bean.getMATERIAL_NAME());
			pstmt.setString(6, bean.getVALID_TERM());
			pstmt.setString(7, bean.getSide_Effect());
			pstmt.setString(8, bean.getCycle());
			pstmt.setTimestamp(9, bean.getDate());
			
			pstmt.executeUpdate();
			
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println("History_insert 메소드 오류 " + e);
		} 
		
		
	}
	public JSONArray Mypage_History_getList(String UserID){

		
		JSONArray list = new JSONArray();
		
		try {
			getCon();
			
			String sql = "select * from"
					+ " medicine_mypage_history"
					+ " where UserID LIKE ?"
					+ " order by time desc limit 0,10";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, UserID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				JSONObject bean = new JSONObject();
				String sub = "";
				bean.put("UserID", rs.getString("UserID"));
				bean.put("ITEM_SEQ", rs.getString("ITEM_SEQ"));
				bean.put("ITEM_NAME", rs.getString("ITEM_NAME"));
				bean.put("ENTP_NAME", rs.getString("ENTP_NAME"));
				sub = rs.getString("MATERIAL_NAME");
				bean.put("MATERIAL_NAME", sub.substring(0, sub.length()-1));
				bean.put("VALID_TERM", rs.getString("VALID_TERM"));
				bean.put("Side_Effect", rs.getString("Side_Effect"));
				bean.put("cycle", rs.getString("cycle"));
				bean.put("time", rs.getString("time"));
				
				System.out.println("time : " + rs.getString("time"));
				
				list.add(bean);
				
			}
			
			
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println("Mypage_History_getList 메소드 오류 " + e);
		} 
		
		return list;
		
	}

	public JSONArray Mypage_medicine_getList(String UserID){

		
		JSONArray list = new JSONArray();
		
		try {
			getCon();
			
			String sql = "select UserID, ITEM_SEQ, ITEM_NAME, ENTP_NAME,"
					+ " MATERIAL_NAME, VALID_TERM, Side_Effect, cycle from" //cycle 들어가야함
					+ " medicine_mypage"
					+ " where UserID LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, UserID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				JSONObject bean = new JSONObject();
				String sub = "";
				bean.put("UserID", rs.getString("UserID"));
				bean.put("ITEM_SEQ", rs.getString("ITEM_SEQ"));
				bean.put("ITEM_NAME", rs.getString("ITEM_NAME"));
				bean.put("ENTP_NAME", rs.getString("ENTP_NAME"));
				sub = rs.getString("MATERIAL_NAME");
				bean.put("MATERIAL_NAME", sub.substring(0, sub.length()-1));
				bean.put("VALID_TERM", rs.getString("VALID_TERM"));
				bean.put("Side_Effect", rs.getString("Side_Effect"));
				bean.put("cycle", rs.getString("cycle"));
				
				list.add(bean);
				
			}
			
			
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println("Mypage_medicine_list 메소드 오류 " + e);
		} 
		
		return list;
		
	}
	
	public void Mypage_time_update(String UserID, String item_name){
		
		try {
			
			getCon();
			
			String sql = "update medicine_mypage "
					+ "set time=now() "
					+ "where UserID=? AND item_name LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, UserID);
			pstmt.setString(2, item_name);
			
			pstmt.executeUpdate();
			
			
			con.close();
			pstmt.close();
			rs.close();
			
			
		} catch (Exception e) {
			System.out.println("Mypage_time_update 메소드 오류 !" + e);
		}
		
	}
	
	public String Mypage_getTime(String UserID, String item_name){
		
		String db_time = "";
		
		try {
			getCon();
			
			String sql = "select time from"
					+ " medicine_mypage"
					+ " where UserID LIKE ? AND ITEM_NAME LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, UserID);
			pstmt.setString(2, item_name);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				db_time = rs.getString("time");
				System.out.println("db_time: " + db_time);
			}
			
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println("Mypage_getTime 메소드 오류 " + e);
		} 
		
		return db_time;
		
	}
	public String Mypage_getSubTime(String sub_time){
		
		String result_time = "";
		
		try {
			getCon();
			
			String sql = "select TIMESTAMPDIFF(second, ?, now()) as time";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sub_time);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result_time = rs.getString("time");
				System.out.println("result_time : "+ result_time);
			}
			
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println("Mypage_getSubTime 메소드 오류 " + e);
		} 
		
		return result_time;
		
	}
	public int Mypage_get_item(String item_name){
		
		int check = 0;
		
		try {
			getCon();
			
			String sql = "select item_name from medicine_mypage"
					+ " where item_name LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, item_name);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				check = 1;
			}
			
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println("Mypage_get_item 메소드 오류 " + e);
		} 
		
		return check;
		
	}
	public HashMap<String, String> Mypage_GetGraph(String UserID, String item_name){
		HashMap<String, String> list = new HashMap<String, String>();
		try {
			getCon();
			
			String sql = "select Userid, ITEM_NAME, cycle, graph_y"
					+ " from medicine_mypage"
					+ " where item_name LIKE ?"
					+ " AND Userid LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, item_name);
			pstmt.setString(2, UserID);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				list.put("UserID", rs.getString("Userid"));
				list.put("ITEM_NAME", rs.getString("ITEM_NAME"));
				list.put("cycle", rs.getString("cycle"));
				list.put("graph_y", rs.getString("graph_y"));
			}
			
			sql = "select m.phone as phone "
					+ "from member as m "
					+ "join medicine_mypage as my "
					+ "on m.id = my.UserID "
					+ "where id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, UserID);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				list.put("phone", rs.getString(1));
			}
			
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println("Mypage_GetGraph 메소드 오류 " + e);
		} 
		
		return list;
		
	}
	public int Mypage_delete(String UserID, String item_name){
		int check = 0;
		
		try {
			getCon();
			
			String sql = "delete from medicine_mypage"
					+ " where item_name LIKE ?"
					+ " AND UserID LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, item_name);
			pstmt.setString(2, UserID);
			
			check = pstmt.executeUpdate();
			
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println("Mypage_delete 메소드 오류 " + e);
		} 
		
		return check;
		
	}
	public int Mypage_item_Cehck(String UserID, String item_name){
		int check = 0;
		
		try {
			getCon();
			
			String sql = "select count(*) from medicine_mypage"
					+ " where item_name LIKE ?"
					+ " AND UserID LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, item_name);
			pstmt.setString(2, UserID);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				check = rs.getInt(1);
			}
			
			
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println("Mypage_item_Cehck 메소드 오류 " + e);
		} 
		
		return check;
		
	}
	public int Mypage_side_Cehck(String item_name){
		
		
		int check = 0;
		
		try {
			getCon();
			
			String sql = "select MATERIAL_NAME,"
					+ " MATERIAL_NAME_ENG"
					+ " from medicine_side_effect"
					+ " where ITEM_NAME LIKE ? or ITEM_NAME_ENG LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, item_name);
			pstmt.setString(2, item_name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				check = 1;
			}
			
			con.close();
			pstmt.close();
			rs.close();
			
			
		} catch (Exception e) {
			System.out.println("serach_medicine_list 메소드 오류 " + e);
		} 
		
		return check;
		
	}
	
	public int get_graphY(String id, String item_name) {
		
		int y = 0;
		
		try {
			
			getCon();
			
			String sql = "select graph_y from medicine_mypage"
					+ " where UserID=? AND item_name=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, item_name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				y = rs.getInt(1);
				
			}
			
			con.close();
			pstmt.close();
			rs.close();
			
			
			
		} catch (Exception e) {
			System.out.println("get_graphY 메소드 에서 오류 발생 : " + e);
		}
		
		return y;
		
	}
	public int update_graphY(int y, String id, String item_name) {
		
		int check = 0;
		
		try {
			
			getCon();
			
			String sql = "update medicine_mypage"
					+ " set graph_y=?"
					+ " where UserID=? AND item_name=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, y);
			pstmt.setString(2, id);
			pstmt.setString(3, item_name);
			
			check = pstmt.executeUpdate();
			
			con.close();
			pstmt.close();
			rs.close();
			
			
			
		} catch (Exception e) {
			System.out.println("update_graphY 메소드 에서 오류 발생 : " + e);
		}
		
		return check;
	}

	
	
}
