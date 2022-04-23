package dbtest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class connectiontest5 {
	public void datainfo()throws ClassNotFoundException {//효능정보
		
		try{	
			
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	           String connectionUrl = "jdbc:sqlserver://localhost:1433;database=seyoung;integratedSecurity=true";
			   Connection con = DriverManager.getConnection(connectionUrl);
	           Statement stmt = con.createStatement();
	           System.out.println("MS-SQL 서버 접속에 성공하였습니다.");
	           String sql = "INSERT INTO EFCYINFO (NUM, DRUG_NAME, DRUG_CODE, ENTP_NAME, EFCY, USEMETHOD, "
	           +"WARNQES, QESITM, MIXWARN, SEQESITM, DEPOSIT)"
	           +" VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	           PreparedStatement pstmt = null;
	           int num=0;
	           


	          
		    	for (int page=1;page<45;page++) {//4까지
		    		 String pageNum=Integer.toString(page);
		    		 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"); /*URL*/
		    	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=LgPg%2BnD2Oy1X1zQBRYI%2FoGjOVdSa17g9KbfkO2xI7ZF68WYcYHtkCncTTmzS5Uw22mSynmpVfnW7TlF0o5WexA%3D%3D"); /*Service Key*/
		    	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNum, "UTF-8")); /*페이지번호*/
		    	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
		    	        
		    	        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) default : xml*/
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
		    	        
		    	       
		    	        JSONParser parser = new JSONParser();	
		    			JSONObject obj=(JSONObject)parser.parse(result);
		    			
		    			JSONObject parse_body=(JSONObject)obj.get("body");
		    			//System.out.println(parse_body.toString());
		    			JSONArray parse_listArr = (JSONArray) parse_body.get("items");
		    			
		    			if (parse_listArr.size() > 0) {
		    				
		    				for (int i = 0; i < parse_listArr.size(); i++) {
		    					pstmt = con.prepareStatement(sql);
		    					num+=1;
		    					JSONObject drug = (JSONObject) parse_listArr.get(i);
		    					/* System.out.println(drug.toString()); */
		    					pstmt.setInt(1, num);
		    					String itemname = (String) drug.get("itemName");
		    					pstmt.setString(2, itemname);
		    					String item_seq = (String) drug.get("itemSeq");//품목코드
		    					pstmt.setString(3, item_seq);
		    					String entp_name = (String) drug.get("entpName");//업체명
		    					pstmt.setString(4, entp_name);
		    					String efcy = (String) drug.get("efcyQesitm");//효능
		    					pstmt.setString(5, efcy);
		    					String usemethod = (String) drug.get("useMethodQesitm");//사용법
		    					pstmt.setString(6, usemethod);
		    					String warnq = (String) drug.get("atpnWarnQesitm");//필수사항
		    					pstmt.setString(7, warnq);
		    					String qesitm = (String) drug.get("atpnQesitm");//주의사항
		    					pstmt.setString(8, qesitm);
		    					String intrcq = (String) drug.get("intrcQesitm");//병용주의
		    					pstmt.setString(9, intrcq);
		    					String seQesitm = (String) drug.get("seQesitm");//부작용
		    					pstmt.setString(10, seQesitm);
		    					String deposit = (String) drug.get("depositMethodQesitm");//보관법
		    					pstmt.setString(11, deposit);

		    					
		    					
		    					int resultsql = pstmt.executeUpdate();
				    			//System.out.println("처리된 레코드 수"+resultsql);
				    			
		    				}

		    			}
		    			
		    	        rd.close();
		    	        conn.disconnect();
		    			/* System.out.println(sb.toString()); */
		    		
		    	}
		    	stmt.close();   
		        con.close(); 
		        pstmt.close();
		    }catch(SQLException e) {
		    	System.out.println(e+ "=> Sql 예외 ");
		    }catch (Exception e) {
				e.printStackTrace();
		    }

	}
	
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException {
		connectiontest5 ct = new connectiontest5();
		ct.datainfo();
  
		
		
       
    }//rs.close( );stmt.close( );conn.close( ); 예외처리로 다 쓰면 닫아주기
}
