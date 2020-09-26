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
<link href="css/findId.css" rel="stylesheet" type="text/css">
</head>
<body>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.js"></script> 
<script type="text/javascript">/* 포커스 주기 */
   $(document).ready(function(){$('#name').focus();});
</script>
<script type="text/javascript">
	var name ;
	var email ;

	function findid(){


		var name = $("#name").val();
		var email = $("#email").val();
		var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		if(!emailRule.test(email)) {            
			alert("이메일을 형식에 맞게 입력해 주세요.");
            return false;
} 

		
	
		if((email=="")||(name=="")){
			alert("이름과 아이디 모두 입력바랍니다.");
			
			   
		}else{ 
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/MemberIdFindAction.me', 
				data:{name:name, email:email},
				success : function(data){
					if(data == null){
						$("#find").html("존재하지 않는 아이디 입니다.");
					}else{
						
						$("#find").html("<br><center>찾으신 아이디는&nbsp;&nbsp;<b>"+data+"</b>&nbsp;&nbsp;입니다.</center><br>");
						
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

<form action="./MemberIdFindAction.me" method="post">
	<h2>아이디 찾기</h2><br>
	<div>
			<label class="t1">가입시 등록한 이름과 이메일 주소를 입력해주세요.</label><br><br><br>

			<label class="t2">이름 *</label><br>
				<input type="text" name="name" class="name" id="name"><br>
			<label class="t3">이메일 주소 *</label><br>
				<input type="text" name="email" class="email" id="email"><br>
			<div class="find" id="find"></div>
				<button type="button" class="btn1" onclick="findid()">아이디 찾기</button>&nbsp;
				<button type="button" class="btn" 
						onclick="location.href='${pageContext.request.contextPath}/MemberLogin.me'">
						로그인 페이지</button>

	</div>
</form>


</div>   
</div>
<!-- 본문내용 -->
   
<!-- 푸터 시작 -->
<div id="footer"><%@include file="../inc/footer.jsp" %></div>

</body>
</html>