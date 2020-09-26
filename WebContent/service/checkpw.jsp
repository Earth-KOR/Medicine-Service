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
<link href="css/checkpw.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">/* 포커스 주기 */
	$(document).ready(function(){ $('#pw').focus(); });
</script>
</head>
<body>
<!-- 헤더 (로고,메뉴 등) -->
<div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

<div id="wrap" style="-ms-overflow-style: none;">
<div id="content">

<!-- 테이블 -->

<h2>※ 비밀번호를 입력해주세요</h2><br>
<form action="./checkPwAction.se" method="post">
<input type="password" id="pw" name="pw">
<input type="hidden" value="${ param.num }" name="num">
<input type="hidden" value="${ param.pageNum }" name="pageNum">
<input type="hidden" value="${ param.search }" name="search">
<button type="submit" class="btn">입 력</button>
</form>


<!-- 본문내용 -->
	</div>	
	</div>
<!-- 푸터 시작 -->
<div id="footer"><%@include file="../inc/footer.jsp" %></div>

</body>
</html>