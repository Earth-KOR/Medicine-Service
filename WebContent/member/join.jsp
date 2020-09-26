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
<link href="css/join.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">/* 포커스 주기 */
   $(document).ready(function(){$('#name').focus();});
</script>

<!-- 유효성 체크 -->
<script type="text/javascript">
   function check(){
	   
	   var idCheck = $('#ss').val()
	  	var idx = $("#id").val();
	   if(idCheck =="" || idx =="" ) {
		   alert("아이디 중복체크 바랍니다 !");
		   $("#btn").focus();
		   return false;
	   }
	   
      //비밀번호
      var pwCheck = RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,12}$/);
      //생년월일
      var birthCheck = RegExp(/^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/);
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
      //비밀번호 일치 체크
      if($('#pw').val()!=$('#pwCheck').val()){
        alert("비밀번호가 일치하지 않습니다.");
        $("#pwCheck").val("");
        $("#pwCheck").focus();
        return false;
        }
      //생년월일
      if(!birthCheck.test($("#birth").val())){
         alert("생년월일은 (19990101)의 형식에 맞게 입력해주세요");
         $("#birth").val("");
         $("#birth").focus();
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
     	  document.join.action = "./MemberJoinAction.me";
     	  document.join.method = "post";
     	  document.join.submit();
   }
 
</script>

<script type="text/javascript">

	//아이디 중복체크
	function idcheck() {		
		$('#ss').val('값 추가');		
		var id = $("#id").val();

		var idCheck = RegExp(/^[a-zA-Z0-9]{4,12}$/);	
		 if(!idCheck.test(id)){
	         alert("아이디는 영문,숫자 혼합 4자~12자 이내의 형식에 맞게 입력해주세요");
	         $("#id").val("");
	         $("#id").focus();
	         return false;
	      }
	
		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/MemberDuplicateAction.me',
			data : {id : id},
			success : function(result) {
				//alert(result)
				if (result == 1) {
					$("#check").html("사용중인 아이디입니다.");
					$("#id").val("");
				} else {
					$("#check").html("사용할 수 있는 아이디 입니다.");
					$("#id").focus();
				}
			}
		});
	}
</script>
</head>
<body>


<!-- 헤더 (로고,메뉴 등) -->
<div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

   <div id="wrap" style="-ms-overflow-style: none;">
   
   <div id="content">

   <h2> Join Us </h2>
   <br>
   <form action="./MemberJoinAction.me" method="post" id="join" name="join">
   <fieldset> 
	  <label class="t1">필수 입력사항 *</label><br><br>
	  <label class="t2">이름 *</label><br>
	      <input type="text" id="name" name="name" required/><br>
	  <label class="t3">아이디 *</label><br>
	      <input type="text" id="id" name="id" required/><br>
	      <button type="button" class="btn" onclick="idcheck()" id="btn">중복확인</button>
	      <div class="check" id="check"> </div>
	  <label class="t4">비밀번호 *</label><br>
	      <input type="password" id="pw" name="pw" placeholder="영문,숫자,특수문자  8자~12자 이내" required/><br>
	  <label class="t5">비밀번호 확인 *</label><br>
	      <input type="password" id="pwCheck" name="pwCheck" placeholder="** 비밀번호 확인" required/><br>
	  <label class="t6">생년월일 *</label><br>	
	      <input type="text" id="birth" name="birth" placeholder="ex) 19990101" required/><br>
	  <label class="t7">핸드폰 번호 *</label><br>
	      <input type="text" id="phone" name="phone" placeholder="ex) 01012341234" required/><br>
	 <label class="t8">이메일 주소 *</label><br>	
	      <input type="text" id="email" name="email" required/><br>
	      <br>
	 <label class="t9">복용 중인 약</label><br>
	      <input type="text" id="my_Pill" name="my_Pill"><br>
	 <label class="t10">부작용 있는 약</label><br>
	      <input type="text" id="my_Bujakyong" name="my_Bujakyong"><br>
	 <label class="t11">통원 병원 이름</label><br>
	      <input type="text" id="my_Hospital" name="my_Hospital"><br><br>
	   </fieldset>

      <div id="button">
         <input type="button" value="JOIN !" class="submit" onclick="check()">
      </div>
   </form>
   
<input type="hidden" id="ss" >
</div>   
</div>
<!-- 본문내용 -->
   
<!-- 푸터 시작 -->
<div id="footer"><%@include file="../inc/footer.jsp" %></div>

</body>
</html>