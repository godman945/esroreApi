package com.fet.estore.shopee.job;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//import com.fet.db.oracle.dao.CrossCooperation.CrossCooperationDAO;
import com.fet.db.oracle.pojo.CrossCooperation;
import com.fet.db.oracle.service.CrossCooperation.ICrossCooperationService;
import com.fet.soft.util.RestTemplateUtil;
import com.fet.spring.init.SpringbootWebApplication;

@Component
public class EstoreShopeeJob {

//	private static final Log log = LogFactory.getLog(EstoreShopeeJob.class);

	private Logger log = LogManager.getLogger(getClass());

//	@PersistenceContext
//	private EntityManager entityManager;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ICrossCooperationService crossCooperationService;

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Value("${shopee.valid.hours}")
	private int validHours = 24;

	@Transactional
	public void process() throws Exception {

//		List<CrossCooperation> ale = crossCooperationService.loadAll();
//		for (CrossCooperation crossCooperation : ale) {
//			if(crossCooperation.getUserName().equals("TEST1")) {
//				System.out.println(crossCooperation.getUserName());
//				System.out.println(crossCooperation.getCancelFlag());
//				
//				
////				crossCooperation.setCancelFlag("A");
////				crossCooperationService.saveOrUpdate(crossCooperation);
//			}
//		}
		if (true) {
			return;
		}

		log.info("====EstoreShopeeJob.process() START====");
		Timestamp jobTimeStamp = new Timestamp(System.currentTimeMillis());

		List<CrossCooperation> crossCooperationList = crossCooperationService.findShopeeUpdateJobData();

		Iterator<CrossCooperation> crossCooperationIterator = crossCooperationList.iterator();
		int index = 0;
		int successTotal = 0;
		int failTotal = 0;
		log.info(">>>>>總共需要處理筆數:" + crossCooperationList.size());
		while (crossCooperationIterator.hasNext()) {
			long startTime = System.currentTimeMillis();
			index = index + 1;
			log.info(">>>>>>START PROCESS ITEM:" + index);
			try {
				CrossCooperation crossCooperation = crossCooperationIterator.next();
//				log.info(">>>>>>getCreateTimestamp:" + crossCooperation.getCreateTimestamp());
//				log.info(">>>>>>getUserName:" + crossCooperation.getUserName());
//				log.info(">>>>>>getCono:" + crossCooperation.getCono());
//				log.info(">>>>>>getCancelFlag:" + crossCooperation.getCancelFlag());

				Timestamp timestam = new Timestamp(Long.valueOf(crossCooperation.getCreateTimestamp()));
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String format1 = simpleDateFormat.format(timestam);
				log.info(">>>>>>format1:" + format1);

				// 取得是否合法小時
				boolean hoursflag = isValidHours(timestam, jobTimeStamp);

				if (hoursflag) {
					// 開始呼叫API
					boolean apiflag = callShopeeApi(crossCooperation);
					crossCooperation.setCancelFlag("Y");
					crossCooperationService.saveOrUpdate(crossCooperation);
				}
			} catch (Exception e) {
				failTotal = failTotal + 1;
				log.info(">>>>>>FAIL PROCESS ITEM:" + index);
			}
			successTotal = successTotal + 1;
			log.info(">>>>>>END PROCESS ITEM:" + index);
		}

		log.info("***********");
		log.info("成功:" + successTotal);
		log.info("失敗:" + failTotal);
		log.info("***********");
		log.info("====EstoreShopeeJob.process() END====");

	}

	/*
	 * 呼叫蝦皮API
	 */
	public boolean callShopeeApi(CrossCooperation crossCooperation) throws Exception {
		Map<String, String> data = new HashMap<>();
		data.put(">>>>>>orderSN", "201102VMWCUM1N");
		data.put(">>>>>>status", "CNL24");
		data.put(">>>>>>logistics", "HTC");
		data.put(">>>>>>logisticsNo", "999999999");

		String url = "http://10.64.33.156:48080/estore-api/shop/updateShopeeOrderStatus";
//			   url = "http://localhost:5080/alexSpringBoot/products";
		String result = RestTemplateUtil.getInstance().doPost(url, MediaType.APPLICATION_JSON, data);
		JSONObject resultJson = new JSONObject(result);
		if (resultJson.get("rtnCode").equals("00000")) {
			log.info(">>>>>>API RETURN OK!!!");
			return true;
		}
		return false;
	}

	/**
	 * 檢查是否超過時間
	 */
	public boolean isValidHours(Timestamp createTimestamp, Timestamp jobTimestamp) throws Exception {
		long diff = (jobTimestamp.getTime() - createTimestamp.getTime());
		long hours = diff / (1000 * 60 * 60);
		if (hours > validHours) {
			return true;
		} else {
			return false;
		}
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
