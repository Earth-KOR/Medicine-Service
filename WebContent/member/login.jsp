<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YAKBBAL</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<link href="${pageContext.request.contextPath}/css/default.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/header.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/footer.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/login.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<script type="text/javascript">/* 포커스 주기 */
		$(document).ready(function(){$('#id').focus();});
	</script>

	<script type="text/javascript">
		componentDidMount() {
			Kakao.init('65112fe6b4bf956e7a2f96ebfc412c50');
		}
	</script>
	<!-- 헤더 (로고,메뉴 등) -->
	<div id="header"><%@include file="../inc/header.jsp"%></div>
	<!-- 본문내용 -->

	<div id="wrap" style="-ms-overflow-style: none;">

		<div id="content">

		<h4>로그인 후 <span class="yak">약복용</span> 메뉴를 이용해보세요 ! </h4>
		<br><br>
			<form action="./MemberLoginAction.me" id="login" method="post">

				<div class="login_form">
					<input type="text" id="id" name="id" placeholder="ID (영문 대소문자 구분)"><br>
					<input type="password" id="pw" name="pw"
						placeholder="PASSWORD (영문 대소문자 구분)">
				</div><br>

				<div id="buttons">
					<input type="submit" value="로그인" class="submit"> 
					<input type="reset" value="다시입력" class="reset"> <br> 
					
					<a href="http://kauth.kakao.com/oauth/authorize?clint_id={}"></a>
					
    					
				</div>
			</form>
			<br><br><br>
			<div class="help">
				<a href="${pageContext.request.contextPath}/findId.me">아이디 찾기</a>
				&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/findPw.me">비밀번호 찾기</a>
				&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/MemberJoin.me">회원가입</a>
			</div>

		</div>
	</div>
	<!-- 본문내용 -->

	<!-- 푸터 시작 -->
<%-- 	<div id="footer"><%@include file="../inc/footer.jsp"%></div> --%>
	<div id="footer"><jsp:include page="../inc/footer.jsp"/></div>
</body>
</html>