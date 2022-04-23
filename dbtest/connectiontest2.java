package dbtest;

import java.sql.*;
import java.sql.SQLException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class connectiontest2 {

	public void datainfo()throws ClassNotFoundException {//병용금기
		
		try{	
			
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	           String connectionUrl = "jdbc:sqlserver://localhost:1433;database=seyoung;integratedSecurity=true";
			   Connection con = DriverManager.getConnection(connectionUrl);
	           Statement stmt = con.createStatement();
	           System.out.println("MS-SQL 서버 접속에 성공하였습니다.");
	           String sql = "INSERT INTO MIXWARNING (NUM,MIX_TYPE, INGR_CODE, INGR_ENG_NAME, INGR_NAME, ORI_INGR, "
	           +"CLASS_NAME, MIXT_T, MINGR_CODE, MINGR_ENAME, MINGR_KNAME,M_ORI, M_CLASS, PROHBT_CONTENT)"
	           +" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	           PreparedStatement pstmt = null;
	           int num=0;
	           


	          
		    	for (int page=1;page<14;page++) {//4까지
		    		 String pageNum=Integer.toString(page);
		    		 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DURIrdntInfoService01/getUsjntTabooInfoList"); /*URL*/
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
		    					String mix_t = (String) drug.get("MIX_TYPE");//단일/복합
		    					pstmt.setString(2, mix_t);
		    					//System.out.println(mix_t);
		    					String ingr_code = (String) drug.get("INGR_CODE");//성분코드
		    					pstmt.setString(3, ingr_code);
		    					String ingr_Ename = (String) drug.get("INGR_ENG_NAME");//성분영문
		    					pstmt.setString(4, ingr_Ename);
		    					String ingr_Kname = (String) drug.get("INGR_KOR_NAME");//성분한글
		    					pstmt.setString(5, ingr_Kname);
		    					String ori = (String) drug.get("ORI");//관계성분
		    					pstmt.setString(6, ori);
		    					String efclass = (String) drug.get("CLASS");//약효분류코드
		    					pstmt.setString(7, efclass);
		    					String mixt_t = (String) drug.get("MIXTURE_MIX_TYPE");//병용금기 단일/복합
		    					pstmt.setString(8, mixt_t);
		    					String mIngr_code = (String) drug.get("MIXTURE_INGR_CODE");//병용금기 성분코드
		    					pstmt.setString(9, mIngr_code);
		    					String mIngr_Ename = (String) drug.get("MIXTURE_INGR_ENG_NAME");//병금성분영문
		    					pstmt.setString(10, mIngr_Ename);
		    					String mIngr_Kname = (String) drug.get("MIXTURE_INGR_KOR_NAME");//병금성분한글
		    					pstmt.setString(11, mIngr_Kname);
		    					String m_ori = (String) drug.get("MIXTURE_ORI");//병용금기관계성분
		    					pstmt.setString(12, m_ori);
		    					String mefclass = (String) drug.get("MIXTURE_CLASS");//병용금기약효분류
		    					pstmt.setString(13, mefclass);
		    					String warncontent = (String) drug.get("PROHBT_CONTENT");//금기내용
		    					pstmt.setString(14, warncontent);

		    					
		    					
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
		connectiontest2 ct = new connectiontest2();
		ct.datainfo();
  
		
		
       
    }//rs.close( );stmt.close( );conn.close( ); 예외처리로 다 쓰면 닫아주기

}
