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
<link href="css/changePw.css" rel="stylesheet" type="text/css">

</head>
<body>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.js"></script> 
 <script type="text/javascript">
// 새 비밀번호 유효성 체크 
function check(){
	//비밀번호
    var pwCheck = RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,12}$/);
	
    var pw = $("#pw").val();
    var update_pw = $("#newPw").val();
    
    if(pw == ""){
		alert("현재 비밀번호를 입력바랍니다.")

	}else{
	
	
  //비밀번호
    if(!pwCheck.test($("#newPw").val())){
       alert("비밀번호는 영문,숫자,특수문자 혼합 8자~12자 이내의 형식에 맞게 입력해주세요");
       $("#newPw").val("");
       $("#newPw").focus();

      }else {
    	  
    	  if($('#newPw').val()!=$('#newPwCheck').val()){
    	      alert("비밀번호가 일치하지 않습니다.");
    	      $("#newPwCheck").val("");
    	      $("#newPwCheck").focus();

    	      }else{
    	    
      	    	  document.changePw.action = "./MemberPwChangeAction.me";
			     	document.changePw.method = "post";
			     	document.changePw.submit();	  
    	      }
      }
	}
  //비밀번호 일치 체크
   
}
</script>
<script type="text/javascript">

//현재 비밀번호 일치 체크
	var pw ;
	
 function pwCheck(){
	 
	var pw = $("#pw").val();
	

	if(pw == ""){
		alert("현재 비밀번호를 입력바랍니다.")
	}else{
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/MemberPwCheckAction.me', 
			data:{pw:pw},
			success : function(result){
				if(result == 1){
					$("#check").html("비밀번호가 일치합니다.");
				}else{
					$("#check").html("다시 입력 바랍니다.");
					$("#pw").val("");
					$("#pw").focus();
				}
			}
		});
	} 
}
	
</script>

<!-- 헤더 (로고,메뉴 등) -->
<div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

   <div id="wrap" style="-ms-overflow-style: none;">
   
   <div id="content">
   
	<form action="${pageContext.request.contextPath}/MemberPwChangeAction.me" id="change" method="post" name="changePw">
		<h2>비밀번호 변경</h2><br><br>
		<div>
			<label class="t2">현재 비밀번호</label><br>
				<input type="password" id="pw" class="pw"><br>
				<button type="button" class="btn" onclick="pwCheck()">확 인</button><br><br>
				<div class="check" id="check"></div>
			<label class="t3">새 비밀번호</label><br>
				<input type="password" id="newPw" class="newPw" name="newPw"><br>
			<label class="t4">새 비밀번호 확인</label><br>
				<input type="password" id="newPwCheck" class="newPwCheck" name="newPwCheck"><br>
		
		<div id="button">
			<input type="button" value="변 경" class="btn1" id="ibtn" onclick="check()">
			<button type="button" class="btn2" onclick="location.href='${pageContext.request.contextPath}/MemberMypageAction.me'">취 소</button></div>	
		</div>
	</form>

</div>   
</div>
<!-- 본문내용 -->
   
<!-- 푸터 시작 -->
<div id="footer"><%@include file="../inc/footer.jsp" %></div>

</body>
</html>