package com.fet.estore.job;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.fet.db.oracle.service.coMaster.ICoMasterService;
import com.fet.db.oracle.service.crossCooperation.ICrossCooperationService;
import com.fet.enumerate.EnumFetIaStatus;
import com.fet.enumerate.EnumFetOrderStatus;
import com.fet.soft.util.RestTemplateUtil;
import com.fet.spring.init.SpringbootWebApplication;

@Component
public class EstoreShopeeJob {

	private Logger log = LogManager.getLogger(getClass());

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ICrossCooperationService crossCooperationService;
	
	@Autowired
	private ICoMasterService coMasterService;

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private Timestamp jobTimeStamp = new Timestamp(System.currentTimeMillis());
	
	@Value("${shopee.valid.hours}")
	private int validHours;

	@Value("${alex.msg}")
	private String alexMsg;
	
	
	@Transactional
	public void process() throws Exception {
		try {
			log.info("========EstoreShopeeJob.process() START========");
			
			log.info(">>>>>> env:"+alexMsg);
			log.info(">>>>>> validHours:"+validHours);
			
			//1.執行過時資料訂單取消API
//			processCancelOverTimeOrder();
			
//			//2.訂單狀態為C 呼叫蝦皮取消訂單
//			processCancelOrder();
//			
//			//3.執行CO_STATUS狀態且IA_STATUS不為C
			processCoMasterOrdeForApi();
			
			log.info("========EstoreShopeeJob.process() END========");
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	/**
	 * 訂單狀態為C 呼叫蝦皮取消訂單
	 * TGA狀態異動co_status,ia_status 無法異動到 CROSS_COOPERATION中co_status,ia_status
	 * */
	public void processCancelOrder() {
		try {
			log.info(">>>>>> START");
			Map<String,String> paramaterMap = new HashMap<String,String>();
			
			List<Map<String, String>> dataList = crossCooperationService.findCancelOrderDataStatus();
			
			Iterator<Map<String, String>> coStatusDataIterator = dataList.iterator();
			while (coStatusDataIterator.hasNext()) {
				paramaterMap.clear();
				Map<String,String> resultData = coStatusDataIterator.next();
				
				String orderSN =  resultData.get("ORDER_NO");
				String coStatus = EnumFetOrderStatus.FET_CNL.getType();
				String logistics = "";
				String logisticsNo = "";
				
				paramaterMap.put("orderSN", orderSN);
				paramaterMap.put("status", coStatus);
				paramaterMap.put("logistics", logistics);
				paramaterMap.put("logisticsNo", logisticsNo);
				
				boolean apiflag = callShopeeApi(paramaterMap);
				if(apiflag) {
					CrossCooperation crossCooperation = crossCooperationService.get(orderSN);
					crossCooperation.setOrderStatus(EnumFetIaStatus.FET_D.getType());
					crossCooperation.setCancelFlag("Y");
					crossCooperationService.saveOrUpdate(crossCooperation);
				}else {
					log.info(">>>>>> API FAIL POST DATA:"+paramaterMap);
				}
			}
			log.info(">>>>>> END");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 訂單成立後呼叫蝦皮API傳送狀態
	 * TGR,BCS狀態會與master co_status不同
	 * */
	public void processCoMasterOrdeForApi() {
		try {
			log.info(">>>>>> START");
			List<String> coStatusList = new ArrayList<String>();
			coStatusList.add(EnumFetOrderStatus.FET_BD.getType());
			coStatusList.add(EnumFetOrderStatus.FET_BO.getType());
			coStatusList.add(EnumFetOrderStatus.FET_TI.getType());
			coStatusList.add(EnumFetOrderStatus.FET_TGR.getType());
			Map<String,String> paramaterMap = new HashMap<String,String>();
			
			List<Map<String,String>> coStatusDataMapList = coMasterService.findCoMasterOrderDataForApi(coStatusList);

			Iterator<Map<String, String>> coStatusDataIterator = coStatusDataMapList.iterator();
			while (coStatusDataIterator.hasNext()) {
				paramaterMap.clear();
				Map<String,String> resultData = coStatusDataIterator.next();
				
				String orderSN =  resultData.get("ORDER_NO");
				String coStatus = StringUtils.isBlank(resultData.get("CO_STATUS")) || resultData.get("CO_STATUS").equals("null") ? "" :resultData.get("CO_STATUS");
				String sendShopeeCostatus = StringUtils.isBlank(resultData.get("CO_STATUS")) || resultData.get("CO_STATUS").equals("null") ? "" :resultData.get("CO_STATUS");
				String logistics = StringUtils.isBlank(resultData.get("PROVIDER_NAME")) || resultData.get("PROVIDER_NAME").equals("null") ? "" :resultData.get("PROVIDER_NAME");
				String logisticsNo = StringUtils.isBlank(resultData.get("CS_STORE_NO")) || resultData.get("CS_STORE_NO").equals("null") ? "" :resultData.get("CS_STORE_NO");
				String iaStatus = StringUtils.isBlank(resultData.get("IA_STATUS")) || resultData.get("IA_STATUS").equals("null") ? "" :resultData.get("IA_STATUS");
				//狀態為BD時已有配送資料則送BCS
				if(StringUtils.isNotBlank(logisticsNo) && coStatus.equals(EnumFetOrderStatus.FET_BD.getType())) {
					sendShopeeCostatus = EnumFetOrderStatus.FET_BCS.getType();
				}
				//IA_STATUS狀態為D送TGR
				if(iaStatus.equals(EnumFetIaStatus.FET_D.getType())) {
					sendShopeeCostatus = EnumFetOrderStatus.FET_TGR.getType();
				}
				
				paramaterMap.put("orderSN", orderSN);
				paramaterMap.put("status", sendShopeeCostatus);
				paramaterMap.put("logistics", logistics);
				paramaterMap.put("logisticsNo", logisticsNo);
				
				if(orderSN.equals("201111PY5N0VFR")
						|| orderSN.equals("201111Q7M4M6TJ")
						|| orderSN.equals("201111Q858YTBG")
						|| orderSN.equals("201111Q8A85Q8T")
						|| orderSN.equals("ALEX-TEST2")
						) {
					
				}else {
					continue;
				}
				
				boolean apiflag = callShopeeApi(paramaterMap);
				if(apiflag) {
					CrossCooperation crossCooperation = crossCooperationService.get(orderSN);
					crossCooperation.setOrderStatus(coStatus);
					crossCooperationService.saveOrUpdate(crossCooperation);
				}else {
					log.info(">>>>>> API FAIL POST DATA:"+paramaterMap);
				}
			}
			log.info(">>>>>> END");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 過時訂單取消狀態
	 * */
	public void processCancelOverTimeOrder() {
		try {
			log.info(">>>>>> START");
			List<CrossCooperation> crossCooperationList = crossCooperationService.findShopeeUpdateJobData();
			int index = 0;
			int processTotal = 0;
			int notProcessTotal = 0;
			int failTotal = 0;
			
			Map<String,String> paramaterMap = new HashMap<String,String>();
			
			log.info(">>>>>>total process size:" + crossCooperationList.size());
			Iterator<CrossCooperation> crossCooperationIterator = crossCooperationList.iterator();
			while (crossCooperationIterator.hasNext()) {
				paramaterMap.clear();
				long startTime = System.currentTimeMillis();
				index = index + 1;
				log.info(">>>>>>START PROCESS ITEM:" + index);
				try {
					CrossCooperation crossCooperation = crossCooperationIterator.next();
					log.info(">>>>>>getCono:" + crossCooperation.getCono());
					log.info(">>>>>>getOrderNo:" + crossCooperation.getOrderNo());

					Timestamp timestam = new Timestamp(Long.valueOf(crossCooperation.getCreateTimestamp()));
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String format1 = simpleDateFormat.format(timestam);
					log.info(">>>>>>CREATE TIME:" + format1);

					// 取得是否合法小時
					boolean hoursflag = isValidHours(timestam, jobTimeStamp);

					if (hoursflag) {
						// 開始呼叫API
						paramaterMap.put("orderSN", crossCooperation.getOrderNo());
						paramaterMap.put("status", EnumFetOrderStatus.FET_CNL24.getType());
						paramaterMap.put("logistics", "");
						paramaterMap.put("logisticsNo", "");
						System.out.println(">>>>>>>>>>>>>>>>"+paramaterMap);
							boolean apiflag = callShopeeApi(paramaterMap);
							if(apiflag) {
								crossCooperation.setCancelFlag("Y");
								crossCooperation.setCoStatus(EnumFetOrderStatus.FET_CNL24.getType());
								crossCooperationService.saveOrUpdate(crossCooperation);
								processTotal = processTotal + 1;
							}else {
								log.info(">>>>>> API FAIL POST DATA:"+paramaterMap);
							}
					}
					
					//驗證用
					else {
						notProcessTotal = notProcessTotal + 1;
					}
					
					long endTime = System.currentTimeMillis();
					log.info(">>>>>>ITEM COST MS:"+(endTime - startTime) );
				} catch (Exception e) {
					failTotal = failTotal + 1;
					log.info(">>>>>>FAIL PROCESS ITEM:");
				}
				log.info(">>>>>>END PROCESS ITEM:" + index);
			}
			
			
			log.info("*********************");
			log.info(">>>>>>TOTAL:"+crossCooperationList.size());
			log.info(">>>>>>PROCESS TOTAL:" + processTotal);
			log.info(">>>>>>NOT NEED PROCESS TOTAL:" + notProcessTotal);
			log.info(">>>>>>FAIL TOTAL:" + failTotal);
			log.info("*********************");
			
			log.info(">>>>>> END");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 呼叫蝦皮API
	 */
	public boolean callShopeeApi(Map<String,String> paramaterMap) throws Exception {
		String url = "http://10.64.33.156:48080/estore-api/shop/updateShopeeOrderStatus";
//			   url = "http://localhost:5080/alexSpringBoot/products";
		String result = RestTemplateUtil.getInstance().doPost(url, MediaType.APPLICATION_JSON, paramaterMap);
		JSONObject resultJson = new JSONObject(result);
		if (resultJson.get("rtnCode").equals("00000")) {
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
			ApplicationContext ctx = new SpringApplicationBuilder(SpringbootWebApplication.class).web(WebApplicationType.NONE).run(args);
			EstoreShopeeJob estoreShopeeJob = ctx.getBean(EstoreShopeeJob.class);
			estoreShopeeJob.process();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
