package com.fet.estore.shopee.job;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fet.soft.util.RestTemplateUtil;

@Component
public class EstoreShopeeJob {

	private static final Log log = LogFactory.getLog(EstoreShopeeJob.class);

	public void process() throws Exception{
		log.info("====EstoreShopeeJob.process() START====");
		
		
		Map<String, String> data = new HashMap<>();
		data.put("orderSN", "201102VMWCUM1N");
		data.put("status", "BO");
    	data.put("logistics", "HTC");
    	data.put("logisticsNo", "999999999");
		String url ="http://10.64.33.156:48080/estore-api/shop/updateShopeeOrderStatus";
		String result = RestTemplateUtil.getInstance().doPost(url, MediaType.APPLICATION_JSON,data);
		log.info(result);
		
		
		
		
		
		log.info("====EstoreShopeeJob.process() END====");
	}

	public static void main(String[] args) {
		try {
			ApplicationContext ctx = new SpringApplicationBuilder(EstoreShopeeJob.class).web(WebApplicationType.NONE).run(args);
			EstoreShopeeJob estoreShopeeJob = ctx.getBean(EstoreShopeeJob.class);
			estoreShopeeJob.process();
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}

}
