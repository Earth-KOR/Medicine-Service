package com.service.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class serviceDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	public void getConnection() {
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/medicine");			
			con = ds.getConnection();
			
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("��� ���� ����  : "+e);
		}
		
	}//getConnection()
	
	public void insertService(serviceBean sbean) {

		getConnection();
		
		int num = 0;
		
	System.out.println(	sbean.getService_id());
	System.out.println(	sbean.getService_pw() );
	System.out.println(	sbean.getService_subject() );
	System.out.println(sbean.getService_content() );
	System.out.println(	sbean.getService_file() );
		
	try {
		sql="select max(service_num) from service_board";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()){
			num=rs.getInt(1)+1; //1번열   가장큰번호+1
		}
		System.out.println("num = "+num);
				
	/*		sql = "insert into service_board(service_num,service_id,service_pw,service_subject,service_content, "
					+ "service_file,service_re_ref,service_re_lev,service_re_seq,service_readcount,service_date) "
					+ "values (?,?,?,?,?,?,?,?,?,?,now())";*/

			sql = "insert into service_board values (null,?,?,?,?,?,?,?,?,?,now()) ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, sbean.getService_id());
			pstmt.setString(2, sbean.getService_pw());
			pstmt.setString(3, sbean.getService_subject());
			pstmt.setString(4, sbean.getService_content());
			pstmt.setString(5, sbean.getService_file());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertService SQL 오류 : "+e);
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		
	}// insertBoard	
	
	
	public List<serviceBean> getserviceList(int startRow,int pageSize){
		
		List<serviceBean> serviceList=new ArrayList<serviceBean>();
		
		try {
			getConnection();
			sql = "select * from service_board order by service_re_ref desc, service_re_seq asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
						
			while(rs.next()){
				serviceBean sbbean = new serviceBean();
				sbbean.setService_content(rs.getString("service_content"));
				sbbean.setService_date(rs.getTimestamp("service_date"));
				sbbean.setService_file(rs.getString("service_file"));
				sbbean.setService_id(rs.getString("service_id"));
				sbbean.setService_num(rs.getInt("service_num"));
				sbbean.setService_pw(rs.getString("service_pw"));
				sbbean.setService_re_lev(rs.getInt("service_re_lev"));
				sbbean.setService_re_ref(rs.getInt("service_re_ref"));
				sbbean.setService_re_seq(rs.getInt("service_re_seq"));
				sbbean.setService_readcount(rs.getInt("service_readcount"));
				sbbean.setService_subject(rs.getString("service_subject"));
				serviceList.add(sbbean);
			}
		} catch (Exception e) {
			System.out.println("getserviceList SQL 오류 : "+e);
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		
		return serviceList;
		
	}

	public int getserviceCount() {
		
		getConnection();
		
		
		int count = 0;
		
		try {
			sql = "select count(*) from service_board";
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
		
		} catch (Exception e) {
			System.out.println("getserviceCount SQL 오류 : "+e);
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return count;
	}
	
	
	public int getserviceCount(String search) {
		
		getConnection();
		int count = 0;
		
		try {
			if(search == ""){
				sql = "select count(*) from service_board";
				pstmt=con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
			}else{
				sql = "select count(*) from service_board where service_subject like ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				rs = pstmt.executeQuery();
				
			}
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("getserviceCount SQL 오류 : "+e);
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return count;
	}
	
	public List<serviceBean> getserviceSearchList(int startRow,int pageSize,String search){
		
		getConnection();
		List<serviceBean> serviceList=new ArrayList<serviceBean>();
		try {
			if(search == ""){
				sql = "select * from service_board order by service_re_ref desc, service_re_seq asc limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow-1);
				pstmt.setInt(2, pageSize);
				
				rs = pstmt.executeQuery();
			}
			else {
				sql = "select * from service_board where service_subject like ? order by service_re_ref desc, service_re_seq asc limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				
				rs = pstmt.executeQuery();
				
			}
			while (rs.next()) {
				serviceBean sbbean = new serviceBean();
				sbbean.setService_content(rs.getString("service_content"));
				sbbean.setService_date(rs.getTimestamp("service_date"));
				sbbean.setService_file(rs.getString("service_file"));
				sbbean.setService_id(rs.getString("service_id"));
				sbbean.setService_num(rs.getInt("service_num"));
				sbbean.setService_pw(rs.getString("service_pw"));
				sbbean.setService_re_lev(rs.getInt("service_re_lev"));
				sbbean.setService_re_ref(rs.getInt("service_re_ref"));
				sbbean.setService_re_seq(rs.getInt("service_re_seq"));
				sbbean.setService_readcount(rs.getInt("service_readcount"));
				sbbean.setService_subject(rs.getString("service_subject"));
				serviceList.add(sbbean);
				
			}
			
		} catch (Exception e) {
			System.out.println("getserviceSearchList 오류 : "+e);
			e.printStackTrace();
		}finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return serviceList;
		
		
	}
	
		
	public void updateReadcount(int num){
		
		getConnection();
		
		try {
			sql = "update service_board set service_readcount=service_readcount+1 where service_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateReadcount SQL 오류 : "+e);
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		
	}
	
	public serviceBean getService(int num){
		
		getConnection();
		serviceBean sbean = new serviceBean();
		try {
			
			sql = "select * from service_board where service_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()){
				sbean.setService_content(rs.getString("service_content"));
				sbean.setService_file(rs.getString("service_file"));
				sbean.setService_pw(rs.getString("service_pw"));
				sbean.setService_id(rs.getString("service_id"));
				sbean.setService_subject(rs.getString("service_subject"));
				sbean.setService_num(rs.getInt("service_num"));
				sbean.setService_re_lev(rs.getInt("service_re_lev"));
				sbean.setService_re_ref(rs.getInt("service_re_ref"));
				sbean.setService_re_seq(rs.getInt("service_re_seq"));
				sbean.setService_readcount(rs.getInt("service_readcount"));
				sbean.setService_date(rs.getTimestamp("service_date"));
			}
			
		} catch (Exception e) {
			System.out.println("getService SQL 오류 : "+e);
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		
		return sbean;
		
	}
	
	public int deleteService(int ref ,int lev, int num, int seq){
		
		getConnection();
		int check = 0;
		try {
			
			System.out.println("DB :" + lev);
			
			if (lev == 0){
				sql = "delete from service_board where service_re_ref=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, ref);
			    pstmt.executeUpdate();
			}
			else{
				sql = "delete from service_board where service_num=? AND service_re_lev=?"
						+ " AND service_re_seq=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setInt(2, lev);
				pstmt.setInt(3, seq);
			    pstmt.executeUpdate();
			}
			
			check = 1;
			
	
		} catch (Exception e) {
			System.out.println("serviceDelete SQL 오류 : "+e);
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		
		
		return check;
		
	}
	
	public int updateService(serviceBean sbean){
		
		getConnection();
		int num;
		int check = -1;
		
		try {
			sql = "select service_pw from service_board where service_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sbean.getService_num());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(sbean.getService_pw().equals(rs.getString("service_pw"))){
					check = 1;
					sql = "update service_board set service_id=?,service_subject=?,service_content=? where service_num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, sbean.getService_id());
					pstmt.setString(2, sbean.getService_subject());
					pstmt.setString(3, sbean.getService_content());
					pstmt.setInt(4, sbean.getService_num());
					pstmt.executeUpdate();
				}else{
					check = 0;
				}
				
			}
		} catch (Exception e) {
			System.out.println("updateService SQL 오류 : "+e);
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
			
		return check;
		
		
	}
	
	public void reInsertServics(serviceBean sbean){

		getConnection();

		int num=0;
		int result=0;
		
		int re_ref = sbean.getService_re_ref(); 
		int re_lev = sbean.getService_re_lev(); 
		int re_seq = sbean.getService_re_seq();


		System.out.println(sbean.getService_id());
		System.out.println(sbean.getService_pw() );
		System.out.println(sbean.getService_subject() );
		System.out.println(sbean.getService_content() );

		try {
						
			sql="select max(service_num) from service_board";
			pstmt=con.prepareStatement(sql);
			//5 rs 첫행 데이터있으면  num = 최대큰번호 +1
			rs=pstmt.executeQuery();
			if(rs.next()){
				num=rs.getInt(1)+1;
			}else{
				num = 1;
			}
			
			sql="update service_board set service_re_seq=service_re_seq+1 where service_re_ref=? and service_re_seq>?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			result = pstmt.executeUpdate();
			
			re_lev++;
			re_seq++;
						
			sql="insert into service_board values(null,?,?,?,?,?,?,?,?,?,now()) ";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, sbean.getService_id());
			pstmt.setString(2, sbean.getService_pw());
			pstmt.setString(3, sbean.getService_subject());
			pstmt.setString(4, sbean.getService_content());
			pstmt.setString(5, sbean.getService_file());
			pstmt.setInt(6, re_ref); 
			pstmt.setInt(7, re_lev);  
			pstmt.setInt(8, re_seq);  
			pstmt.setInt(9, 0); 

			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("reInsertServics 오류 : "+e);
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		
	}
	
	public int serviceSecret(int num, String id, String pwd){
		
		getConnection();
		int check = 0;
		
		try {
			sql = "select service_pw from service_board where service_num=? and service_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if(pwd.equals(rs.getString("service_pw"))){
					check = 1;
					
					
				}
				
			}
			
			
		} catch (Exception e) {
			System.out.println("serviceSecret 오류 : "+e);
			e.printStackTrace();
		}
		return check;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
