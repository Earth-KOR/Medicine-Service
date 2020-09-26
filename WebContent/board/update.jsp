<%@page import="com.board.db.boardBean"%>
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
<link href="css/write.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">/* 포커스 주기 */
	$(document).ready(function(){ $('#title').focus(); });
</script>

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

</head>
	<%
	
		String userid = (String)session.getAttribute("id");
		
		if(userid==null){
			response.sendRedirect("./MemberLogin.me");
		}
		
		boardBean bean=(boardBean)request.getAttribute("bean");
		String pageNum = request.getParameter("pageNum");
	%>
<body>
<!-- 헤더 (로고,메뉴 등) -->
<div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

<div id="wrap" style="-ms-overflow-style: none;">
<div id="content">

<!-- 테이블 -->

	<form action="./boardUpdateAction.bo?pageNum=<%=pageNum %>" method="get" encType="multipart/form-data">
	<input type="hidden" name="num" value="<%=bean.getNum()%>">
	<table id="notice"> 
	  <tr>
	   	<th class="tw">작성자</th>
	  	<td class="tw1"><input type="text" name="id" value="<%=userid %>" readonly></td>
	  </tr>
	  
	   <tr>
	  	<th class="tt">제목</th>
	  	<td class="tt1" colspan="7"><input type="text" id="title" name="subject" value="<%=bean.getSubject() %>"></td>
	  </tr>
	  <tr>
        <th>첨부파일</th>
        <td class="tf" colspan="7"><input type="file" name="file" value="<%=bean.getFile() %>"/></td>
      </tr>
	  <tr></tr>
	  <tr>
	  	<td class="tc" colspan="8">
	  	 <textarea class="form-control" id="p_content" name="content" ><%=bean.getContent() %></textarea>
			<script type="text/javascript">CKEDITOR.replace('p_content', {height: 350});
			
			</script>
	  	</td>
	  </tr>
	  <tr>
        <td colspan="8">
	        <button class="btn"  type="button" value="목록보기"
	        onclick="location.href='./boardListAction.bo?pageNum=<%=pageNum%>''">목록보기</button>
	        <button class="btn"  type="reset" value="다시작성">다시쓰기 </button>
	        <button class="btn1" type="submit"  value="수정하기">수정하기</button>
        </td>
       </tr>
	</table>
	</form>

<!-- 본문내용 -->
	</div>	
	</div>
<!-- 푸터 시작 -->
<div id="footer"><%@include file="../inc/footer.jsp" %></div>

</body>
</html>