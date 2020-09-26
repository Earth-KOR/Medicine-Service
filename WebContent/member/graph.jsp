<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<!DOCTYPE HTML>
<html>
<head>
<title>YAKBBAL</title>
<link href="css/graph.css" rel="stylesheet" type="text/css">

<%
HashMap<String, String> list = (HashMap)request.getAttribute("list");


int Y_value = 0;
int I_value = 0;
int XX = 0;
int I = 0;
int X_value = 0;
int XX_1 = 0;
int p = 0 ;
int time = 0;
int c = 0;

String i = "";
String n = "";
String name = "";
String phone = "";
String medicine = "";

String a = request.getParameter("y");

if( list == null){
	String cycle_Value = request.getParameter("c");
    c = Integer.parseInt(cycle_Value);
    I = Integer.parseInt(request.getParameter("i"));
    phone = request.getParameter("phone");
    medicine = request.getParameter("medicine");
    name = request.getParameter("name");
    n = cycle_Value;
}
else{
	
	if(list.get("cycle") != null) {
    c = Integer.parseInt(list.get("cycle"));
	}else{
		c= Integer.parseInt( request.getParameter("c") ) ;
	}
    if(list.get("time") != null){
       I = Integer.parseInt(list.get("time"));
    }
    name = list.get("UserID");
    medicine = list.get("ITEM_NAME");
    phone = list.get("phone");
    if(request.getAttribute("n") != null){
    	 n = (String)request.getAttribute("n");
     }else{
    n = request.getParameter("n");
     }
    if(list.get("graph_y") != null){
    Y_value = Integer.parseInt(list.get("graph_y"));
    }
}


XX = c;

if (request.getAttribute("y") != null) {
   Y_value = Integer.parseInt(request.getParameter("y"));
   X_value = (int) Math.sqrt(Y_value + Math.pow(XX, 2));
}


if (request.getAttribute("n") != null) {
   XX_1 = Integer.parseInt(request.getParameter("n"));
}
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
window.onload = function() { 



var dataPoints = [];
var y = 0;

var chart = new CanvasJS.Chart("chartContainer", {
   title: {
      text: "실시간 약 복용 그래프"
   },
   axisX:{
      title: "시간"
   },
   axisY:{
      suffix: "%"
   },
   data: [{
      type: "line",
      yValueFormatString: "#,###",
      toolTipContent: "{y}",
      dataPoints: dataPoints
   }]
});



if(<%=I%> > ((<%=XX%>*2)+1+(<%=XX_1%> + <%=X_value%>)-<%=XX_1%>*2) ) {
   alert("약 복용이 완료되었습니다. 재복용 버튼을 눌러주세요!");
   
      $('#y').val(0);
       $('#x').val(0);
       $('#c').val(<%=XX%>);
       $('#n').val(<%=XX%>);
       $("#item_name").val("<%=medicine%>");
       $("#phone").val("<%=phone%>");
       
       
}
 
for (var i=<%=I%>; i <= ((<%=XX%>*2)+1+(<%=XX_1%> + <%=X_value%>)-<%=XX_1%>*2) ; i++) {
	
	
   
   if( i == ((<%=XX%>*2)+1+(<%=XX_1%> + <%=X_value%>)-<%=XX_1%>*2)) {
      break;
   } else {
      
     (function(a) {

         setTimeout(function() {

            var y_1 = -((<%=XX%>-a)**2) ;
            y = y_1 + <%=Y_value%> + <%=XX%>**2 ;
            
            if ((y/((<%=XX%>**2)*2))*100 > 20) {
               document.getElementById('Medicine').innerHTML="<img src='${pageContext.request.contextPath}/images/effect.gif' width='300'/><br>약 효과가 발효중입니다 !"
            }else {
               document.getElementById('Medicine').innerHTML=""
            }
                        
            $('#y').val(y);
            $('#x').val(a);
            $('#c').val(<%=XX%>);
            $('#n').val(<%=XX%>);
            $("#item_name").val("<%=medicine%>");
            $("#phone").val("<%=phone%>");
            $("#name").val("<%=name%>");
            
         p = (y/((<%=XX%>**2)*2))*100 ;
            
           if(p > 100) {
                p = 100
             } 
           
           
           var time = parseInt((a/((<%=XX%>*2)+1+(<%=XX_1%> + <%=X_value%>)-<%=XX_1%>*2))*100);
           var time2 = parseInt((55/100)*((<%=XX%>*2)+1+(<%=XX_1%> + <%=X_value%>)-<%=XX_1%>*2));   
           
               if( time == 51) {
                  alert("문자전송!")
                  location.href='mesg.me?name=<%=name%>&phone=<%=phone%>&medicine=<%=medicine%>&c=<%=c%>&time2='+time2;
              }
           

            dataPoints.push({x: a, y:p });
            
            chart.render();
         
         }, 1000*a);

      })(i)     ;
      
   }

}

}
</script>
</head>
<body>

<div id="wrap" style="-ms-overflow-style: none;">  
<div id="content">
<!-- 본문내용 -->
   
   
  <!-- 그래프  -->
   <div id="chartContainer" style="height: 370px; width: 100%;"></div>
   <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
   
   <div id="whole">
   
 	 <center>
      <form action="Member_Mypage_GraphAction_re.me" method="post">
         <input type="hidden" id="y" name="y"> 
         <input type="hidden" id="c" name="c">
         <input type="hidden" id="n" name="n">
         <input type="hidden" id="item_name" name="item_name">
   	   
   	   <div id="left">
		    <input type="image" src="${pageContext.request.contextPath}/images/retake.png" class="btn" width="130"/>
	   </div>
	   <div id="center">
	   		<div id="Medicine"></div><!-- 약효과 -->
	   </div>
	   <div id="right">
			<button type="button" onclick="location.href='./pill.me'" class="btn1"><img src="${pageContext.request.contextPath}/images/showlist.png" width="130"/></button> 
	   </div><!-- right -->
	  </form>
	</center>
   </div><!-- whole -->
<!-- 본문내용 -->
</div>   
</div>
</body>
</html>