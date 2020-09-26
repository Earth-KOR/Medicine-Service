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
<link href="${pageContext.request.contextPath}/css/myDelete.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">/* 포커스 주기 */
	$(document).ready(function(){$('#pw').focus();});
</script>
 
</head>
<body>

<!-- 헤더 (로고,메뉴 등) -->
<div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

	<div id="wrap" style="-ms-overflow-style: none;">
	
	<div id="content">

	<h4><span style="color:#00686a">※ 비밀번호</span>를 입력하세요</h4>
	<br>
		<form action="${pageContext.request.contextPath}/MemberDeleteAction.me" id="delete" method="post">
			<div class="pw">
				<input type="hidden" name="id" value="${dto.id }">
			    <input type="password" placeholder="" name="pw" id="pw">
			</div>
			<div class="button">
			     <button type="submit">확 인</button>
			     <button type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/MemberMypageAction.me'">취 소</button>
			</div>
		</form>
</div>	
</div>
<!-- 본문내용 -->

<!-- 푸터 시작 -->
<div id="footer"><%@include file="../inc/footer.jsp" %></div>


</body>
</html>