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
<link href="${pageContext.request.contextPath}/css/myModify.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">/* 포커스 주기 */
	$(document).ready(function(){$('#pw').focus();});
</script>

<!-- 유효성 체크 -->
<script type="text/javascript">
   function check(){
	   
	   
      //비밀번호
      var pwCheck = RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,12}$/);

      //휴대폰번호
      var phoneCheck = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
    
      //이메일
      var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      
      //비밀번호
      if(!pwCheck.test($("#pw").val())){
         alert("비밀번호는 영문,숫자,특수문자 혼합 8자~12자 이내의 형식에 맞게 입력해주세요");
         $("#pw").val("");
         $("#pw").focus();
         return false;
      }
      
      //폰번호
      if(!phoneCheck.test($("#phone").val())){
         alert("폰번호는 (01012341234)의 형식에 맞게 입력해주세요");
         $("#phone").val("");
         $("#phone").focus();
         return false;
      }
      
      //이메일
     	if(!emailRule.test($("#email").val())) {            
			alert("이메일을 형식에 맞게 입력해 주세요. \n lsk941017@naver.com");
            return false;
  	  }
      
      
     	  document.update.action = "./MemberUpdateAction.me";
     	  document.update.method = "post";
     	  document.update.submit();

       
   }
 
</script>


</head>
<body>


<!-- 헤더 (로고,메뉴 등) --> 

<div id="header"><%@include file="../inc/header.jsp" %></div>
 

<!-- 본문내용 -->

	<div id="wrap" style="-ms-overflow-style: none;">
	
	<div id="content">

	<h3 align="left">&nbsp;&nbsp;* 수정할 정보를 입력하세요. </h3>
	<br>
	
   	  
	<form action="${pageContext.request.contextPath}/MemberUpdateAction.me" id="update" method="post" name="update">
	<table id="notice">
		<tr>
			<td class="t">이름</td>
			<td class="tt" name="name">${mdto.name}</td>
		</tr>
		<tr>
			<td class="t">아이디</td>
			<td class="tt">${mdto.id}</td> 
			<input type="hidden" name="id" value="${mdto.id }">
		</tr>
		<tr>
			<td class="t">비밀번호</td>
			<td class="tt"><input type="password" name="pw" id="pw"></td>
		</tr>
		<tr>
			<td class="t">생년월일</td>
			<td class="tt"><input type="text" name="birth" value="${mdto.birth }" readonly="readonly"></td>
		</tr>
		<tr>
			<td class="t">휴대폰번호</td>
			<td class="tt"><input type="text" name="phone" id="phone" value="${mdto.phone }"></td>
		</tr>
		<tr>
			<td class="t">E-mail</td>
			<td class="tt"><input type="text" name="email" id="email" value="${mdto.email }"></td>
		</tr>
		<tr>
			<td class="t">복용 중인 약</td>
			<td class="tt"><input type="text" name="my_Pill" value="${mdto.my_Pill }"></td>
		</tr>
		<tr>
			<td class="t">부작용 있는 약</td>
			<td class="tt"><input type="text" name="my_Bujakyong" value="${mdto.my_Bujakyong }"></td>
		</tr>
		<tr>
			<td class="t">통원 병원 정보</td>
			<td class="tt"><input type="text" name="my_Hospital" value="${mdto.my_Hospital }"></td>
		</tr>
	</table>
	
	<br><br>
	<div id="button">
		<button type="button" onclick="check()">완 료</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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