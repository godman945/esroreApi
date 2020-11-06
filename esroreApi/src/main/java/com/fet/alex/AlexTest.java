package com.fet.alex;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.fet.db.oracle.service.Acc2giftLog.Acc2giftLogService;
import com.fet.spring.init.SpringbootWebApplication;



@Component
public class AlexTest {
	public static void main(String[] args) {
		try {
			
			
//			OracleDataSource dataSource = new OracleDataSource();
//			dataSource.setUser("es_dev");
//			dataSource.setPassword("es_dev");
//			dataSource.setURL("jdbc:oracle:thin:@10.64.70.99:1530:estoreT3");
//			dataSource.setImplicitCachingEnabled(true);
//			dataSource.setFastConnectionFailoverEnabled(true);
//			System.out.println(dataSource.getConnection().isClosed());
			ApplicationContext ctx = new SpringApplicationBuilder(SpringbootWebApplication.class).web(WebApplicationType.NONE).run(args);
			AlexTest alexTest = ctx.getBean(AlexTest.class);
			System.out.println(alexTest == null);
//			
//			
//			
//			
//			
			Acc2giftLogService acc2giftLogService = ctx.getBean(Acc2giftLogService.class);
			System.out.println(acc2giftLogService == null);
			
			acc2giftLogService.alex();
			
			
//			Map<String, String> data = new HashMap<>();
//			data.put("orderSN", "201102VMWCUM1N");
//			data.put("status", "BO");
//	    	data.put("logistics", "HTC");
//	    	data.put("logisticsNo", "999999999");
//			String url ="http://10.64.33.156:48080/estore-api/shop/updateShopeeOrderStatus";
//			String result = RestTemplateUtil.getInstance().doPost(url, MediaType.APPLICATION_JSON,data);
//			System.out.println(result);
			
			
//			 Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
//			
//			 String url = "jdbc:oracle:thin:@10.64.70.99:1530:estoreT3";    
//			 String user = "es_dev ";
//			 String password = "es_dev ";
//			 Connection conn = DriverManager.getConnection(url, user, password);
//			
//			 System.out.println(conn.isClosed());
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
