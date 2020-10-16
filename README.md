# Medicine-Service - 약 복용 알리미
<br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95682260-4bca4a00-0c1f-11eb-800a-84f91a25a2f6.png" width="600px"> </p>
<br><br><br>

**약 먹는 시간을 자주 깜빡하거나, 복용하고 있는 약의 정보 및 효과를 보고 싶은 사람들을 위한 서비스**

## 목차

  1. [프로젝트 목표](#프로젝트-목표)
  1. [프로젝트 핵심기능](#프로젝트-핵심기능)
  1. [개발환경](#개발환경)
  1. [기능구현](#기능구현)
  1. [Destructuring](#destructuring)
  1. [Strings](#strings)
  1. [Functions](#functions)
  1. [Arrow Functions](#arrow-functions)
  1. [Classes & Constructors](#classes--constructors)

## 프로젝트 목표

  <a name="프로젝트-목표--아날로그-방식-개선목적"></a><a name="1.1"></a>
  - [1.1](#프로젝트-목표--아날로그-방식-개선목적) **아날로그 방식 개선 목적 :** 식사시간을 기준으로 정하는 아날로그 방식에서 `복용알림 서비스로의 개선 목적.`
  
  <a name="프로젝트-목표--복용효과-100%받기"></a><a name="1.2"></a>
  - [1.2](#프로젝트-목표--복용효과-100%받기) **복용효과 100%받기 :** 약 동력의 기반하여 `최적의 재 복용 시간`을 알려 줌.
  
  <a name="프로젝트-목표--체내-약효능-시기-인지하기"></a><a name="1.3"></a>
  - [1.3](#프로젝트-목표--체내-약효능-시기-인지하기) **체내 약효능 시기 인지하기 :** 체내에 약의 효과가 작용하고 있는 것을 `시각적 그래프`로 알려 줌.
  
  
  
## 프로젝트 핵심기능

  <a name="프로젝트-핵심기능--약-정보-검색"></a><a name="2.1"></a>
  - [2.1](#프로젝트-핵심기능--약-정보-검색) **약 정보 검색** : 복용 중인 약의 기본 정보, 효능, 용법, 주의사항, 부작용 안내
  
  <a name="프로젝트-핵심기능--약-복용-그래프"></a><a name="2.2"></a>
  - [2.2](#프로젝트-핵심기능--약-복용-그래프) **약 복용 그래프** : 복용 주기에 맞춘 약 효능 그래프
  
  <a name="프로젝트-핵심기능--문자-발송"></a><a name="2.3"></a>
  - [2.3](#프로젝트-소개--문자-발송) **문자 발송** : 약 복용 주기를 등록하여 복용 시간에 맞춰 문자 메세지 전송
  
  <a name="프로젝트-핵심기능--약국-지도-API"></a><a name="2.4"></a>
  - [2.4](#프로젝트-소개--약국-지도-API) **약국 지도 API** : 전국 약국위치를 파악 할 수 있는 지도
  
  
## 개발환경

* 언어
  * JAVA SE1.8 `JDK 8`
  * HTML5/CSS3
  * JavaScript
  <br>
* 프레임 워크
  * Jquery
  * node.js `v12.0`
  <br>  
* 서버(WAS)
  * Apache Tomcat `v8.0`
  <br>
* 개발도구
  * Eclipse-JEE-Mars-2
  * MySQL WorkBench 
  <br>
* 커뮤니티
  * Github
  
## DATABASE

<br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95739084-39a2e700-0cc5-11eb-9577-6fb907f23a17.png" width="600px"> </p>
<br><br>

## 기능구현 
* [검색 기능](#검색-기능)
* [복용 그래프](#복용-그래프)
* [약국 찾기 지도](#약국-찾기-지도)
* [실시간 채팅 상담](#실시간-채팅-상담)
<br><br>


### 검색 기능
<br>

  <a name="기능구현--검색-기능--1."></a><a name="4.1"></a>
  - [4.1](#기능구현--검색-기능--1.) 공공데이터 에서 약 `53000개`의 약 정보를 파싱하여 DB에 저장함.
  
  <br>
  
  ```java
  private static String getTagValue(String tag, Element eElement) {
       NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
       Node nValue = (Node) nlList.item(0);
       if(nValue == null) 
           return null;
       return nValue.getNodeValue();
   }

   public static void main(String[] args) {
      
      int page2 = 1;   // 페이지 초기값
      
      
      
      try{
         while(true){
            // parsing할 url 지정(API 키 포함해서)
            String url = "http://apis.data.go.kr/1470000/MdcinSdefctInfoService/getMdcinSdefctInfoList?serviceKey=mFW3I9zgl4vL6raWIh5QNyRZxIuxHHzVVutLRKteGhUZQnbd%2BYUocob3AsbcP6imSjhZ9FO8TwmdXZFLgVtxpg%3D%3D&numOfRows=1&pageNo="+page2;
            
            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            
            // root tag 
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            
            // 파싱할 tag
            NodeList nList = doc.getElementsByTagName("item");
            //System.out.println("파싱할 리스트 수 : "+ nList.getLength());
            
            for(int temp = 0; temp < nList.getLength(); temp++){
               Node nNode = nList.item(temp);
               if(nNode.getNodeType() == Node.ELEMENT_NODE){
                  
                  Element eElement = (Element) nNode;
                  System.out.println("######################");
                  //System.out.println(eElement.getTextContent())
                  String a = getTagValue("COL_001", eElement);
                  String b = getTagValue("COL_002", eElement);
                  String c = getTagValue("COL_005", eElement);        
                  String d = getTagValue("COL_006", eElement);
  
  ```
  

  
  <a name="기능구현--검색-기능--2"></a><a name="4.2"></a>
  - [4.2](#기능구현--검색-기능--2)  공공데이터 에서 약 `100개`의 약 부작용 정보를 파싱하여 DB에 저장함.
  
  <br>
  
  ```java
  
    // 파싱 할 URL 주소
		String DocumentURL = "http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem?serviceKey=mFW3I9zgl4vL6raWIh5QNyRZxIuxHHzVVutLRKteGhUZQnbd%2BYUocob3AsbcP6imSjhZ9FO8TwmdXZFLgVtxpg%3D%3D&";

		// xpath 생성
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		NodeList cols_2; // 전체 SECTION 값 받을 변수
		NodeList cols_3; // 전체 PARAGRAPH 값 받을 변수
		NodeList cols_4; // 전체 ARTICLE 값 받을 변수
		NodeList cols_5; // 전체 ITEM_NAME 값 받을 변수
		String TitleName2 = ""; // 특정 ARTICLE 내의 title 태그 안 내용을 받는 변수
		String TitleName = ""; // 특정 SECTION 내의 title 태그 안 내용을 받는 변수
		
		String result = ""; // DB에 넣을 최종 결과 문자열을 받는 변수

		
		for (int page = 1; page < 20; page++) {
			
			System.out.println(page);
		
				String URL2 = DocumentURL + "pageNo=" + page + "&numOfRows=1"; 
				  
			  
			Document document_2 = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(URL2);

			cols_2 = (NodeList) xpath.evaluate("//EE_DOC_DATA/DOC/SECTION", document_2, XPathConstants.NODESET);
			cols_5 = (NodeList) xpath.evaluate("//ITEM_NAME", document_2, XPathConstants.NODESET);
			String Item_name = cols_5.item(0).getTextContent();
				

				for (int i = 0; i < cols_2.getLength(); i++) {
					TitleName = cols_2.item(i).getAttributes().getNamedItem("title").getTextContent();

					System.out.println(TitleName);
					result += TitleName +"\n";
					System.out.println();
					
					cols_4 = (NodeList) xpath.evaluate("//EE_DOC_DATA/DOC/SECTION[@title='" + TitleName + "']/ARTICLE", document_2, XPathConstants.NODESET);

					

					for (int j = 0; j < cols_4.getLength(); j++) {
					TitleName2 = cols_4.item(j).getAttributes().getNamedItem("title").getTextContent();

					System.out.println(TitleName2);
					result += TitleName2 + "\n";

					System.out.println();

					cols_3 = (NodeList) xpath.evaluate(
							"//EE_DOC_DATA/DOC/SECTION/ARTICLE[@title='" + TitleName2 + "']/PARAGRAPH", document_2,
							XPathConstants.NODESET);

					for (int idx1 = 0; idx1 < cols_3.getLength(); idx1++) {

						System.out.println(cols_3.item(idx1).getTextContent());
						result += cols_3.item(idx1).getTextContent() + "\n\n";
					}
				}

  ```
        
  <a name="기능구현--검색-기능--3"></a><a name="4.3"></a>
  - [4.3](#기능구현--검색-기능--3) Jquery의 AutoComplete 기능을 이용하여 검색 시 `자동완성` 기능을 추가함.
  
  <br>
  
  ```java
  
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
  
 
  
  ```

<hr>





### 복용 그래프
<br>


 <a name="기능구현--복용-그래프--1."></a><a name="4.4"></a>
  - [4.4](#기능구현--복용-그래프--1.) 임의로 만든 약 동력 그래프를 시간에 따라 그리게 되고, 복용주기가 끝나면 그래프는 멈추도록 설계함.
  
  
  <a name="기능구현--복용-그래프--2."></a><a name="4.5"></a>
  - [4.5](#기능구현--복용-그래프--2.) 약의 효과가 발현하는 40% 지점부터 (임의로 지정) 약 효과가 발현하고 있다는 신호를 주기위해 `gif 이미지를 띄워 표시함.`
  
  
  ```javascript
  
    // 약 복용 효과가 시작되는 %를 지정 할 수 있음 (해당 예시는 40%)
            if ((y/((<%=XX%>**2)*2))*100 > 40) {
               document.getElementById('Medicine').innerHTML="<img src='${pageContext.request.contextPath}/images/effect.gif' width='300'/><br>약 효과가 발효중입니다 !"
            }else {
               document.getElementById('Medicine').innerHTML=""
            }
            
  ```
  
  <a name="기능구현--복용-그래프--3."></a><a name="4.6"></a>
  - [4.6](#기능구현--복용-그래프--3.) 입력한 재복용 주기만큼 시간이 지나면 사용자의 휴대전화로 `재복용 알람 문자 메세지를 전송함.`
  
  
  ```javascript
  
     
           // 현재 진행상황(x)에서 가지는 y(%)값을 저장하는 변수
           var time = parseInt((a/((<%=XX%>*2)+1+(<%=XX_1%> + <%=X_value%>)-<%=XX_1%>*2))*100);
           
           // y(%)가 55%일 때 해당하는 x의 값
           var time2 = parseInt((55/100)*((<%=XX%>*2)+1+(<%=XX_1%> + <%=X_value%>)-<%=XX_1%>*2));   
           
           	   // y가 51% 일 떄 문자전송하는 jsp로 이동!
               if( time == 51) {
                  alert("문자전송!")
                  location.href='mesg.me?name=<%=name%>&phone=<%=phone%>&medicine=<%=medicine%>&c=<%=c%>&time2='+time2;
              }
           
           
  ```
  
  <br>
  
  **문자보내기 jsp파일 코드**
  
  ```java
  
    // coolsms api 사용
    String api_key = "NCS2AVI8KJN9VEWC";
    String api_secret = "PA3D1KQCGXAI2NZTQNMLIAIIMW4INQUC";
    Message coolsms = new Message(api_key, api_secret);

    // 4 params(to, from, type, text) are mandatory. must be filled
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", phone);
    params.put("from", "01071675907"); //무조건 자기번호 (인증)
    params.put("type", "SMS");
    params.put("text", name+"님! "+medicine+"의 약 효능이 떨어지고 있습니다. 재복용 해주세요!");
    params.put("app_version", "test app 1.2"); // application name and version

    try {
    	//send() 는 메시지를 보내는 함수  
      JSONObject obj = (JSONObject) coolsms.send(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    } 
%>

  ```
  
  
  <br>
  
 **전체 그래프 jsp파일 코드**
 
 ```java
 <%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<!DOCTYPE HTML>
<html>
<head>
<title>YAKBBAL</title>
<link href="css/graph.css" rel="stylesheet" type="text/css">

<%
HashMap<String, String> list = (HashMap)request.getAttribute("list"); //그래프에 필요한 변수를 담는 List


int Y_value = 0; // 재복용시 남아있는 Y값을 담는 변수
int XX = 0; // 그래프 x값
int XX_1 = 0; // XX와 같은 값, 계산식을 위한 변수
int I = 0; // 화면을 껏다가 다시 켰을 때, 복용시간 - 현재시간을 뺀 값을 담는 변수
int X_value = 0; // 그래프 만들기 위한 변수
int p = 0 ; // Y값을 퍼센트로 나타낸 변수
int time = 0; // 약 복용 시작시간
int c = 0; // 약 복용 주기

String i = ""; // 약 복용 시작시간
String n = ""; // XX_1 값
String name = ""; // 회원의 아이디
String phone = ""; // 회원의 휴대전화 번호
String medicine = ""; // 복용한 약의 이름
/* String a = request.getParameter("y"); */

//리스트 이외로 받을 때
if( list == null){
	String cycle_Value = request.getParameter("c"); // 약 복용 주기
	n = cycle_Value; // XX_1 값
	c = Integer.parseInt(cycle_Value); // 약 복용 주기
    I = Integer.parseInt(request.getParameter("i")); // 약 시작시간
    phone = request.getParameter("phone"); // 회원 번호
    medicine = request.getParameter("medicine"); // 회원 복용 약
    name = request.getParameter("name"); // 회원 아이디
}

//리스트로 받을 때
else{
	
	// 약 복용 주기
	if(list.get("cycle") != null) {
    c = Integer.parseInt(list.get("cycle")); 
	}else{
		c= Integer.parseInt( request.getParameter("c") ) ;
	}
	
	// 약 시작 시간
    if(list.get("time") != null){
       I = Integer.parseInt(list.get("time"));
    }
	
    name = list.get("UserID"); // 회원 아이디
    medicine = list.get("ITEM_NAME"); // 회원 복용 양
    phone = list.get("phone"); // 회원 번호
    
    // XX_1 값
    if(request.getAttribute("n") != null){
    	 n = (String)request.getAttribute("n");
     }else{
    n = request.getParameter("n");
     }
    
    // 재복용시 남아있는 Y값
    if(list.get("graph_y") != null){
    Y_value = Integer.parseInt(list.get("graph_y")); // 재복용시 남아있는 Y값을 담는 변수
    }
}


// String 값  int로 변환하기

XX = c; // 복용주기

if (request.getAttribute("y") != null) {
   Y_value = Integer.parseInt(request.getParameter("y")); // 남아있는 y의 값 int로 변환 
   X_value = (int) Math.sqrt(Y_value + Math.pow(XX, 2)); // XX 값 int 변환
}


if (request.getAttribute("n") != null) {
   XX_1 = Integer.parseInt(request.getParameter("n")); // XX_1 값 int 변환
}
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
window.onload = function() { 



var dataPoints = [];  // 그래프를 이루는 점의 좌표
var y = 0; // 그래프 시작 값

var chart = new CanvasJS.Chart("chartContainer", {
   title: {
      text: "실시간 약 복용 그래프" // 그래프 이름
   },
   axisX:{
      title: "시간" // 그래프 x축 이름
   },
   axisY:{
      suffix: "%" // 그래프 y축 이름
   },
   data: [{ // 기타옵션
      type: "line", 
      yValueFormatString: "#,###",
      toolTipContent: "{y}",
      dataPoints: dataPoints
   }]
});


// 현재시간 - 복용시간의 값이 약 전체 복용의 시간보다 초과 했을 시, alert 창 띄우기 !
if(<%=I%> > ((<%=XX%>*2)+1+(<%=XX_1%> + <%=X_value%>)-<%=XX_1%>*2) ) {
   alert("약 복용이 완료되었습니다. 재복용 버튼을 눌러주세요!");
   		
       // 다시 재복용 했을 떄 필요한 변수들
       $('#y').val(0);
       $('#x').val(0);
       $('#c').val(<%=XX%>);
       $('#n').val(<%=XX%>);
       $("#item_name").val("<%=medicine%>");
       $("#phone").val("<%=phone%>");
       
       
}
 
 // 시작시간 I 부터 총 복용시간까지 반복문을 실행하며 그래프 동작
for (var i=<%=I%>; i <= ((<%=XX%>*2)+1+(<%=XX_1%> + <%=X_value%>)-<%=XX_1%>*2) ; i++) {
	
	
   // 복용 시간까지 시간이 진행되면 그래프가 멈추도록 제어
   if( i == ((<%=XX%>*2)+1+(<%=XX_1%> + <%=X_value%>)-<%=XX_1%>*2)) {
      break;
   } else {
      
     (function(a) {
		 // Sleep을 걸어주는 메서드
         setTimeout(function() {

        	// 약 동학과 유사한 그래프를 만드는 함수식
            var y_1 = -((<%=XX%>-a)**2) ; 
            y = y_1 + <%=Y_value%> + <%=XX%>**2 ;
            
            // 약 복용 효과가 시작되는 %를 지정 할 수 있음 (해당 예시는 40%)
            if ((y/((<%=XX%>**2)*2))*100 > 40) {
               document.getElementById('Medicine').innerHTML="<img src='${pageContext.request.contextPath}/images/effect.gif' width='300'/><br>약 효과가 발효중입니다 !"
            }else {
               document.getElementById('Medicine').innerHTML=""
            }
            
            // 문자 알림을 보내고 돌아올 때, 재복용 실행 시 필요한 파라미터들 저장
            $('#y').val(y);
            $('#x').val(a);
            $('#c').val(<%=XX%>);
            $('#n').val(<%=XX%>);
            $("#item_name").val("<%=medicine%>");
            $("#phone").val("<%=phone%>");
            $("#name").val("<%=name%>");
            
         // y값을 %로 바꿔줌 (현재 y값/전체 y값)
         p = (y/((<%=XX%>**2)*2))*100 ; 
            
           // % 이므로 100%가 되면 멈추도록 제어
           if(p > 100) {
                p = 100
             } 
           
           // 현재 진행상황(x)에서 가지는 y(%)값을 저장하는 변수
           var time = parseInt((a/((<%=XX%>*2)+1+(<%=XX_1%> + <%=X_value%>)-<%=XX_1%>*2))*100);
           
           // y(%)가 55%일 때 해당하는 x의 값
           var time2 = parseInt((55/100)*((<%=XX%>*2)+1+(<%=XX_1%> + <%=X_value%>)-<%=XX_1%>*2));   
           
           	   // y가 51% 일 떄 문자전송하는 jsp로 이동!
               if( time == 51) {
                  alert("문자전송!")
                  location.href='mesg.me?name=<%=name%>&phone=<%=phone%>&medicine=<%=medicine%>&c=<%=c%>&time2='+time2;
              }
           

            dataPoints.push({x: a, y:p }); // 그래프를 이루는 좌표 대입
            
            chart.render(); // 차트 생성
         
         }, 1000*a); // 1초의 sleep 을 가짐

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
 	  <!-- 변수를 담아두는 form 태그  -->
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
 ```
 
 
<hr><hr><hr>


<br>

### 약국 찾기 지도
<br>

* **공공데이터에서 약 `22000개`의 전국 약국정보를 파싱하여 DB에 저장함.** <br><br>
* **해당 약국의 위치를 표시하기 위해서` Marker` 기능을 사용하였고, 해당 마커의 정보를 표기하기 위해 `InfoWindow` 기능을 사용함.** <br><br>
* **Marker가 한 곳에 밀집되어 있을 때, 밀집된 Marker의 수를 표시하기 위해서 `Clusterer` 기능을 구현함.** <br><br>
* **광역시 시청을 기준으로 버튼 클릭 시 , `해당 좌표로 바로 이동`할 수 있도록 구현함.**

<br><br><br><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95738785-c00af900-0cc4-11eb-9c86-5a2b74e67851.png" width="600px"> </p>
<br><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95738771-ba151800-0cc4-11eb-8096-8fca86454a9c.png" width="600px"> </p>
<br><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734753-d57d2480-0cbe-11eb-8e79-d81428cd699b.png" width="600px"> </p>
<br><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95738780-be413580-0cc4-11eb-9d40-2c987a2aa8cd.png" width="600px"> </p>
<br><br>
<hr><br>

### 실시간 채팅
<br>

* **Admin으로 접속 했을 때에는 관리자 채팅 표시가 나타나게 하고, 일반 유저일 경우 실시간 상담 로고만 나타나도록 설정함.** <br><br>
* **관리자 채팅으로 접속 시, 일반 유저들이 작성 한 채팅창이 뜨게 작성하였고 `실시간 채팅`이 가능하도록 구현함.** <br><br>
* **일반 유저가 실시간 상담을 종료하면 관리자 채팅에서의 `채팅방도 사라지게 구현함.`**

<br><br><br><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734757-d615bb00-0cbe-11eb-85e5-083338798c82.png" width="600px"> </p>
<br><br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95734763-d7df7e80-0cbe-11eb-858b-cd1bae9a5081.png" width="600px"> </p>
<br><br>

