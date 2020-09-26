<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.board.db.commentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.board.db.boardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YAKBBAL</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/footer.css" rel="stylesheet" type="text/css">
<link href="css/content_board.css" rel="stylesheet" type="text/css">

   <%
    boardBean bean = new boardBean();
    commentDTO cdto = new commentDTO();
    List<commentDTO> boardlist = new ArrayList <commentDTO>();
    
    bean = (boardBean)request.getAttribute("bean");
     
    boardlist =( List<commentDTO> ) request.getAttribute("comment_list");
   
     String pageNum = (String)request.getAttribute("pageNum");
   %>
</head>

<%-- <script type="text/javascript">
   $("#boardDelete").click(function(){
      
      var result = confirm("게시글을 삭제하시겠습니까?");
      
      if(result){
         location.href='${pageContext.request.contextPath}/boardDeleteAction.bo?num=<%=bean.getNum()%>';
      }
      
   });
</script> --%>
<body>

<!-- 헤더 (로고,메뉴 등) -->
<div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

<div id="wrap" style="-ms-overflow-style: none;">
<div id="content">

<!-- "수정,삭제 "버튼은 작성자에게만 노출  / 작성자 외에는 "목록 "버튼만 보이기 -->
    <div id="button">
       <%
         String userid = (String)session.getAttribute("id");
         if(userid != null){
            if(userid.equals(bean.getId())){
      %>
      <div class="update">
         <button class="btn" onclick="location.href='${pageContext.request.contextPath}/boardUpdate.bo?num=<%=bean.getNum()%>&pageNum=<%=pageNum%>'">수 정</button>
      </div>
      <%System.out.println(""); %>
      <div class="delete">
         <button class="btn" onclick="location.href='${pageContext.request.contextPath}/boardDeleteAction.bo?num=<%=bean.getNum()%>'">삭 제</button>
         <!-- <button class="btn" id="boardDelete">삭 제</button> -->
      </div>
      <%
         }
      }
      %>
      <div class="list">
         <button class="btn1" onclick="location.href='./boardListAction.bo'">목 록</button>
      </div>      
    </div>   <br>


<!-- 테이블 -->
<form method="get" encType="multipart/form-data">
   <table id="notice"> 
     <tr>
        <th class="tn">No</th>
        <td class="tn1"><%=bean.getNum() %></td>
        <th class="tw">작성자</th>
        <td class="tw1"><%=bean.getId() %></td>
        <th class="td">작성일</th>
        <td class="td1"><%=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(bean.getDate()) %></td>
       <th class="tr">조회수</th>
        <td class="tr1"><%=bean.getReadcount() %></td>
     </tr>
     <tr></tr>
     <tr>
        <th class="tt">제목</th>
        <td class="tt1" colspan="7"><%=bean.getSubject() %></td>
     </tr>
     <tr>
     	<% if(bean.getFile() == null) {
     	%>
<!--      		<th class="tt">첨부파일</th>
     		<td class="tt1" colspan="7"> 첨부파일 없음 </td> -->
     	<%	
     	} else {
     	%>
        	<th class="tt">첨부파일</th>
        	<td class="tt1" colspan="7"><a href="./board/download.jsp?path=upload&name=<%=bean.getFile()%>"><%=bean.getFile() %></a></td>
   	</tr>
        <%	
     	}
     	%>

     <tr>
        <td class="tc" colspan="8">
           <%=bean.getContent() %>
        </td>
     </tr>
   </table>
</form>
<br>

<!-- 댓글창 로그인 시에 보이게 -->
 <!-- 댓글 목록 -->
    <div id="comment">
    
      <%
         String idd = (String)session.getAttribute("id");
         if(idd != null){
      %>

	 <!-- 댓글입력 -->    
	     <div id="commentWrite">
	       <form action="./commentWriteAction.bo">
	       
	       <input type="hidden" value="<%=bean.getNum()%>" name="board_num">
	       <input type="hidden" value="<%=userid%>" name="id">      
	       <input type="hidden" value="<%=pageNum%>" name="pageNum">
	       
	 	  <table id="commentWrite">
	         <tr>
	          <td class="tcw">
	           <span style="color:#004040;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	           		댓글쓰기</span><input type="text" class="input" name="com_con">
	            <button type="submit" class="btn3">입 력</button>
	          </td>
	         </tr>   
	      </table>
	      
	      </form>
	   </div>

      <% } else{ } %>


	<!-- 댓글보이기  -->
      <table id="comment">

	 <%
         for (int i = 0 ; i<boardlist.size() ; i++) {
         commentDTO bean2 = new commentDTO();
         bean2 = boardlist.get(i);
     %>
      <tr>        
      <td class="cc" colspan="7"><small style="color:gray;"><%=bean2.getId()%></small> &nbsp;&nbsp;&nbsp; <small style="color:gray;"> <%=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(bean2.getDate())%> </small> <br> <%=bean2.getCom_con()%>  </td>

          <td class="ccc" colspan="1">
               <%
               if(userid != null){
                  if(userid.equals(bean2.getId())){
               %>
               <div class="delete1"><!-- 삭제버튼  -->
                  <button class="btn4" onclick="location.href='./commentDeleteAction.bo?board_num=<%=bean.getNum()%>&pageNum=<%=pageNum%>&comment_num=<%=bean2.getComment_num()%>' ">X</button>
               </div>
               <%
               }
            }
         }
               %>
          </td> 
     </tr>
      </table> 
      
   </div>
 
<!-- 본문내용 -->
   </div>   
   </div>
<!-- 푸터 시작 -->
<div id="footer"><%@include file="../inc/footer.jsp" %></div>

</body>
</html>