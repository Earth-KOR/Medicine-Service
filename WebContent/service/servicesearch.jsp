<%@page import="com.service.db.serviceBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
<link href="css/service.css" rel="stylesheet" type="text/css">
<%  
serviceBean sBean = new serviceBean();
List<serviceBean> boardList=(List<serviceBean>)request.getAttribute("boardList");
int count=((Integer)request.getAttribute("count")).intValue();
String pageNum=(String)request.getAttribute("pageNum");
int pageCount=((Integer)request.getAttribute("pageCount")).intValue();
int pageBlock=((Integer)request.getAttribute("pageBlock")).intValue();
int startPage=((Integer)request.getAttribute("startPage")).intValue();
int endPage=((Integer)request.getAttribute("endPage")).intValue();
String search = (String)request.getAttribute("search");
%>
<script type="text/javascript">
	function ser() {
		
		var ser = document.getElementById("searchin").value;
		window.location ="./serviceSearchList.se?search="+ser;
		
	}


</script>
</head>
<body>
<!-- 헤더 (로고,메뉴 등) -->
<div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

	<div id="wrap" style="-ms-overflow-style: none;">
	<div id="content">
	
	<!-- 상단 -->
	<div class="top">
		<div class="h2"><h2>고객센터&nbsp;&nbsp;Q&A</h2></div>
		
		<!-- 글쓰기 버튼 / 로그인 시에만 노출 -->
		<%
		String idd=(String)session.getAttribute("id");
		if(idd!=null){
		%>
		<div class="write">
			<button type="button" onclick="location.href='./serviceWrite.se'">글쓰기</button>
		</div>
		<% } %>
		<!-- 검색  -->
		<div class="search">	
			<input type="text" id="searchin" placeholder="문의 내용을 검색하세요" value="<%=search%>">&nbsp;
			<button type="button" class="btn" value="search" onclick="ser();">검 색</button>
		</div>
	</div><br>
		
	<!-- 게시판 -->
	<table id="notice"> 
	  <tr>
	  	<th class="tn">No</th>
	  	<th class="tt">제 목</th>
	  	<th class="tw">작성자</th>
	  	<th class="td">작성날짜</th>
	  	<th class="tr">조회수</th>
	  </tr>
	<%
	if(count != 0){
	
	for (int i = 0 ; i<boardList.size() ; i++) {
		sBean = boardList.get(i);
		
		%>
	 <tr onclick="location.href='./serviceContentAction.se?num=<%=sBean.getService_num() %>&pageNum=<%=pageNum%>'">
	   	<td class="tn"><%=sBean.getService_num() %></td>	
	   	  	
	  	<td class="tt"><%=sBean.getService_subject() %></td>
	  	<td class="tw"><%=sBean.getService_id() %></td>
	  	<td class="td"><%=sBean.getService_date() %></td>
	  	<td class="tr"><%=sBean.getService_readcount() %></td>
	  </tr>
	 <%}
	}else {%>
	
	
	<tr>
	  	<td colspan="5">작성된 내용이 없습니다</td>
	</tr>
	
	<% 
	}%>
	</table>


	<!-- 페이지 번호 -->
	<div id="page">
		<%
		if(count!=0){
			if(startPage > pageBlock){
		%>
			<a href="./serviceSearchList.se?pageNum=<%=startPage-pageBlock%>&search=<%=search%>"> < </a>
		<%
			}
			for(int i=startPage;i<=endPage;i++){
		%>
			<a href="./serviceSearchList.se?pageNum=<%=i%>&search=<%=search%>"><%=i %></a>
		<%
			}
			if(endPage < pageCount){
		%>
			<a href="./serviceSearchList.se?pageNum=<%=startPage+pageBlock%>&search=<%=search%>"> > </a>
		<%
			}
		}
		%>
	</div>
		
	
	</div>	
	</div>
<!-- 본문내용 -->

<!-- 푸터 시작 -->
<div id="footer"><%@include file="../inc/footer.jsp" %></div>
	
</body>
</html>