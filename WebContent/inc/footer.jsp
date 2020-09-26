<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../css/footer.css" rel="stylesheet" type="text/css">

</head>
<body>

<div id="footer">
 	<div class="text">
	 	<p>
			YAKBBAL&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;관리자 : 6강4조&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			Tel : 051-123-1234&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;이메일 : yakbbal@yakbbal.com
			&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;사업자등록번호 : 123-12-12345<br>
			주소 : 부산광역시 부산진구 동천로 109 삼한골든게이트빌딩7층 6 Class&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			Copyright ⓒ 2020- YAKBBAL.&nbsp;&nbsp;&nbsp;All rights reserved<br>
			<span>
				<a href="./serviceList.se">
					고객지원&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;CUSTOMER SUPPORT</a>
			</span>	
		</p>
	</div>
	
      <%
         String id2 = (String)session.getAttribute("id");
    	  if(id2 != null ) {
          if(id2.equals("admin")){
      %>
		<div class="adminChat">
			<button class="b"><a href="" onclick="window.open('chat/admin.jsp','admin','width=750, height=750, resizable=no')">
			관리자 채팅</a></botton>
		</div>
      <% }}%>	
	
	<div class="chat">
		<img alt="웹 채팅" src="images/pc_btn_chat.png" onclick="window.open('./RealChat.me','new','width=800, height=600, resizable=no')">
	</div>
	
</div>

</body>
</html>