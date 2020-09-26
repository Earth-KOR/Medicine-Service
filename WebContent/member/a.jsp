<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.HashMap"%>
<%@ page import = "org.json.simple.JSONObject"%>
<%@ page import = "net.nurigo.java_sdk.api.Message"%>
<%@ page import = "net.nurigo.java_sdk.exceptions.CoolsmsException"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%                                    
String name = "";
String phone = "";
String medicine = "";
String time2 = "";


name = request.getParameter("name");
phone = request.getParameter("phone");
medicine = request.getParameter("medicine");
time2 = request.getParameter("time2");
String c = request.getParameter("c");

    System.out.println(name);
    System.out.println(phone);
    System.out.println(medicine);

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
<script>

location.href = "graph.me?i=<%=time2%>&c=<%=c%>";
</script>


</head>
<body>
</body>
</html>