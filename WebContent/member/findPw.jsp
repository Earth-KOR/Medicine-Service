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
<link href="css/findPw.css" rel="stylesheet" type="text/css">
</head>
<body>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.js"></script> 
<script type="text/javascript">/* 포커스 주기 */
   $(document).ready(function(){$('#id').focus();});
</script>

<script type="text/javascript">

var id ;
var email ;

	
	function findPw(){
		
	
		var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		var id = $("#id").val();
		var email = $("#email").val();
		
		if(!emailRule.test(email)) {            
			alert("이메일을 형식에 맞게 입력해 주세요.");
            return false;
} 

		 
		 
		
		if((email=="")||(id=="")){
			alert("아이디, 이메일 모두 입력바랍니다.");
			 if(!emailCheck.test(email)){
		         alert("email의 형식에 맞게 입력해주세요");
		         $("#email").val("");
		         $("#email").focus();
		         return false;
		      }
		}else{
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/MemberPwFindAction.me', 
				data:{id:id, email:email},
				success : function(data){// 0: 실패, 1: 성공, -1: 메일 발송 실패
					
				   if(data == 0){
						alert("해당 정보의 회원이 없습니다.");
					}else if( data == -1){
						alert("임시 비밀번호 발급에 실패했습니다.");
					}else{
						alert("임시 비밀번호를 발급했습니다. \n다시 로그인 해주시기 바랍니다.");
						
						document.findid.action = "./MemberLogin.me";
				     	document.findid.method = "post";
				     	document.findid.submit();
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

<form action="./MemberPwFindAction.me" method="post" name="findid">
	<h2>비밀번호 찾기</h2><br>
	<div>
		<label class="t1">
		회원님의 아이디와 이메일을 입력해주세요.<br>
		작성하신 이메일로 임시비밀번호를 보내드립니다.<br>
		</label><br><br>
		
		<label class="t2">아이디 *</label><br>
			<input type="text" name="id" class="id" id="id" ><br>
		<label class="t3">이메일 주소 *</label><br>
			<input type="text" name="email" class="email" id="email" ><br>
	
			<button type="button" class="btn1" onclick="findPw()">임시 비밀번호 발송</button>&nbsp;
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