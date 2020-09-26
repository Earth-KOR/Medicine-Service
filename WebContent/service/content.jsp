<%@page import="com.service.db.serviceBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YAKBBAL</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/footer.css" rel="stylesheet" type="text/css">
<link href="css/content_service.css" rel="stylesheet" type="text/css">
</head>
<%
System.out.println("content.jsp 시작");
serviceBean sbean = (serviceBean)request.getAttribute("sbean");

System.out.println("sbean : "+sbean);

String pageNum = (String)request.getAttribute("pageNum");
System.out.println("pageNum : "+pageNum);
%>
<script type="text/javascript">
	function ser(ser,pageNum) {
		window.location ="./serviceSearchList.se?search="+ser+"&pageNum="+pageNum;
		
	}
</script>
<body>

<!-- 헤더 (로고,메뉴 등) -->
<div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

<div id="wrap" style="-ms-overflow-style: none;">
<div id="content">

<!-- "수정,삭제 "버튼은 작성자에게만 노출  / 작성자 외에는 "목록 "버튼만 보이기 -->
	 <div id="button">
	 	<%
		String userid=(String)session.getAttribute("id");
		if(userid!=null){
			if(userid.equals(sbean.getService_id())){				
		%>
		<div class="update">
			<button class="btn" onclick="location.href='${pageContext.request.contextPath}/serviceUpdate.se?num=<%=sbean.getService_num()%>&pageNum=<%=pageNum%>'">수 정</button>
		</div>
		<div class="delete">
			<button class="btn" onclick="location.href='${pageContext.request.contextPath}/serviceDelete.se?num=<%=sbean.getService_num()%>&lev=<%=sbean.getService_re_lev()%>&seq=<%=sbean.getService_re_seq()%>&ref=<%= sbean.getService_re_ref()%>'">삭 제</button>
		</div>

		<%						
			}else if(userid.equals("admin")){
				 %>
				<div class="update">
				<button class="btn" onclick="location.href='${pageContext.request.contextPath}/serviceUpdate.se?num=<%=sbean.getService_num()%>&pageNum=<%=pageNum%>'">수 정</button>
			</div>
			<div class="delete">
				<button class="btn" onclick="location.href='${pageContext.request.contextPath}/serviceDelete.se?num=<%=sbean.getService_num()%>&lev=<%=sbean.getService_re_lev()%>&seq=<%=sbean.getService_re_seq()%>&ref=<%= sbean.getService_re_ref()%>'">삭 제</button>
			</div>
			<div class="re">
				<button class="btn2" onclick="location.href='./serviceReWrite.se?num=<%=sbean.getService_num()%>&re_ref=<%=sbean.getService_re_ref()%>&re_lev=<%=sbean.getService_re_lev()%>&re_seq=<%=sbean.getService_re_seq()%>'">답글달기</button>
			</div>
			<%
			}
		}
		%>
		<div class="list">
			<button class="btn1" onclick="ser('${ param.search }', '${param.pageNum }');">목 록</button>
		</div>		
	 </div>	<br>


<!-- 테이블 -->
<form method="get" encType="multipart/form-data">
	<table id="notice"> 
	  <tr>
	  	<th class="tn">No</th>
	  	<td class="tn1"><%=sbean.getService_num() %></td>
	  	<th class="tw">작성자</th>
	  	<td class="tw1"><%=sbean.getService_id() %></td>
	  	<th class="td">작성날짜</th>
	  	<td class="td1"><%=sbean.getService_date() %></td>
	    <th class="tr">조회수</th>
	  	<td class="tr1"><%=sbean.getService_readcount() %></td>
	  </tr>
	  <tr></tr>
	  <tr>
	  	<th class="tt">제목</th>
	  	<td class="tt1" colspan="7"><%=sbean.getService_subject() %></td>
	  </tr>
	  <tr>
	  <%if(sbean.getService_re_lev() == 0) {
	  		if(sbean.getService_file() == null){
	  		%>
<!-- 	  			<th class="tt">첨부파일</th>
	  		  	<td class="tt1" colspan="7">존재하지 않습니다.</td> -->
	  		<%
	  		}
	  		else{
	  		%>
	  			<th class="tt">첨부파일</th>
	  			<td class="tt1" colspan="7"><a href="./service/download.jsp?path=upload&name=<%=sbean.getService_file() %>"><%=sbean.getService_file() %></a></td>
	  			</tr>	
	  		<%	
	  		}
	  }
	  %>
	  <tr>
	  	<td class="tc" colspan="8"> 
	  	<%=sbean.getService_content() %>
	  	</td>
	  </tr>
	</table>
</form>
  

<!-- 본문내용 -->
	</div>	
	</div>
<!-- 푸터 시작 -->
<div id="footer"><%@include file="../inc/footer.jsp" %></div>
</body>
<%System.out.println("content.jsp 끝"); %>
</html>