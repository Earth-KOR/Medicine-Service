package com.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.action.SMTPAuthenticator;



public class MemberDAO {

	// 전역변수 선언
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
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

	// 연결할 jspbeginner데이터베이스 정보를 담고 있는
	// DataSource(커넥션풀)을 얻고
	// DataSource(커넥션풀)내부에 DB와 미리 연결을 맺은 Connection접속객체를 얻기 위해
	// 메소드 만들기
	private Connection getConnection() throws Exception {

		Connection con = null;
		Context init = new InitialContext();
		// 커넥션풀 얻기
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/medicine");
		// 커넥션풀로 부터 커넥션 객체 빌려와서 얻기
		con = ds.getConnection();

		return con; // 커넥션을 반환

	}// getConnection메소드 끝
	
	
	
	//insertMember start
	public boolean insertMember(MemberDTO mdto) {

		int result = 0; // 회원가입 성공 여부
		
		try {
			// 1.DB접속(연결) : 커넥션풀 DataSource로 부터 커넥션 Connection객체 빌려오기
			con = getConnection();

			// 2.SQL문 만들기 (INSERT)
			sql = "insert into member(name,id,pw,pwCheck,email,birth,phone,my_Pill,my_Bujakyong, my_Hospital) "
		             +"values(?,?,?,?,?,?,?,?,?,?)";

			// 3. 위 Insert전체 문자열 중에서 ?기호에 대응되는 설정값을 제외한 전체 구문을
			// PreparedStatement객체에 로딩후
			// PreparedStatement객체 자체를 반환
			pstmt = con.prepareStatement(sql);
			// 4. ?기호에 대응되는 값들을 설정
			pstmt.setString(1, mdto.getName()); 
	        pstmt.setString(2, mdto.getId()); 
	        pstmt.setString(3, mdto.getPw()); 
	        pstmt.setString(4, mdto.getPwCheck()); 
	        pstmt.setString(5, mdto.getEmail());
	        pstmt.setString(6, mdto.getBirth());
	        pstmt.setString(7, mdto.getPhone());
	        pstmt.setString(8, mdto.getMy_Pill());
	        pstmt.setString(9, mdto.getMy_Bujakyong());
	        pstmt.setString(10, mdto.getMy_Hospital());

			// 5. insert 문장을 DB에 전송 하여 실행
			result = pstmt.executeUpdate(); // 회원가입 성공 시 1, 실패 시 0
			
			if (result != 0){
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("insertMember메소드 내부에서 SQL실행예외 : " + e);
		} finally { // 무조건 한번 실행 해야 할 구문이 있을때.... 작성하는 영역
			// 6.자원해제
			resourceClose();
		}
		return false;
	}// insertMember메소드 끝
		
	
	//userCheck for login start
	public int userCheck(String id, String pw){
		
		int check = 1; // 1 -> 아이디 O , 비밀번호 O
					   // 0 -> 아이디 O , 비밀번호 X
					   // -1 -> 아이디 X
		try {
			
			con = getConnection();
			
			sql = "select pw from member where id=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				
				//입력한 pw와 DB의 pw가 같으면 
				if(pw.equals(rs.getString("pw"))){
					
					check = 1; // 아이디 O , 비밀번호 O
				
				// pw가 틀리면
				}else{
					
					check = 0; // 아이디 O , 비밀번호 X
				}
				
			// 아이디가 없으면
			}else {
				
				check = -1; //아이디 X
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceClose();
		}
		
		return check;
	}//userCheck start end
	
	
	
	
	//detletMember start
	
	public int deleteMember(MemberDTO dto){
        int result = 0;
 
        try {
            con = getConnection();
            String sql = "select pw from member where id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, dto.getId());
            
            rs = pstmt.executeQuery();

            if(rs.next()){
            	
            	if(dto.getPw().equals(rs.getString("pw"))){

            		sql = "delete from member where id=?";
            		pstmt = con.prepareStatement(sql);
            		
            		pstmt.setString(1, dto.getId());
            		pstmt.executeUpdate();
            		
            		result = 1;  //성공 
            	}else{
            		result = 0; //실패 
            }
           } 	
        } catch (Exception e) {
        	System.out.println("deleteMember()오류: "+e);
        	e.printStackTrace();
        } finally {
            resourceClose();
        }
		return result;
    }
	//detletMember end
	
	//updateMember start
	public int updateMember(MemberDTO mdto){
		
		int check = 0;
		
		try{
			con = getConnection();
			
			sql = "select pw from member where id=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mdto.getId());
			System.out.println(mdto.getId());
			rs = pstmt.executeQuery();
			System.out.println(mdto.getPw());
			//System.out.println(rs.getString("pw"));
			
			if (rs.next()){
				if(mdto.getPw().equals(rs.getString("pw"))){
					
					pstmt.close();
					
					pstmt = null;
					
					sql = "update member set phone=?, email=?, my_Pill=?, my_Bujakyong=?, my_Hospital=? where id=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, mdto.getPhone());
					pstmt.setString(2, mdto.getEmail());
					pstmt.setString(3, mdto.getMy_Pill());
					pstmt.setString(4, mdto.getMy_Bujakyong());
					pstmt.setString(5, mdto.getMy_Hospital());
					pstmt.setString(6, mdto.getId());
					
					pstmt.executeUpdate();
					
					check = 1;
					
				}else {
					
					check = 0;
				}
				
			}
			
		
		} catch (Exception e) {
			
			System.out.println("updateMember() 오류 : "+e);
		
		} finally {
			
			resourceClose();
			
		}
		
		return check;
		
	}
	//updateMember end
	
	public int updatePw(MemberDTO mdto){
		int result = 0; //0 실패, 1 성공
		try{
			con = getConnection();
			sql = "update member set pw=? where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getPw());
			pstmt.setString(2, mdto.getId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updatePw() 오류 : "+e);
		} finally {
			resourceClose();
		}
		return result;
	}
	
	//id duplicate check start
	  public int dupCheck(String id){
	      int check = 1;
	      
	      try{
	         con = getConnection();
	         sql = "select * from member where id = ?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, id);
	         rs = pstmt.executeQuery();
	         System.out.println(id);
	         //System.out.println(rs.next());
	         if(rs.next()) {
	            
	            check = 1;
	            
	         } else {
	         
	            check = 0 ;
	            
	         }
	   
	         
	      } catch (Exception e) {
	         System.out.println("dupCheck오류() " + e);
	      } finally {
	         resourceClose();
	      }
	      return check;
	   }
	//id duplicate check end
	
	
	//getMember start
	public MemberDTO getMember(String id){
		MemberDTO mdto = new MemberDTO();
		
		try {
			con = getConnection();
			sql = "select * from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				mdto.setId(id);
				mdto.setName(rs.getString("name"));
				mdto.setPw(rs.getString("pw"));
				mdto.setBirth(rs.getString("birth"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setEmail(rs.getString("email"));
				mdto.setMy_Pill(rs.getString("my_Pill"));
				mdto.setMy_Bujakyong(rs.getString("my_Bujakyong"));
				mdto.setMy_Hospital(rs.getString("my_Hospital"));
			}
			
		} catch (Exception e) {
			System.out.println("getMember()에서 오류: " + e);
		}finally {
			resourceClose();
		}
		return mdto;
	}
	//getMember end
	
	
	//findId start
		public String findid(String name, String email) {
			String id = null;
			
//			System.out.println(name);
//			System.out.println(email);
			
			try{
				con = getConnection();
				sql = "select id from member where name =? and email=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					id = rs.getString(1);
					
				}
			} catch (Exception e) {
				System.out.println("findId()오류: " + e);
			} finally {
				resourceClose();
			}
			return id;
		}
		//findId end
		
		//idCheck start
		public boolean idCheck(String id, String email) {
			boolean check = false;
			
//			System.out.println(name);
//			System.out.println(email);
			
			try{
				con = getConnection();
				sql = "select id from member where id =? and email =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, email);
	            rs = pstmt.executeQuery();
	            if(rs.next() ) {
	            	check = true ;
	            }
	            

			} catch (Exception e) {
				System.out.println("findId()오류: " + e);
			} finally {
				resourceClose();
			}
			return check;
		}
		//idCheck end
		
		
		
		//findPw start
		public String rePw() { // 임시 비밀번호 
			String randomPw = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			StringBuilder rePw = new StringBuilder();
			for (int i = 0; i < 8; i++) {
				int num = (int) (Math.random() * randomPw.length());
				rePw.append(randomPw.charAt(num));
			}
			return rePw.toString();
		}
		//findPw end
		
		
		//sendEmail for findPw start
		public int sendEmail(String to, String subject, String content){
			String from ="itwillbs_yakbbal@naver.com";
			 
			Properties p = new Properties(); // 정보를 담을 객체

			p.put("mail.smtp.host", "smtp.naver.com"); // 네이버 SMTP
			p.put("mail.smtp.port", "465");
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.debug", "true");
			p.put("mail.smtp.socketFactory.port", "465");
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback", "false");
			// SMTP 서버에 접속하기 위한 정보들

			try {
				Authenticator auth = new SMTPAuthenticator();
				Session ses = Session.getInstance(p, auth);

				ses.setDebug(true);

				MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
				msg.setSubject(subject); // 제목

				Address fromAddr = new InternetAddress(from);
				msg.setFrom(fromAddr); // 보내는 사람

				Address toAddr = new InternetAddress(to);
				msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람

				msg.setContent(content, "text/html;charset=UTF-8"); // 내용과 인코딩

				Transport.send(msg); // 전송

				return 1;
			} catch (Exception e) {
				System.out.println("sendEmail()에서 오류: " + e);
				return 0;
			}
		
		}
		
		//sendEmail for findPw end
	
		
		//pw check start
		  public int pwCheck(String pw){
		      int check = 1;
		      
		      try{
		         con = getConnection();
		         sql = "select * from member where pw = ?";
		         pstmt = con.prepareStatement(sql);
		         pstmt.setString(1, pw);
		         rs = pstmt.executeQuery();
		         System.out.println(pw);

		         if(rs.next()) {
		            System.out.println("111111");
		            check = 1;
		            
		         } else {
		        	   System.out.println("22222");
		            check = 0 ;
		            
		         }
		   
		      } catch (Exception e) {
		         System.out.println("pwCheck오류() " + e);
		      } finally {
		         resourceClose();
		      }
		      return check;
		   }
		//pw check end
		
			//change new Pw start
			public int changePw(MemberDTO mdto){
				int result = 0; //0 실패, 1 성공
				try{
					con = getConnection();
					sql = "update member set pw=? where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, mdto.getPw());
					pstmt.setString(2, mdto.getId());
					result = pstmt.executeUpdate();
				} catch (Exception e) {
					System.out.println("changePw() 오류 : "+e);
				} finally {
					resourceClose();
				}
				return result;
			}
			//change new Pw start End 
				
				
	
	
}//MemberDAO end
