<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/header.css" rel="stylesheet" type="text/css">

</head>
<body>

<div id="header">

	<!-- 로고 -->
	<filter id="logo">
		<div id="logo">
		 <a href="main.me">
		  <img src="${pageContext.request.contextPath}/images/logo.png" class="logo" width="230" >
		 </a>
		</div>	
	</filter>

	<!-- 로그인 등 메뉴 (로그인하면 로그아웃,마이페이지 보이게 하기) -->
		<%
			String id1 = (String)session.getAttribute("id");
		
			if (id1 == null) {
		
		%>
		<div id="top_menu"> 
			<a href="./MemberLogin.me">로그인</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="./MemberJoin.me">회원가입</a>
		</div>
		
		<% } else { %>
		<div id="top_menu"> 
			<span><%=id1 %>님 약빨로 건강관리!</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  <a href="./MemberLogout.me">로그아웃</a>&nbsp;&nbsp;|&nbsp;
					  <a href="./MemberMypageAction.me">마이페이지</a>&nbsp;&nbsp;|&nbsp;
					  <a href="./MemberHistory.me">복용기록</a>
		</div>
		<% } %>
		
	<!-- 메뉴 -->
	<nav role="navigation">
	  <ul id="main_menu">
	    <li><a href="./pill.me">약복용</a></li>
	    <li><a href="./searchMain.me">약검색</a></li>
	    <li><a href="./pharmacy.me">약국찾기</a></li>
	    <li><a href="#">커뮤니티</a>
	      <ul id="sub_menu">
	        <li><a href="./news.me" aria-label="subemnu">보도자료</a></li>
	        <li><a href="./boardListAction.bo" aria-label="subemnu">자유게시판</a></li>
	        <li><a href="./serviceList.se" aria-label="subemnu">고객지원</a></li>
	      </ul>
	    </li>
	  </ul>
	</nav>	
	
	
	</div>
</body>
</html>