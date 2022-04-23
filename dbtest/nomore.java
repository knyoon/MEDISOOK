package dbtest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.json.XML;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;


public class nomore {
	public void jsonex() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	           String connectionUrl = "jdbc:sqlserver://localhost:1433;database=seyoung;integratedSecurity=true";
			   Connection con = DriverManager.getConnection(connectionUrl);
	           Statement stmt = con.createStatement();
	           ResultSet rs=null;
	           PreparedStatement pstmt = null;
	           System.out.println("MS-SQL 서버 접속에 성공하였습니다.");
	           String sql = "INSERT INTO IGRCODEINFO (DRUG_NAME, GNL_CODE, DRUG_CODE)"
						+ " VALUES (?,?,?)";//여기 수정해야함 
	     
	 
	           for (int page=1;page<4;page++) {//4까지
		    		 String pageNum=Integer.toString(page);
				    		 //String pageNum=Integer.toString(page);
				    		 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551182/dgamtCrtrInfoService/getDgamtList"); /*URL*/
				    	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=LgPg%2BnD2Oy1X1zQBRYI%2FoGjOVdSa17g9KbfkO2xI7ZF68WYcYHtkCncTTmzS5Uw22mSynmpVfnW7TlF0o5WexA%3D%3D"); /*Service Key*/
				    	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("4", "UTF-8")); /*페이지번호*/
				    	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
				    	        urlBuilder.append("&" + URLEncoder.encode("mnfEntpNm","UTF-8") + "=" + URLEncoder.encode("얀센", "UTF-8")); /*품목명 (검색 유형 : %A%)*/
				    	       
				    	       
				    	        URL url = new URL(urlBuilder.toString());
				    	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				    	        conn.setRequestMethod("GET");
				    	        conn.setRequestProperty("Content-type", "application/json");
				    	        System.out.println("Response code: " + conn.getResponseCode());
				    	       
				    	        BufferedReader rd;
				    	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				    	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				    	        } else {
				    	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				    	        }
				    	        
				    	        String line;
				    	        String result="";		    	    
				    	        while ((line = rd.readLine()) != null) {
				    	        	result = result.concat(line);
				    	        }
				    	       
				    	       
				    	        org.json.JSONObject xml= XML.toJSONObject(result);
				    	        //System.out.println(xml);
				    	        
				    	        JSONParser parser = new JSONParser();
				    			JSONObject obj = (JSONObject) parser.parse(xml.toString());
				    			//System.out.println(obj);
				    			
				    			JSONObject parse_response = (JSONObject) obj.get("response");
				    			JSONObject parse_body = (JSONObject) parse_response.get("body");
				    			//String count=String.valueOf(parse_body.get("totalCount"));
				    			Object count =(Object) parse_body.get("totalCount");
								  //System.out.println(parse_body.get("totalCount"));
									if(count.toString().equals("0")) //여기 비교가 안됨. 
				    				{
										  System.out.println("메롱");
										  continue; 
									}
				    				JSONObject parse_item = (JSONObject) parse_body.get("items");
				    				if (parse_item.get("item").toString().charAt(0)=='{') {
				    					
				    					pstmt = con.prepareStatement(sql);
				    					JSONObject drug = (JSONObject) parse_item.get("item");
				    					//System.out.println(drug.toString());
				    					String mix_t = (String) drug.get("itmNm");// 약품이름
				    					pstmt.setString(1, mix_t);
				    				    String ingr_code = (String) drug.get("gnlNmCd");//성분코드 
				    					pstmt.setString(2,ingr_code); 
				    					String ingr_Ename = String.valueOf(drug.get("mdsCd")) ;//약품코드
				    					pstmt.setString(3, ingr_Ename); 
				    					int resultsql = pstmt.executeUpdate();
				    					

				    					
				    				}
				    				
				    				else {
				    					JSONArray parse_listArr = (JSONArray) parse_item.get("item");
				    					if (parse_listArr.size() > 0) {

						    				for (int i = 0; i < parse_listArr.size(); i++) {
						    					pstmt = con.prepareStatement(sql);
						    					JSONObject drug = (JSONObject) parse_listArr.get(i);
						    					//System.out.println(drug.toString());
						    					String mix_t = (String) drug.get("itmNm");// 약품이름
						    					pstmt.setString(1, mix_t);
						    				    String ingr_code = (String) drug.get("gnlNmCd");//성분코드 
						    					pstmt.setString(2,ingr_code); 
						    					String ingr_Ename = String.valueOf(drug.get("mdsCd")) ;//약품코드
						    					pstmt.setString(3, ingr_Ename); 
						    					  
						    					  
						    					 

						    					int resultsql = pstmt.executeUpdate();
						    					// System.out.println("처리된 레코드 수"+resultsql);

						    				}

						    			}

				    				}
					    	        
					    	        rd.close();
					    	        conn.disconnect();
					    	        
				    			
				    }
	        		   

	        	   	          
		    	stmt.close();   
		        con.close();
		
		}catch(SQLException e) {
			System.out.println(e+ "=> Sql 예외 ");
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
				
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		nomore tt=new nomore();
		tt.jsonex();
		
	}

}
