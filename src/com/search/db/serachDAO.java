package com.search.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class serachDAO {

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
	
	public ArrayList<String> search_medicine(String name) {
		
		ArrayList<String> name_list = new ArrayList<String>();
		
		
		try {
			getCon();
			
			String sql = "select ITEM_NAME from medicine_info"
					+ " where ITEM_NAME LIKE ?";
					
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name+"%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				name_list.add(rs.getString("ITEM_NAME"));
			}
			
			con.close();
			pstmt.close();
			rs.close();
			
			
		} catch (Exception e) {
			System.out.println("serach_medicine 메소드 오류 " + e); 
		}
		
		
		return name_list;
		
	}
	public int search_medicineName(String name) {
		
		int check = 0;
		
		try {
			getCon();
			
			String sql = "select ITEM_NAME from medicine_info"
					+ " where ITEM_NAME LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				check = 1;
			}
			else {
				check = 0;
			}
			
			con.close();
			pstmt.close();
			rs.close();
			
			
		} catch (Exception e) {
			System.out.println("serach_medicine 메소드 오류 " + e); 
		}
		
		
		return check;
		
	}
	
	public JSONArray search_effect(String name) {

		JSONArray json_arr = new JSONArray();

		try {
			getCon();

			System.out.println("test 실행 완료! 현재 name 값 : " + name);

			String sql = "select ITEM_NAME, ITEM_NAME_ENG, MATERIAL_NAME," + " MATERIAL_NAME_ENG"
					+ " from medicine_side_effect" + " where ITEM_NAME LIKE ? or ITEM_NAME_ENG LIKE ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.put("ITEM_NAME", rs.getString("ITEM_NAME"));
				json.put("MATERIAL_NAME", rs.getString("MATERIAL_NAME"));
				json_arr.add(json);
			}

			con.close();
			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println("serach_effect 메소드 오류 " + e);
		}

		return json_arr;

	} 
	
	public JSONObject search_medicine_list(String name) {
		
		JSONObject json = new JSONObject();
		
		
		try {
			getCon();
			
			String sql = "select ITEM_SEQ, ITEM_NAME, ENTP_NAME, MATERIAL_NAME, VALID_TERM,"
					+ " EFFECT, USAGE2, PRECAUTION"
					+ " from medicine_info"
					+ " where item_name LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			System.out.println(name);
			
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				json.put("ITEM_SEQ" , rs.getString("ITEM_SEQ"));
				json.put("ITEM_NAME", rs.getString("ITEM_NAME"));
				json.put("ENTP_NAME", rs.getString("ENTP_NAME"));
				String slice = rs.getString("MATERIAL_NAME");
				slice = slice.substring(0, slice.length()-1);
				json.put("MATERIAL_NAME", slice);
				json.put("VALID_TERM", rs.getString("VALID_TERM"));
				json.put("EFFECT", rs.getString("EFFECT"));
				json.put("USAGE2", rs.getString("USAGE2"));
				json.put("PRECAUTION", rs.getString("PRECAUTION"));
			}
			
			con.close();
			pstmt.close();
			rs.close();
			
			
		} catch (Exception e) {
			System.out.println("serach_medicine_list 메소드 오류 " + e); 
		}
		
		
		return json;
		
	} 
	
	
	
	
	
	
	
}
