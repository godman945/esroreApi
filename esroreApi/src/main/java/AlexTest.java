import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;

import com.fet.soft.util.RestTemplateUtil;

public class AlexTest {
	public static void main(String[] args) {
		try {
//			Map<String, String> data = new HashMap<>();
//			data.put("orderSN", "201102VMWCUM1N");
//			data.put("status", "BO");
//	    	data.put("logistics", "HTC");
//	    	data.put("logisticsNo", "999999999");
//			String url ="http://10.64.33.156:48080/estore-api/shop/updateShopeeOrderStatus";
//			String result = RestTemplateUtil.getInstance().doPost(url, MediaType.APPLICATION_JSON,data);
//			System.out.println(result);
			
			
			 Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			
			 String url = "jdbc:oracle:thin:@10.64.70.99:1530:estoreT3";    
			 String user = "es_dev ";
			 String password = "es_dev ";
			 Connection conn = DriverManager.getConnection(url, user, password);
			
			 System.out.println(conn.isClosed());
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
