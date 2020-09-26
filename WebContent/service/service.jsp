<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.service.db.serviceBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
<link href="css/service.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<%  
String idd=(String)session.getAttribute("id");
serviceBean sBean = new serviceBean();
List<serviceBean> boardList=(List<serviceBean>)request.getAttribute("boardList");
int count=((Integer)request.getAttribute("count")).intValue();
String pageNum=(String)request.getAttribute("pageNum");
int pageCount=((Integer)request.getAttribute("pageCount")).intValue();
int pageBlock=((Integer)request.getAttribute("pageBlock")).intValue();
int startPage=((Integer)request.getAttribute("startPage")).intValue();
int endPage=((Integer)request.getAttribute("endPage")).intValue();
String ser = request.getParameter("search");
if(ser == null){
   ser = "";
}
%>
<script type="text/javascript">
   function ser() {
      
      var ser = document.getElementById("searchin").value;
      window.location ="./serviceSearchList.se?search="+ser;
      
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
      <div class="h2"><h2>고객센터&nbsp;&nbsp;Q&A</h2></div>
      
      <!-- 글쓰기 버튼이 로그인 시에만 작동  -->
      <%
      String id = (String)session.getAttribute("id");
      if(id!=null){
      %>
      <div class="write">
         <button type="button" class="btn" 
            onclick="location.href='./serviceWrite.se'">글쓰기</button>
      </div>
      <% } else if(id == null){ %>
      
      <!-- 글쓰기 버튼 로그인 아닌 경우 로그인 페이지로 이동 -->
      <div class="write">
         <button type="button" class="btn" 
            onclick="alert('로그인 후 이용 가능합니다.')">글쓰기</button>
      </div>
      <% } %>
      <!-- 검색  -->
      <div class="search">   
         <input type="text" id="searchin" placeholder="문의 내용을 검색하세요" value=<%= ser %>>&nbsp;
         <button type="button" class="btn" onclick="ser();">검 색</button>
      </div>
   </div><br>
      
   <!-- 게시판 -->
   <table id="notice"> 
     <tr>
        <th class="tn">No</th>
        <th class="tt">제 목</th>
        <th class="tw">작성자</th>
        <th class="td">작성날짜</th>
        <th class="tr">조회수</th>
     </tr>
   <%
   if(count != 0){
   
   for (int i = 0 ; i<boardList.size() ; i++) {
      sBean = boardList.get(i);
      
      if(id != null ) {
      if(id.equals("admin")){
         %>
       <tr onclick="location.href='./serviceContentAction.se?num=<%=sBean.getService_num() %>&pageNum=<%=pageNum%>&search=<%=ser%>'">
      <%   
      }
      else{
      %>
       <tr onclick="location.href='./checkAction.se?num=<%=sBean.getService_num() %>&pageNum=<%=pageNum%>&search=<%=ser%>'">
      <%   
      }
      }
      %>
       
      
         <td class="tn"><%=sBean.getService_num() %></td>   
        <td class="tt tt1">&nbsp;<!-- 제목 좌물쇠 이미지 넣기 -->
         <img src="./images/lock.png" width="21">&nbsp;
        <%
          int wid=0;
           
          if(sBean.getService_re_lev()>0){
             wid=sBean.getService_re_lev()*10;
           
          %><!-- 리플 -->
             <img src="./images/level.gif" width="<%=wid%>">
             <img src="./images/re_pill.png" width="38">
          <%
          }
          %>
          <!-- 파일  -->
          <% 
              if(sBean.getService_file() != null){
          %>
              <img src="./images/clip.png" width="12">
          <%      
              }
          %>
          <!-- 제목 --> 
          &nbsp;<%=sBean.getService_subject()  %> 


        </td>
        <td class="tw"><%=sBean.getService_id() %></td>
        <td class="td"><%=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(sBean.getService_date())%></td>
        <td class="tr"><%=sBean.getService_readcount() %></td>
     </tr>
    <%}
   }else {%>
   
   
   <tr>
        <td colspan="5">작성된 내용이 없습니다</td>
   </tr>
   
   <% 
   }%>
   </table>


   <!-- 페이지 번호 -->
   <div id="page">
      <%
      if(count!=0){
         if(startPage > pageBlock){
      %>
         <a href="./serviceList.se?pageNum=<%=startPage-pageBlock%>"> &lt; </a>
      <%
         }
         for(int i=startPage;i<=endPage;i++){
      %>
         <a href="./serviceList.se?pageNum=<%=i%>">&nbsp;<%=i %>&nbsp;</a>
      <%
         }
         if(endPage < pageCount){
      %>
         <a href="./serviceList.se?pageNum=<%=startPage+pageBlock%>"> &gt; </a>
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