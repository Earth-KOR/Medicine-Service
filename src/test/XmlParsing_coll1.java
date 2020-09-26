package test;


import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlParsing_coll1 {
	public static void main(String[] args) throws Exception {

		String DocumentURL = "http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem?serviceKey=mFW3I9zgl4vL6raWIh5QNyRZxIuxHHzVVutLRKteGhUZQnbd%2BYUocob3AsbcP6imSjhZ9FO8TwmdXZFLgVtxpg%3D%3D&";

		// xpath 생성
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList cols_2;
		NodeList cols_3;
		NodeList cols_4;
		NodeList cols_5;
		String TitleName2 = "";
		String TitleName = "";
		
		String result = "";

		
		for (int page = 1; page < 20; page++) {
			result = "";
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(DocumentURL + "&pageNo=" + page);

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
					

				}

				System.out.println();
				System.out.println(Item_name);
				System.out.println(result+"복사 끝");
				System.out.println();
				
				 Connection con = null; PreparedStatement pstmt = null;
				  
				  String driver = "com.mysql.jdbc.Driver"; String url1 =
				  "jdbc:mysql://localhost:3306/medicine"; String user = "root"; String password =
				  "1234";
				  
				  try {
					  
				Class.forName(driver);
				con = DriverManager.getConnection(url1, user,password);
				  
				  String sql = "UPDATE Medicine_Info SET EFFECT = ? WHERE ITEM_NAME=?";


				  pstmt = con.prepareStatement(sql);
				  pstmt.setString(1, result);
				  pstmt.setString(2, Item_name);
			
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

		}

	}

}
