<%@page import="com.board.db.commentDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
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
<link href="css/board.css" rel="stylesheet" type="text/css">

   <%  
      boardBean bean = new boardBean();
   
      List<boardBean> boardList = (List<boardBean>)request.getAttribute("boardList");
      
      int count = ((Integer)request.getAttribute("count")).intValue();
      String pageNum = (String)request.getAttribute("pageNum");
      int pageCount = ((Integer)request.getAttribute("pageCount")).intValue();
      int pageBlock = ((Integer)request.getAttribute("pageBlock")).intValue();
      int startPage = ((Integer)request.getAttribute("startPage")).intValue();
      int endPage = ((Integer)request.getAttribute("endPage")).intValue();
      
   %>
   
   <script type="text/javascript">
      function ser() {
         
         var ser = document.getElementById("searchin").value;
         window.location ="./boardSearchList.bo?search="+ser;
         
      }
   </script>
</head>
<body>
   <!-- 헤더 (로고,메뉴 등) -->
   <div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

   <div id="wrap" style="-ms-overflow-style: none;">
   
   <div id="content">

   <!-- 상단 -->
   <div class="top">
      <div class="h2"><h2>커뮤니티</h2></div>
      
      <!-- 글쓰기 버튼 / 로그인 시에만 노출 -->
      <%
         String id3 = (String)session.getAttribute("id");
         if(id3 != null){
      %>
      <div class="write">
         <button class="btn" onclick="location.href='./write.bo'">글쓰기</button>
      </div>
      <% } else{ %>
      
      <!-- 글쓰기 버튼 로그인 아닌 경우 로그인 페이지로 이동 -->
      <div class="write">
         <button type="button" class="btn" 
            onclick="alert('로그인 후 이용 가능합니다.')">글쓰기</button>
      </div>
      <% } %>
      <!-- 검색  -->
      <div class="search">   
         <input type="text" id="searchin" placeholder="제목을 검색하세요">&nbsp;
         <button class="btn" onclick="ser();">검 색</button>
      </div>
   </div><br>
   
   <!-- 게시판 -->
   <table id="notice"> 
     <tr>
        <th class="tn">No</th>
        <th class="tt">제 목</th>
        <th class="tw">작성자</th>
        <th class="td">작성일</th>
        <th class="tr">조회수</th>
     </tr>
        <%
         if(count != 0){
      
         for (int i = 0 ; i<boardList.size() ; i++) {
         bean = boardList.get(i);
      
      %>
      <tr onclick="location.href='./boardContentAction.bo?num=<%=bean.getNum() %>&pageNum=<%=pageNum%>'">
         <td class="tn"><%=bean.getNum() %></td>   
        <%
           if(bean.getComment_count() > 0){
        %>
           <td class="tt tt1">&nbsp;&nbsp;&nbsp;
              <%=bean.getSubject() %>
           <!-- 파일  -->
          <% 
              if(bean.getFile() != null){
          %>
              <img src="./images/clip.png" width="12">
          <%      
              }
          %>
           <!-- 댓글수 -->
              [<%=bean.getComment_count()%>]
          </td>
        <%      
           }
           else{
        %>
        <td class="tt tt1">&nbsp;&nbsp;&nbsp;<%=bean.getSubject() %>
        
          <!-- 파일  -->
          <% 
              if(bean.getFile() != null){
          %>
              <img src="./images/clip.png" width="12">
          <%      
              }
          %>
        
        </td>
        <%      
           }
        %>
              
        <td class="tw"><%=bean.getId()%></td>
        <td class="td"><%=new SimpleDateFormat("yyyy.MM.dd HH:MM:SS").format(bean.getDate())%></td>
        <td class="tr"><%=bean.getReadcount()%></td>
        </tr>
        <%}
      }else {%>
      <tr>
           <td colspan="5">작성된 내용이 없습니다</td>
      </tr>
   
      <% }%>
      
   </table>


   <!-- 페이지 번호 -->
   <div id="page">
      <%
      if(count!=0){
         if(startPage > pageBlock){
      %>
         <a href="./boardListAction.bo?pageNum=<%=startPage-pageBlock%>"> < </a>
      <%
         }
         for(int i=startPage;i<=endPage;i++){
      %>
         <a href="./boardListAction.bo?pageNum=<%=i%>"><%=i %></a>
      <%
         }
         if(endPage < pageCount){
      %>
         <a href="./boardListAction.bo?pageNum=<%=startPage+pageBlock%>"> > </a>
      <%
         }
      }
      %>
   </div>
      
   
   </div>   
   </div>
<!-- 본문내용 -->

   <!-- 푸터 시작 -->
   <div id="footer"><%@include file="../inc/footer.jsp" %></div>

</body>
</html>