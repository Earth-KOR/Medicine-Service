<%@page import="com.member.db.MemberDTO"%>
<%@page import="com.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YAKBBAL</title>
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/mypage.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">/* 포커스 주기 */
	$(document).ready(function(){$('#pw').focus();});
</script>

</head>

<%-- <jsp:useBean id="m" class="com.member.db.MemberDTO"/>
<jsp:setProperty  property="*"  name="m"/> --%>

<body>

<!-- 헤더 (로고,메뉴 등) -->
<div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

	<div id="wrap" style="-ms-overflow-style: none;">
	
	<div id="content">

	<h2> MY PAGE </h2>
	<br>

	
	<form action="${pageContext.request.contextPath}/MemberModifyAction.me" id="mypage" method="post">
	<table id="notice">
		<tr>
			<td class="t">이름</td>
			<td class="tt">${mdto.name }</td>
		</tr>
		<tr>
			<td class="t">아이디</td>
			<td class="tt">${mdto.id }</td>
		</tr>
		<tr> 
			<td class="t">생년월일</td>
			<td class="tt">${mdto.birth }</td>
		</tr>
		<tr>
			<td class="t">휴대폰번호</td>
			<td class="tt">${mdto.phone }</td>
		</tr>
		<tr> 
			<td class="t">E-mail</td>
			<td class="tt">${mdto.email }</td>
		</tr>
		<tr>
			<td class="t">복용 중인 약</td>
			<td class="tt">${mdto.my_Pill }</td>
		</tr>
		<tr>
			<td class="t">부작용 있는 약</td>
			<td class="tt">${mdto.my_Bujakyong }</td>
		</tr>
		<tr>
			<td class="t">통원 병원 정보</td>
			<td class="tt">${mdto.my_Hospital }</td>
		</tr>
	</table>
	
	<br><br>
	<div id="button">
		<button type="submit">정보수정</button>
			&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn" onclick="location.href='./changePw.me'">비밀번호 변경</button>
			&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn" onclick="location.href='./myDelete.me'">탈퇴하기</button>
	</div>
	</form>
	
	
</div>	
</div>
<!-- 본문내용 -->

<!-- 푸터 시작 -->
<div id="footer"><%@include file="../inc/footer.jsp" %></div>


</body>
</html>