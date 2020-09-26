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
<link href="css/history.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(function(){

		  $.ajax({
				type:"get",
				url:"Member_HistoryListAction.me",
				dataType : "json",
				success : function(data) {
					var html = "";
		            if(data == 1 || data == 2){
		               html += "<tr>";
		               html += "<td colspan='10'>정보가 존재하지 않습니다.</td>";
		               html += "</tr>";
		            }
		            else{
		               for(var i = 0; i<data.length; i++){
		                  html += "<tr>";
		                  html += "<td class='idex'>" + data[i].UserID + "</td>";
		                  html += "<td class='seq'>" + data[i].ITEM_SEQ + "</td>";
		                  html += "<td class='name"+i+"'>" + data[i].ITEM_NAME + "</td>";
		                  html += "<td class='num'>" + data[i].ENTP_NAME + "</td>";
		                  html += "<td class='material'>" + data[i].MATERIAL_NAME + "</td>";
		                  html += "<td class='valid'>" + data[i].VALID_TERM + "</td>";
		                  html += "<td class='side_effect'>" + data[i].Side_Effect + "</td>";
		                  html += "<td class='cycle'>" + data[i].cycle + "</td>";
		                  html += "<td class='time'>" + data[i].time + "</td>";
		                  html += "</tr>";
		               }
		               
		            }
		            $("#notice").append(html);
				},
				error : function(request, status, error) {
					alert("message:" + request.responseText + "\n" + "error:"
							+ error);
				}
			});
});

</script>

</head>
<body>

	<!-- 헤더 (로고,메뉴 등) -->
	<div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

	<div id="wrap" style="-ms-overflow-style: none;">
	
	<div id="content">
	<h3 id="text" align="left">* 약 이름을 클릭하면 복용 타임을 그래프로 확인할 수 있습니다.</h3><br>
		<table id="notice"> 
		 <tr>
		 	<th class="id">ID</th>
		 	<th class="seq">회사</th>
		 	<th class="name">약이름</th>
		 	<th class="num">품번</th>
		 	<th class="material">성분</th>
		 	<th class="valid">유효기간</th>
		 	<th class="side_effect">부작용</th>
		 	<th class="cycle">주기</th>
		 	<th class="date">복용날짜</th>
		 <tr>
		</table>
	</div>	
	</div>
<!-- 본문내용 -->

	<!-- 푸터 시작 -->
	<div id="footer"><%@include file="../inc/footer.jsp" %></div>

</body>
</html>