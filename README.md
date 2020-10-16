# Medicine-Service - 약 복용 알리미
<br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95682260-4bca4a00-0c1f-11eb-800a-84f91a25a2f6.png" width="600px"> </p>
<br><br><br>

**"약 먹는 시간을 자주 깜빡하거나, 복용하고 있는 약의 정보 및 효과를 보고 싶은 사람들을 위한 서비스"**

<br><br>
## 목차

  1. [프로젝트 목표](#프로젝트-목표)
  1. [프로젝트 핵심기능](#프로젝트-핵심기능)
  1. [개발환경](#개발환경)
  1. [기능구현](#기능구현)

  
<br><br>

## 프로젝트 목표

  <a name="프로젝트-목표--아날로그-방식-개선목적"></a><a name="1.1"></a>
  - [1.1](#프로젝트-목표--아날로그-방식-개선목적) **아날로그 방식 개선 목적 :** 식사시간을 기준으로 정하는 아날로그 방식에서 `복용알림 서비스로의 개선 목적.`
  
  <a name="프로젝트-목표--복용효과-100%받기"></a><a name="1.2"></a>
  - [1.2](#프로젝트-목표--복용효과-100%받기) **복용효과 100%받기 :** 약 동력의 기반하여 `최적의 재 복용 시간`을 알려 줌.
  
  <a name="프로젝트-목표--체내-약효능-시기-인지하기"></a><a name="1.3"></a>
  - [1.3](#프로젝트-목표--체내-약효능-시기-인지하기) **체내 약효능 시기 인지하기 :** 체내에 약의 효과가 작용하고 있는 것을 `시각적 그래프`로 알려 줌.
  
 <br><br> 
  
## 프로젝트 핵심기능

  <a name="프로젝트-핵심기능--약-정보-검색"></a><a name="2.1"></a>
  - [2.1](#프로젝트-핵심기능--약-정보-검색) **약 정보 검색** : 복용 중인 약의 기본 정보, 효능, 용법, 주의사항, 부작용 안내
  
  <a name="프로젝트-핵심기능--약-복용-그래프"></a><a name="2.2"></a>
  - [2.2](#프로젝트-핵심기능--약-복용-그래프) **약 복용 그래프** : 복용 주기에 맞춘 약 효능 그래프
  
  <a name="프로젝트-핵심기능--문자-발송"></a><a name="2.3"></a>
  - [2.3](#프로젝트-소개--문자-발송) **문자 발송** : 약 복용 주기를 등록하여 복용 시간에 맞춰 문자 메세지 전송
  
  <a name="프로젝트-핵심기능--약국-지도-API"></a><a name="2.4"></a>
  - [2.4](#프로젝트-소개--약국-지도-API) **약국 지도 API** : 전국 약국위치를 파악 할 수 있는 지도
  
  <br><br>
  
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
  
  <br><br>
  
## DATABASE

<br>
<p align="center"> <img src="https://user-images.githubusercontent.com/62025746/95739084-39a2e700-0cc5-11eb-9577-6fb907f23a17.png" width="600px"> </p>
<br><br>

## 기능구현 
* [검색 기능](#검색-기능)
* [복용 그래프](#복용-그래프)
* [전국 약국 지도](#전국-약국-지도)
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
  
<br>
  
  <br> 
  
  <p align="center"> <img src="https://user-images.githubusercontent.com/62025746/96236326-dff93000-0fd6-11eb-8dae-3d63a0ae12c4.png" width="600px"> </p>
  
  <br>
  
  <a name="기능구현--검색-기능--2"></a><a name="4.2"></a>
  - [4.2](#기능구현--검색-기능--2)  공공데이터 에서  `48개`의 약 부작용 정보를 파싱하여 DB에 저장함.
  
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
        
  <br> 
  
  <p align="center"> <img src="https://user-images.githubusercontent.com/62025746/96236465-09b25700-0fd7-11eb-9d03-f46f0f31beed.png" width="600px"> </p>
  
  <br>
	
	
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
  
  
   <br> 
  
  <p align="center"> <img src="https://user-images.githubusercontent.com/62025746/96236828-7decfa80-0fd7-11eb-8f03-fcb553015e1c.png" width="600px"> </p>
  
  <br>
  
  

<hr>
<br>




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
  
  
  <br>
  
  <p align="center"> <img src="https://user-images.githubusercontent.com/62025746/96237136-e3d98200-0fd7-11eb-8318-57ceafa54f92.gif" width="600px"> </p>
  
  <br>
  
  
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
  
  <p align="center"> <img src="https://user-images.githubusercontent.com/62025746/96237280-15524d80-0fd8-11eb-9083-1b3b520caacf.png" width="600px"> </p>
  
  <br>
  
  
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
 
 
<hr>

<br>

### 전국 약국 지도
<br>

 <a name="기능구현--전국-약국-지도--1."></a><a name="4.7"></a>
  - [4.7](#기능구현--검색-기능--1.) 공공데이터에서 약 `22000개`의 전국 약국정보를 파싱하고 `XML`에서 `JSON`으로 바꾼 뒤, ndate.js파일에 넣음.
  
  <br><br>
  
  
 **공공데이터에서 약국정보 파싱코드**
 
 <br>
 
  ```
  import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.XML;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

public class sample {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/MdcinSdefctInfoService/getMdcinSdefctInfoList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=tXyrwVeSLRn84CXxp6YRwCyYN1W3r%2FZWSOxncCCvENtvDAsBb%2Fi5amus91w%2Bp%2FYesxx9xraXWHJTIW5HN4jrzg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("col_001","UTF-8") + "=" + URLEncoder.encode("이소니아지드", "UTF-8")); /*품명*/
        urlBuilder.append("&" + URLEncoder.encode("col_002","UTF-8") + "=" + URLEncoder.encode("Isoniazide", "UTF-8")); /*품명(영문)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*한 페이지 결과 수*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
//        System.out.println(sb.toString());
        
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        String jsonPrettyPrintString = xmlJSONObj.toString();
        System.out.println(jsonPrettyPrintString);
    }
}
  ```
  <br>
  
  **ndata.js 파일**
  
  <br>
  
```
 var position = [
               
{ full: "대구약국", address: "대구광역시 남구 두류공원로 22", wgs84Lat: "35.84064883956591", wgs84Lon: "128.5747952162564"},
{ full: "모자약국", address: "대구광역시 남구 대명로 228  (대명동)", wgs84Lat: "35.84077061206386", wgs84Lon: "128.58199581580055"},
{ full: "지혜약국", address: "대구광역시 남구 두류공원로 26", wgs84Lat: "35.84089393453877", wgs84Lon: "128.5745211595811"},
{ full: "우리들약국", address: "대구광역시 남구 대명로 216, (대명동)", wgs84Lat: "35.84039433311613", wgs84Lon: "128.58092453441785"},
{ full: "새건강약국", address: "대구광역시 남구 성당로 200-1", wgs84Lat: "35.850788091415936", wgs84Lon: "128.5734503251811"},
{ full: "은행약국", address: "대구광역시 남구 성당로 206, (대명동)", wgs84Lat: "35.850995399601146", wgs84Lon: "128.5736524643989"},
{ full: "봄약국", address: "대구광역시 남구 중앙대로 151, 1층 (대명동)", wgs84Lat: "35.84684341133469", wgs84Lon: "128.5901771836704"},
{ full: "메디팜효성약국", address: "대구광역시 남구 효성중앙길 37", wgs84Lat: "35.83797808247134", wgs84Lon: "128.60179890113184"},
{ full: "신종로약국", address: "서울특별시 동대문구 왕산로 261, 1층 (청량리동)", wgs84Lat: "37.58428577891409", wgs84Lon: "127.05011550443913"},
{ full: "하나온누리약국", address: "인천광역시 서구 가정로 309, 1층 (석남동)", wgs84Lat: "37.51205763023198", wgs84Lon: "126.6723588271092"},
{ full: "함께약국", address: "인천광역시 서구 서곶로 823, 1층 (당하동)", wgs84Lat: "37.59069090638597", wgs84Lon: "126.67296550460665"},
{ full: "사랑온누리약국", address: "인천광역시 서구 검단로 500 ", wgs84Lat: "37.60262204214791", wgs84Lon: "126.65967276013117"},
{ full: "성호약국", address: "인천광역시 서구 원적로 85, 310호 (가좌동, 우신프라자)", wgs84Lat: "37.494568096733836", wgs84Lon: "126.6815156473692"},
{ full: "사랑약국", address: "전라남도 목포시 고하대로 795-1, (연산동)", wgs84Lat: "34.81707148544758", wgs84Lon: "126.37970517557826"},
{ full: "하나로약국", address: "전라남도 목포시 원산중앙로 46, (산정동)", wgs84Lat: "34.80910174948349", wgs84Lon: "126.37704418191879"},
{ full: "동아약국", address: "부산광역시 서구 대신공원로 31, (서대신동3가)", wgs84Lat: "35.120705555555553", wgs84Lon: "129.016730555555569"},
{ full: "부부약국", address: "충청북도 진천군 광혜원면 중리1길 17", wgs84Lat: "36.989811806594446", wgs84Lon: "127.4420341586943"},
{ full: "약국산책", address: "광주광역시 광산구 풍영철길로 20-6 (우산동)", wgs84Lat: "35.1628010136", wgs84Lon: "126.8128531181"},
{ full: "굿모닝약국", address: "경상북도 포항시 북구 흥해읍 중성로 62, 나동 1호 (대성빌라2차)", wgs84Lat: "36.108684050082616", wgs84Lon: "129.34976647255505"},
{ full: "락희약국", address: "부산광역시 사하구 낙동남로 1417, 1층 (하단동)", wgs84Lat: "35.10656557303422", wgs84Lon: "128.96662771685874"},
{ full: "감초당약국", address: "강원도 화천군 화천읍 중앙로7길 6", wgs84Lat: "38.10424315689682", wgs84Lon: "127.70672872949552"},
{ full: "한소망약국", address: "광주광역시 광산구 사암로 275, 1층 (월곡동)", wgs84Lat: "35.16894189756016", wgs84Lon: "126.80877320155433"},
{ full: "참조은약국", address: "광주광역시 광산구 임방울대로 348, 1층 106호 (수완동)", wgs84Lat: "35.19107757908005", wgs84Lon: "126.82438306328999"},
{ full: "마더스약국", address: "충청남도 천안시 서북구 충무로 187 (쌍용동, E 마트)", wgs84Lat: "36.795840492176225", wgs84Lon: "127.12711057726139"},
{ full: "펜타포트약국", address: "충청남도 천안시 서북구 공원로 196 (불당동, 펜타포트)", wgs84Lat: "36.79832753426949", wgs84Lon: "127.10141606543531"},
{ full: "종로약국", address: "서울특별시 종로구 종로 195 (종로4가)", wgs84Lat: "37.57118153913992", wgs84Lon: "126.99928461461036"},
{ full: "현대약국", address: "전라남도 무안군 무안읍 몽탄로 67,", wgs84Lat: "34.991442641233775", wgs84Lon: "126.48906269741404"},
{ full: "윤약국", address: "전라남도 무안군 삼향읍 남악5로48번길 17", wgs84Lat: "34.80990725737075", wgs84Lon: "126.4731818457765"},
{ full: "세명약국", address: "경상남도 양산시 서창로 190, (삼호동)", wgs84Lat: "35.41595808761014", wgs84Lon: "129.17264134727634"},
{ full: "소담약국", address: "경상남도 양산시 덕계로 25, 소담약국 (덕계동)", wgs84Lat: "35.370706485", wgs84Lon: "129.1483416554"},
{ full: "솔약국", address: "경상남도 양산시 서창로 164-1 (삼호동)", wgs84Lat: "35.41436213248085", wgs84Lon: "129.1707932574717"},
{ full: "하나약국", address: "전라남도 무안군 일로읍 일로로 69-1", wgs84Lat: "34.849873025279216", wgs84Lon: "126.49462033065865"},
{ full: "건강약국", address: "대구광역시 달성군 현풍읍 비슬로122길 27-2", wgs84Lat: "35.69358074942984", wgs84Lon: "128.4453365248431"},
{ full: "다온약국", address: "대구광역시 달성군 구지면 과학마을로 42, 106호", wgs84Lat: "35.6555512318", wgs84Lon: "128.4179910751"},
{ full: "마디약국", address: "대구광역시 달성군 현풍읍 현풍중앙로 66", wgs84Lat: "35.695232322", wgs84Lon: "128.4452867887"},
{ full: "메가약국", address: "대구광역시 달성군 유가읍 테크노순환로12길 4, 111,112호", wgs84Lat: "35.6895581743", wgs84Lon: "128.4536903359"},
{ full: "무광온누리약국", address: "대구광역시 달성군 현풍면 현풍로 48", wgs84Lat: "35.69557016469301", wgs84Lon: "128.4447808594943"},
{ full: "바른손약국", address: "대구광역시 달성군 구지면 과학마을로 46", wgs84Lat: "35.65555501020544", wgs84Lon: "128.4182841581492"},
{ full: "비슬약국", address: "대구광역시 달성군 유가면 테크노상업로 98", wgs84Lat: "35.69323314999929", wgs84Lon: "128.46009810363895"},
{ full: "사랑모아약국", address: "대구광역시 달성군 유가면 테크노상업로 108", wgs84Lat: "35.6932457781", wgs84Lon: "128.4611367664"},
{ full: "시장약국", address: "대구광역시 달성군 현풍면 현풍로8길 19", wgs84Lat: "35.694438554751635", wgs84Lon: "128.44295313208977"},

....


```

<br>
  
   <a name="기능구현--전국-약국-지도--2."></a><a name="4.8"></a>
  - [4.8](#기능구현--검색-기능--2.) 해당 약국의 위치를 표시하기 위해서` Marker` 기능을 사용하였고, 해당 마커의 정보를 표기하기 위해 `InfoWindow` 기능을 사용함.

   ```
   for (var i in position) {
  arr = [];
  var mark = [];
  let full;
 
  name = position[i].name
  full = position[i].full;

 
    let latlng = new naver.maps.LatLng(position[i].wgs84Lat, position[i].wgs84Lon);
    arr.push(latlng);

    var content = document.createElement("div");
    content.className = "wrap";

    var info = document.createElement("div");
    info.className = "info";
    content.appendChild(info);

    var title = document.createElement("div");
    title.className = "title";
    title.innerHTML = full ? full : position[i]["title"];
    info.appendChild(title);

    var body = document.createElement("div");
    body.className = "body";
    body.innerHTML =
      position[i]["address"];
    info.appendChild(body);

    marker = new naver.maps.Marker({
      position: latlng,
      map: map,
      icon: {
        content:
          // i == ii - 1 && coco !== leng && !position[i].solo
          // ? "<div style='opacity:0.9;border:1px solid black;border-radius:50%;height:25px;width:25px;background:red;'><span style='position: absolute;left: 0px;top: 1.8px;line-height: 0.9;color:white;font-size:25px;opacity:1;' class='glyphicon glyphicon-plus-sign'></span></div>"
          "<div style='border: 1px solid black;color:white;opacity:0.8;display:flex;justify-content:center;align-items:center; width: 26px;height: 26px;border-radius: 50%;background-color: green" +
          ";'>" +
          "" +
          "</div>",
        anchor:
          // i == ii - 1
          // ? new naver.maps.Point(12, 12)
          new naver.maps.Point(13, 13),
      },
    });

    marker.address_english = "asd";
    marker.address = "asd";
    marker.month = "asd";
    marker.day = "asd";
    marker.full = full ? full : "asd";
    marker.set("seq", i);

    var infowindow = new naver.maps.InfoWindow({
      content: content,
      backgroundColor: "#00ff0000",
      borderColor: "#00ff0000",
      anchorSize: new naver.maps.Size(0, 0),
      anchorColor: "white",
      pixelOffset: new naver.maps.Point(0, -20),
    });

    // 마우스 올릴때

    mark.push(marker);
    markers.push(marker);
    infoWindows.push(infowindow);
    // infos.push(overlay);
    markerList.push(mark);
    icon = null;
    marker = null;
    co = 0;
  
}

   ```
   
  <br> 
  
  <p align="center"> <img src="https://user-images.githubusercontent.com/62025746/96237865-c2c56100-0fd8-11eb-8102-5ff8c326eacb.png" width="600px"> </p>
  
  <br>
  
  
   
  <br>
  
   <a name="기능구현--전국-약국-지도--3."></a><a name="4.9"></a>
  - [4.9](#기능구현--검색-기능--3.) Marker가 한 곳에 밀집되어 있을 때, 밀집된 Marker의 수를 표시하기 위해서 `Clusterer` 기능을 구현함.
  
  ```
  /**
 * Copyright 2016 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 마커 클러스터링을 정의합니다.
 * @param {Object} options 마커 클러스터링 옵션
 */
var MarkerClustering = function(options) {
  // 기본 값입니다.
  this.DEFAULT_OPTIONS = {
    // 클러스터 마커를 올릴 지도입니다.
    map: null,
    // 클러스터 마커를 구성할 마커입니다.
    markers: [],
    // 클러스터 마커 클릭 시 줌 동작 여부입니다.
    disableClickZoom: true,
    // 클러스터를 구성할 최소 마커 수입니다.
    minClusterSize: 2,
    // 클러스터 마커로 표현할 최대 줌 레벨입니다. 해당 줌 레벨보다 높으면, 클러스터를 구성하고 있는 마커를 노출합니다.
    maxZoom: 13,
    // 클러스터를 구성할 그리드 크기입니다. 단위는 픽셀입니다.
    gridSize: 100,
    // 클러스터 마커의 아이콘입니다. NAVER Maps JavaScript API v3에서 제공하는 아이콘, 심볼, HTML 마커 유형을 모두 사용할 수 있습니다.
    icons: [],
    // 클러스터 마커의 아이콘 배열에서 어떤 아이콘을 선택할 것인지 인덱스를 결정합니다.
    indexGenerator: [10, 100, 200, 500, 1000],
    // 클러스터 마커의 위치를 클러스터를 구성하고 있는 마커의 평균 좌표로 할 것인지 여부입니다.
    averageCenter: false,
    // 클러스터 마커를 갱신할 때 호출하는 콜백함수입니다. 이 함수를 통해 클러스터 마커에 개수를 표현하는 등의 엘리먼트를 조작할 수 있습니다.
    stylingFunction: function() {}
  };

  this._clusters = [];

  this._mapRelations = null;
  this._markerRelations = [];

  this.setOptions(
    naver.maps.Util.extend({}, this.DEFAULT_OPTIONS, options),
    true
  );
  this.setMap(options.map || null);
};

naver.maps.Util.ClassExtend(MarkerClustering, naver.maps.OverlayView, {
  onAdd: function() {
    var map = this.getMap();

    this._mapRelations = naver.maps.Event.addListener(
      map,
      "idle",
      naver.maps.Util.bind(this._onIdle, this)
    );

    if (this.getMarkers().length > 0) {
      this._createClusters();
      this._updateClusters();
    }
  },

  draw: naver.maps.Util.noop,

  onRemove: function() {
    naver.maps.Event.removeListener(this._mapRelation);

    this._clearClusters();

    this._geoTree = null;
    this._mapRelation = null;
  },

  /**
   * 마커 클러스터링 옵션을 설정합니다. 설정한 옵션만 반영됩니다.
   * @param {Object | string} newOptions 옵션
   */
  setOptions: function(newOptions) {
    var _this = this;

    if (typeof newOptions === "string") {
      var key = newOptions,
        value = arguments[1];

      _this.set(key, value);
    } else {
      var isFirst = arguments[1];

      naver.maps.Util.forEach(newOptions, function(value, key) {
        if (key !== "map") {
          _this.set(key, value);
        }
      });

      if (newOptions.map && !isFirst) {
        _this.setMap(newOptions.map);
      }
    }
  },

  /**
   * 마커 클러스터링 옵션을 반환합니다. 특정 옵션 이름을 지정하지 않으면, 모든 옵션을 반환합니다.
   * @param {string} key 반환받을 옵션 이름
   * @return {Any} 옵션
   */
  getOptions: function(key) {
    var _this = this,
      options = {};

    if (key !== undefined) {
      return _this.get(key);
    } else {
      naver.maps.Util.forEach(_this.DEFAULT_OPTIONS, function(value, key) {
        options[key] = _this.get(key);
      });

      return options;
    }
  },

  /**
   * 클러스터를 구성하는 최소 마커 수를 반환합니다.
   * @return {number} 클러스터를 구성하는 최소 마커 수
   */
  getMinClusterSize: function() {
    return this.getOptions("minClusterSize");
  },

  /**
   * 클러스터를 구성하는 최소 마커 수를 설정합니다.
   * @param {number} size 클러스터를 구성하는 최소 마커 수
   */
  setMinClusterSize: function(size) {
    this.setOptions("minClusterSize", size);
  },

  /**
   * 클러스터 마커를 노출할 최대 줌 레벨을 반환합니다.
   * @return {number} 클러스터 마커를 노출할 최대 줌 레벨
   */
  getMaxZoom: function() {
    return this.getOptions("maxZoom");
  },

  /**
   * 클러스터 마커를 노출할 최대 줌 레벨을 설정합니다.
   * @param {number} zoom 클러스터 마커를 노출할 최대 줌 레벨
   */
  setMaxZoom: function(zoom) {
    this.setOptions("maxZoom", zoom);
  },

  /**
   * 클러스터를 구성할 그리드 크기를 반환합니다. 단위는 픽셀입니다.
   * @return {number} 클러스터를 구성할 그리드 크기
   */
  getGridSize: function() {
    return this.getOptions("gridSize");
  },

  /**
   * 클러스터를 구성할 그리드 크기를 설정합니다. 단위는 픽셀입니다.
   * @param {number} size 클러스터를 구성할 그리드 크기
   */
  setGridSize: function(size) {
    this.setOptions("gridSize", size);
  },

  /**
   * 클러스터 마커의 아이콘을 결정하는 인덱스 생성기를 반환합니다.
   * @return {Array | Function} 인덱스 생성기
   */
  getIndexGenerator: function() {
    return this.getOptions("indexGenerator");
  },

  /**
   * 클러스터 마커의 아이콘을 결정하는 인덱스 생성기를 설정합니다.
   * @param {Array | Function} indexGenerator 인덱스 생성기
   */
  setIndexGenerator: function(indexGenerator) {
    this.setOptions("indexGenerator", indexGenerator);
  },

  /**
   * 클러스터로 구성할 마커를 반환합니다.
   * @return {Array.<naver.maps.Marker>} 클러스터로 구성할 마커
   */
  getMarkers: function() {
    return this.getOptions("markers");
  },

  /**
   * 클러스터로 구성할 마커를 설정합니다.
   * @param {Array.<naver.maps.Marker>} markers 클러스터로 구성할 마커
   */
  setMarkers: function(markers) {
    this.setOptions("markers", markers);
  },

  /**
   * 클러스터 마커 아이콘을 반환합니다.
   * @return {Array.<naver.maps.Marker~ImageIcon | naver.maps.Marker~SymbolIcon | naver.maps.Marker~HtmlIcon>} 클러스터 마커 아이콘
   */
  getIcons: function() {
    return this.getOptions("icons");
  },

  /**
   * 클러스터 마커 아이콘을 설정합니다.
   * @param {Array.<naver.maps.Marker~ImageIcon | naver.maps.Marker~SymbolIcon | naver.maps.Marker~HtmlIcon>} icons 클러스터 마커 아이콘
   */
  setIcons: function(icons) {
    this.setOptions("icons", icons);
  },

      return index;
    }
  },

```
  <br>


 <br> 
  
  <p align="center"> <img src="https://user-images.githubusercontent.com/62025746/96238006-f0120f00-0fd8-11eb-8506-efc27137ecb9.png" width="600px"> </p>
  
  <br>
  
  
  
   <a name="기능구현--전국-약국-지도--4."></a><a name="4.10"></a>
  - [4.10](#기능구현--검색-기능--4.) 광역시 시청을 기준으로 버튼 클릭 시 , `해당 좌표로 바로 이동`할 수 있도록 구현함.
  
  ```
  map = new naver.maps.Map(document.getElementById("map"), {
  useStyleMap: true,
  zoom: 17,
  center: new naver.maps.LatLng(35.158526, 129.062031),
});

var seoul = new naver.maps.LatLngBounds(
        new naver.maps.LatLng(37.42829747263545, 126.76620435615891),
        new naver.maps.LatLng(37.7010174173061, 127.18379493229875));

busan = new naver.maps.LatLng(35.179783, 129.074987),

daejeon = new naver.maps.LatLng(36.350500, 127.384862),

wonju = new naver.maps.LatLng(37.342071, 127.919624),

gwangju = new naver.maps.LatLng(37.429485, 127.255142),

itwill = new naver.maps.LatLng(35.158526, 129.062031)
  ```
  <br>

 <br> 
  
  <p align="center"> <img src="https://user-images.githubusercontent.com/62025746/96238011-f1433c00-0fd8-11eb-9032-756ba7abc77a.png" width="600px"> </p>
  
  <br>
  
  <hr>

### 실시간 채팅
<br>

 <a name="기능구현--실시간-채팅--1."></a><a name="4.10"></a>
  - [4.10](#기능구현--실시간-채팅--1.) Admin으로 접속 했을 때에는 관리자 채팅 표시가 나타나게 하고, 일반 유저일 경우 실시간 상담 로고만 나타나도록 설정함.
  
   <a name="기능구현--실시간-채팅--2."></a><a name="4.11"></a>
  - [4.11](#기능구현--실시간-채팅--2.) 관리자 채팅으로 접속 시, 일반 유저들이 작성 한 채팅창이 뜨게 작성하였고 `실시간 채팅`이 가능하도록 구현함.
  
  <a name="기능구현--실시간-채팅--3."></a><a name="4.12"></a>
  - [4.12](#기능구현--실시간-채팅--3.) 일반 유저가 실시간 상담을 종료하면 관리자 채팅에서의 `채팅방도 사라지게 구현함.`
  

 <br> 
  
  <p align="center"> <img src="https://user-images.githubusercontent.com/62025746/96238150-22237100-0fd9-11eb-91c7-2b57e63149b8.png" width="600px"> </p>
  
  <br>


**관리자 jsp 페이지 전체 코드**

```
	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<!DOCTYPE html>	
<html>	
<head>	
<title>Web Socket Example</title>	
<style>	
  /* 여러 채팅창 간의 간격과 배열 위치*/	
  .float-left{	
    float:left;	
    margin: 5px;	
  }	
</style>	
</head>	
<body>	
  <!-- 유저가 접속할 때마다 이 템플릿으로 채팅창을 생성한다. -->	
  <div class="template" style="display:none">	
    <form>	
      <!-- 메시지 텍스트 박스 -->	
      <input type="text" class="message" onkeydown="if(event.keyCode === 13) return false;">	
      <!-- 전송 버튼 -->	
      <input value="Send" type="button" class="sendBtn">	
    </form>	
    <br />	
    <!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->	
    <textarea id="messageTextArea" style="width: 320px; resize: none;" 
    rows="10" cols="50" class="console" disabled="disabled"></textarea>	
  </div>	
  <!-- 소스를 간단하게 하기 위하 Jquery를 사용했습니다. -->	
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>	
  <script type="text/javascript">	
    // 서버의 admin의 서블릿으로 웹 소켓을 한다.	
    var webSocket = new WebSocket("ws://localhost:8080/WillProject_0.1/admin");	
    // 운영자에서의 open, close, error는 의미가 없어서 형태만 선언	
    webSocket.onopen = function(message) { };	
    webSocket.onclose = function(message) { };	
    webSocket.onerror = function(message) { };	
    // 서버로 부터 메시지가 오면	
    webSocket.onmessage = function(message) {	
      // 메시지의 구조는 JSON 형태로 만들었다.	
      let node = JSON.parse(message.data);	
      // 메시지의 status는 유저의 접속 형태이다.	
      // visit은 유저가 접속했을 때 알리는 메시지다.	
      if(node.status === "visit") {	
        // 위 템플릿 div를 취득한다.	
        let form = $(".template").html();	
        // div를 감싸고 속성 data-key에 unique키를 넣는다.	
        form = $("<div class='float-left'></div>").attr("data-key",node.key).append(form); 	
        // body에 추가한다.	
        $("body").append(form);	
      // message는 유저가 메시지를 보낼 때 알려주는 메시지이다.	
      } else if(node.status === "message") {	
        // key로 해당 div영역을 찾는다.	
        let $div = $("[data-key='"+node.key+"']");	
        // console영역을 찾는다.	
        let log = $div.find(".console").val();	
        // 아래에 메시지를 추가한다.	(유저 확인후 아이디 출력)
        $div.find(".console").val(log + "(유저) => " +node.message + "\n");	
      // bye는 유저가 접속을 끊었을 때 알려주는 메시지이다.
      } else if(node.status === "bye") {	
        // 해당 키로 div를 찾아서 dom을 제거한다.	
        $("[data-key='"+node.key+"']").remove();	
      }	
    };	
    // 전송 버튼을 클릭하면 발생하는 이벤트	
    $(document).on("click", ".sendBtn", function(){	
      // div 태그를 찾는다.	
      let $div = $(this).closest(".float-left");	
      // 메시지 텍스트 박스를 찾아서 값을 취득한다.	
      let message = $div.find(".message").val();	
      // 유저 key를 취득한다.	
      let key = $div.data("key");	
      // console영역을 찾는다.	
      let log = $div.find(".console").val();	
      // 아래에 메시지를 추가한다.	
      $div.find(".console").val(log + "(나) => " + message + "\n");	
      // 텍스트 박스의 값을 초기화 한다.	
      $div.find(".message").val("");	
      // 웹소켓으로 메시지를 보낸다.	
      webSocket.send(key+"#####"+message);	
      document.getElementById("messageTextArea").scrollTop = document.getElementById("messageTextArea").scrollHeight;
    });	
    // 텍스트 박스에서 엔터키를 누르면	
    $(document).on("keydown", ".message", function(){	
      // keyCode 13은 엔터이다.	
      if(event.keyCode === 13) {	
        // 버튼을 클릭하는 트리거를 발생한다.	
        $(this).closest(".float-left").find(".sendBtn").trigger("click");
        // form에 의해 자동 submit을 막는다.	
        return false;	
      }	
      return true;	
    });	
  </script>	
</body>	
</html>
```

**사용자 채팅 전체 코드**

```
	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<!DOCTYPE html>	
<html>	
<head>	
<title>Web Socket Example</title>	
</head>	
<body>	
  <!-- 채팅 영역 -->	
  <form>	
    <!-- 텍스트 박스에 채팅의 내용을 작성한다. -->	
    <input id="textMessage" type="text" onkeydown="return enter()">	
    <!-- 서버로 메시지를 전송하는 버튼 -->	
    <input onclick="sendMessage()" value="Send" type="button">	
  </form>	
  <br />	
<!--   사이즈 550/500으로 -->
  <!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->	
  <textarea id="messageTextArea" rows="10" cols="50" style="width: 320px; resize: none;" disabled="disabled"></textarea>	
  <script type="text/javascript">	
    // 서버의 broadsocket의 서블릿으로 웹 소켓을 한다.	
    var webSocket = new WebSocket("ws://192.168.6.7:8080/WillProject_0.1/broadsocket");	
    // 콘솔 텍스트 영역	
    var messageTextArea = document.getElementById("messageTextArea");	
    // 접속이 완료되면	
    webSocket.onopen = function(message) {	
      // 콘솔에 메시지를 남긴다.	
      messageTextArea.value += "접속완료\n";	
    };	
    // 접속이 끝기는 경우는 브라우저를 닫는 경우이기 떄문에 이 이벤트는 의미가 없음.	
    webSocket.onclose = function(message) { };	
    // 에러가 발생하면	
    webSocket.onerror = function(message) {	
      // 콘솔에 메시지를 남긴다.	
      messageTextArea.value += "오류...\n";	
    };	
    // 서버로부터 메시지가 도착하면 콘솔 화면에 메시지를 남긴다.	
    webSocket.onmessage = function(message) {	
      messageTextArea.value += "(관리자) => " + message.data + "\n";
      document.getElementById("messageTextArea").scrollTop = document.getElementById("messageTextArea").scrollHeight;
    };	
    // 서버로 메시지를 발송하는 함수	
    // Send 버튼을 누르거나 텍스트 박스에서 엔터를 치면 실행	
    function sendMessage() {	
      // 텍스트 박스의 객체를 가져옴	
      let message = document.getElementById("textMessage");	
      // 콘솔에 메세지를 남긴다.(세션확인후 아이디 출력)
      messageTextArea.value += "(나) => " + message.value + "\n";	
      // 소켓으로 보낸다.	
      webSocket.send(message.value);	
      // 텍스트 박스 추기화	
      message.value = "";
      // 텍스트 박스 아랫줄로 이동
      document.getElementById("messageTextArea").scrollTop = document.getElementById("messageTextArea").scrollHeight;
    }	
    // 텍스트 박스에서 엔터를 누르면	
    function enter() {	
      // keyCode 13은 엔터이다.	
      if(event.keyCode === 13) {	
        // 서버로 메시지 전송	
        sendMessage();	
        // form에 의해 자동 submit을 막는다.	
        return false;	
      }	
      return true;	
    }	
  </script>	
</body>	
</html>
```

