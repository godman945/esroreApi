package com.fet.estore.shopee.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fet.db.oracle.pojo.CrossCooperation;
import com.fet.soft.util.RestTemplateUtil;
import com.fet.spring.init.SpringbootWebApplication;

@Component
public class EstoreShopeeJob {

	private static final Log log = LogFactory.getLog(EstoreShopeeJob.class);

	@PersistenceContext
	private EntityManager entityManager;

//	@Transactional(readOnly=true)
	public void process() throws Exception {
		log.info("====EstoreShopeeJob.process() START====");

		String hr = "24";

		Query query = entityManager.createQuery(
				"  from CrossCooperation where 1=1 and  cono is null and (cancelFlag <> 'Y' or cancelFlag is null)  ");
//			
		List<CrossCooperation> crossCooperationList = query.getResultList();
		
		Iterator<CrossCooperation> crossCooperationIterator = crossCooperationList.iterator();
		int index = 0;
		int successTotal = 0;
		int failTotal = 0;
        while (crossCooperationIterator.hasNext()) {
        	long startTime = System.currentTimeMillis();
			index = index + 1;
			log.info(">>>>>>START PROCESS ITEM:" + index);
        	try {
        		CrossCooperation crossCooperation = crossCooperationIterator.next();
    			log.info(crossCooperation.getUserName());
    			log.info(crossCooperation.getCreateTime());
            	
    			
    			if(index==2) {
    				List s= new ArrayList();
    				s.get(3);
    			}
    			Map<String, String> data = new HashMap<>();
    			data.put(">>>>>>orderSN", "201102VMWCUM1N");
    			data.put(">>>>>>status", "CNL24");
    			data.put(">>>>>>logistics", "HTC");
    			data.put(">>>>>>logisticsNo", "999999999");
    			String url = "http://10.64.33.156:48080/estore-api/shop/updateShopeeOrderStatus";
    			url = "http://localhost:5080/alexSpringBoot/products";
    			String result = RestTemplateUtil.getInstance().doPost(url, MediaType.APPLICATION_JSON, data);
    			JSONObject resultJson = new JSONObject(result);
    			if(resultJson.get("rtnCode").equals("00000")) {
    				log.info(">>>>>>API RETURN OK!!!");
    				crossCooperation.setCancelFlag("Y");
    				entityManager.merge(crossCooperation);
    			}
        	}catch(Exception e) {
        		failTotal = failTotal + 1;
//        		entityManager.getTransaction().rollback();
        		log.info(">>>>>>FAIL PROCESS ITEM:" + index);
        	}
        	successTotal = successTotal + 1;
			log.info(">>>>>>END PROCESS ITEM:" + index);
        }
		
		log.info("***********");
		log.info("成功:"+successTotal);
		log.info("失敗:"+failTotal);
		log.info("***********");
		
		log.info("====EstoreShopeeJob.process() END====");
	}

	public static void main(String[] args) {
		try {
			ApplicationContext ctx = new SpringApplicationBuilder(SpringbootWebApplication.class)
					.web(WebApplicationType.NONE).run(args);
			EstoreShopeeJob estoreShopeeJob = ctx.getBean(EstoreShopeeJob.class);
			estoreShopeeJob.process();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
