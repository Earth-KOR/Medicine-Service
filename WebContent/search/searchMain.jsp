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
<link href="css/searchMain.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">/* 포커스 주기 (적용 안되는 상태)*/
	$(document).ready(function(){ $('#name').focus(); });
</script>

<style> 
     .ui-autocomplete { 
      width: 300px; 
      max-height: 200px; 
      overflow-y: auto; 
/*       overflow-x: auto;  */
      /* add padding to account for vertical scrollbar */ 
      padding-right: 20px; 
     } 
</style> 

<script>
	$(function() { //화면 다 뜨면 시작
		$("#tags").autocomplete({
			source : function(request, response) {
				var ser = $("#tags").val();
				$.ajax({
					type : 'get',
					url : "search_medicine.do",
					dataType : "text",
					data : { param : ser },
					success : function(data) {
						console.log(data);
						var sublist1 = data.substring(1);
						var sublist2 = sublist1.slice(0, -1);
						var list = new Array();
						list = sublist2.split(', ');

						response(list);
					}

				});
			}, // source 는 자동 완성 대상
			select : function(event, ui) { //아이템 선택시
				console.log(ui);//사용자가 오토컴플릿이 만들어준 목록에서 선택을 하면 반환되는 객체

			},
			focus : function(event, ui) { //포커스 가면
				return false;//한글 에러 잡기용도로 사용됨
			},
			minLength : 1,// 최소 글자수
			autoFocus : true, //첫번째 항목 자동 포커스 기본값 false
			scroll : true,
			classes : { //잘 모르겠음
				"ui-autocomplete" : "highlight"
			},
			delay : 500, //검색창에 글자 써지고 나서 autocomplete 창 뜰 때 까지 딜레이 시간(ms)
			//        disabled: true, //자동완성 기능 끄기
			position : {
				my : "right top",
				at : "right bottom"
			}, //잘 모르겠음
			close : function(event) { //자동완성창 닫아질때 호출
				console.log(event);
			}

		});

	});
	
// 	function search_move() {
		
// 		var ser = $("#tags").val();
		
// 		$.ajax({
// 			type : 'get',
// 			url : "search_medicineName.do",
// 			data : { param : ser },
// 			success : function(data) {
// 				if(data == 1){
// 					window.location = "./search_move.do?ser="+ser;
// 				}
// 				else{
// 					alert("등록된 약의 성분이 존재 하지 않습니다.");
// 				}
// 			}
// 		});
		
		
// 	}
	function search_move() {
		
		var ser = $("#tags").val();
		window.location = "./search_move.do?ser="+ser;
		
	}
</script>
</head>
<body>
	<!-- 헤더 (로고,메뉴 등) -->
	<div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

	<div id="wrap" style="-ms-overflow-style: none;">
	
	<div id="content">

		<div class="ui-widget">
		     <input type="text" placeholder="약 이름을 적어주세요" id="tags">
		</div>
		<div class="button">
		     <button type="button" onclick="search_move();">검 색</button>
		</div>

	</div>	
	</div>
<!-- 본문내용 -->

	<!-- 푸터 시작 -->
	<div id="footer"><%@include file="../inc/footer.jsp" %></div>

</body>
</html>