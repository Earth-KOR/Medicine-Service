package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParsing_medicinformation {

    // tag값의 정보를 가져오는 메소드
   private static String getTagValue(String tag, Element eElement) {
       NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
       Node nValue = (Node) nlList.item(0);
       if(nValue == null) 
           return null;
       return nValue.getNodeValue();
   }

   public static void main(String[] args) {
      
      int page2 = 1;   // 페이지 초기값
      
      String[] abc = null;
      List<String> list2 = new ArrayList<String>();
      List<String> list = new ArrayList<String>();
      String listB = "";
      
      try{
         while(true){
            // parsing할 url 지정(API 키 포함해서)
            String url = "http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem?serviceKey=mFW3I9zgl4vL6raWIh5QNyRZxIuxHHzVVutLRKteGhUZQnbd%2BYUocob3AsbcP6imSjhZ9FO8TwmdXZFLgVtxpg%3D%3D&pageNo="+page2;
            
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
                  String c = getTagValue("ENTP_NAME", eElement);
                  String b = getTagValue("ITEM_NAME", eElement);   
                  String a = getTagValue("ITEM_SEQ", eElement);
                  String d = getTagValue("MATERIAL_NAME", eElement);
                  String e = getTagValue("VALID_TERM", eElement);
                  
                  if(d == null) {
                     d = "없음";
                    
                  }else {
                     list2.clear();
                     String[] arr = d.split("성분명 : ");
                     for(int i=1 ; i < arr.length ; i++) {
                     abc = arr[i].split("\\|");
                     list2.add(abc[0]);
                     System.out.println("성분 명 : " + abc[0]);
                     }
                     System.out.println(list2);
                  }

                  list.clear();
                  list.add(a);
                  list.add(b);
                  list.add(c);
               
                  list.add(e);
                
                  if(d == "없음") {
                     listB="없음";
                    
                  }
                     listB="";
                    for (int i=0 ; i<list2.size() ; i++) {
                       String listA = list2.get(i);
                       
                       listB += listA ;
                       listB += ",";
                       
                    } 
                
                    
					
					  Connection con = null; PreparedStatement pstmt = null;
					  
					  String driver = "com.mysql.jdbc.Driver"; String url1 =
					  "jdbc:mysql://localhost:3306/medicine"; String user = "root"; String password =
					  "1234";
					  
					  try {
						  
					Class.forName(driver);
					con = DriverManager.getConnection(url1, user,password);
					  
					  String sql = "INSERT INTO Medicine_Info (ENTP_NAME,ITEM_NAME,ITEM_SEQ,MATERIAL_NAME,VALID_TERM) VALUES (?,?,?,?,?)";
					  pstmt = con.prepareStatement(sql);
					  pstmt.setString(1, list.get(0));
					  pstmt.setString(2, list.get(1));
					  pstmt.setString(3, list.get(2));
					  pstmt.setString(4, listB);
					  pstmt.setString(5, list.get(3));
					  
					  pstmt.executeUpdate(); // INSERT 구문 실행 결과를 int형 변수에 저장
	                    
					  } catch (ClassNotFoundException exception) {
	                        exception.printStackTrace();
	                     } catch (SQLException exception) {
	                        exception.printStackTrace();
	                     } finally {
	                        // 자원 반환 => 예외 발생 여부와 관계없이 실행되어야 하므로
	                        // finally 블록 내에서 close() 메서드를 호출한다!
	                        try {
	                           pstmt.close();
	                           con.close();
	                        } catch (SQLException exception2) {
	                           exception2.printStackTrace();
	                        }
	                        
	                      
	                     }

	                    
	               }   // for end
	             
	            }   // if end

            
            page2 += 1;
            System.out.println(listB);
            System.out.println("page number : "+page2);
            if(page2 > 20){
               break;
            }
         }   // while end
         
      } catch (Exception e){   
         e.printStackTrace();
      }   // try~catch end
   }   // main end
}   // class end
