
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Web Socket Example</title>
<style>
div {
	width: 100%;
	height: 300px;
}

div.left {
	width: 50%;
	float: left;
	box-sizing: border-box;
}

div.right {
	width: 50%;
	float: right;
	box-sizing: border-box;
}
</style>
</head>
<body>
	<div>
		<div class="left">
			<!-- 텍스트 박스에 채팅의 내용을 작성한다. -->
			<input id="textMessage" type="text" onkeydown="return enter()">
			<!-- 서버로 메시지를 전송하는 버튼 -->
			<input value="Send" type="button" onclick="qna_write()"> <br />
			<!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->
			<textarea id="messageTextArea" rows="10" cols="50"
				style="width: 320px; height: 500px; resize: none;"
				disabled="disabled"></textarea>
		</div>
		<!------------------------------------------------------------------------------------------------->
		<div class="right">	
			<br> <b style="padding-left: 30%">자주 묻는 질문</b><br>
			<br> <input type="text" id="qna_subject" disabled="disabled"
				size="43" value="질문과 유사한 기존 답변"><br>
			<br>
			<textarea rows="10" cols="50" id="qna_content"
				style="width: 330px; height: 400px; resize: none;"
				disabled="disabled"></textarea>
			<br>
			<br> <input type="button" value="실시간 상담사 채팅" onclick="location.href='RealChat.bk'">
		</div>
	</div>
	<script src="../js/jquery-3.5.1.js"></script>
	<script type="text/javascript">
	JQfun = null;
	var checkAnswer = "**************************************" + "\n";
	$(function() {
		function checkQna() {
			$.ajax({
			url:"chatBotAI.bk",
			dataType: "json",
			data:{
				subject:$('#textMessage').val()
			},
			success:function(returndata){
				var result = returndata;
				$.each(result,function(key, value){
					checkAnswer += value;
				});
					checkAnswer += "**************************************"+"\n";
					$('#qna_content').val(checkAnswer);
			}
		});
		}
		JQfun = checkQna;
	})
    // 콘솔 텍스트 영역	
    var messageTextArea = document.getElementById("messageTextArea");	
    messageTextArea.value += "궁금한 것이 있다면 입력해 주세요.\n";
    messageTextArea.value += "ex)예약,리뷰,가격...\n";
    function qna_write() {
    	let message = document.getElementById("textMessage");
    	JQfun();
    	document.getElementById("qna_subject").value = "*"+message.value+"* 에 관련된 답변들(최대 2개)";
    	if(message.value != "")
    		{
    		  messageTextArea.value += "(me) => " + message.value + "\n";
    		  message.value = ""; 
    		  document.getElementById("messageTextArea").scrollTop = document.getElementById("messageTextArea").scrollHeight;
    		  document.getElementById("qna_content").scrollTop = document.getElementById("qna_content").scrollHeight;
    		}
	}	
    // 텍스트 박스에서 엔터를 누르면	
    function enter() {	
      // keyCode 13은 엔터이다.	
      if(event.keyCode === 13) {	
    	 qna_write();
        return false;	
      }	
      return true;	
    }

  </script>



</body>
</html>