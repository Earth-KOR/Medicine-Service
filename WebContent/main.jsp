<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YAKBBAL</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>

<body>

	<filter id="img">
		<div id="pill">
		
		 <a href="./pill.me">
		  &nbsp;약복용<br><br>
		  <img src="${pageContext.request.contextPath}/images/pill.png" width="320" class="img">
		 </a>
		</div>	
	
		<div id="search">
		 <a href="./searchMain.me">
		  &nbsp;약검색<br><br>
		  <img src="${pageContext.request.contextPath}/images/search.png" width="320" class="img">
		 </a>
		</div>	
	
		<div id="pharmacy">
		 <a href="./pharmacy.me">
		  &nbsp;약국찾기<br><br>
		  <img src="${pageContext.request.contextPath}/images/pharmacy.png" width="320" class="img">
		 </a>
		</div>	
	</filter>
</body>
</html>