<%@ page import="org.jsoup.Jsoup" %>
<%@ page import="org.jsoup.nodes.Document" %>
<%@ page import="org.jsoup.nodes.Element" %>
<%@ page import="org.jsoup.select.Elements" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YAKBBAL</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/footer.css" rel="stylesheet" type="text/css">
<link href="css/news.css" rel="stylesheet" type="text/css">


  <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
  <link rel="icon" href="data:;base64,iVBORw0KGgo=">

<%
   String link ="http://www.health.kr/notice/press.asp?search_value=&search_term=&paging_value=1&setLine=";

   Document doc = Jsoup.connect(link).get();
   Elements table_tr = doc.select(".list_basic>tbody>tr");
   Elements div_a = doc.select("#paging>a");
   //System.out.println(div_a);  
   Elements div_span = doc.select("#paging>span>a");
   //System.out.println(div_span);
%>
</head>
<body>

<!-- 헤더 (로고,메뉴 등) -->
<div id="header"><%@include file="../inc/header.jsp" %></div>
<!-- 본문내용 -->

	<div id="wrap" style="-ms-overflow-style: none;">
	<div id="content">
	
      <h2>&nbsp;&nbsp;보도자료</h2><br>
      
         <table id="notice">
            <tr>
               <th class="tn">No</th>
               <th class="tt">제 목</th>
               <th class="tw">첨부파일</th>
               <th class="td">작성일</th>
            </tr>
	<!-- 글내용  -->
<%
	for(int i = 0; i<15; i++){
		
		out.println("<tr>");
		
		String str = table_tr.get(i+1).getElementsByTag("td").get(1).getElementsByTag("a").attr("href");
		
		Elements tTag = table_tr.get(i+1).getElementsByTag("td");
		
		tTag.get(1).getElementsByTag("a").attr("href", "http://www.health.kr/notice/" + str);
		tTag.get(1).getElementsByTag("a").attr("target", "blank");
		tTag.get(1).getElementsByTag("img").remove();
		
		out.println(tTag);
		
		out.println("</tr>");
		
		}

			for(int i=0; i<4; i++){
				
				
				String str1 = div_a.get(i).getElementsByTag("a").attr("href");
				
				Elements aTag = div_a.get(i).getElementsByTag("a");
				
				aTag.attr("href", "http://www.health.kr/notice/" + str1);
				
				out.println(aTag);
			}
			
%>
         </table><br><br>
 	<div id="comment">      
		<%       
		for(int i=0; i<10; i++){
			
			out.println("<span>");
			
			String str2 = div_span.get(i).attr("href");
							
			Element spanTag = div_span.get(i);
			
			spanTag.attr("href", "http://www.health.kr/notice/" + str2);
			
			out.println(spanTag);
			
			out.println("</span>");
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