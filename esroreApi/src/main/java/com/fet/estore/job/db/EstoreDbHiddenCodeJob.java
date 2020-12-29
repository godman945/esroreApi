package com.fet.estore.job.db;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.fet.db.oracle.pojo.CoMaster;
import com.fet.db.oracle.pojo.CoMaster20200709Hk;
import com.fet.db.oracle.pojo.CrossCooperation;
import com.fet.db.oracle.pojo.FridayOrder;
import com.fet.db.oracle.pojo.LoyaltyRecord;
import com.fet.db.oracle.pojo.NbaCoMaster;
import com.fet.db.oracle.pojo.NbaNameList;
import com.fet.db.oracle.pojo.PersonalInformationBackup;
import com.fet.db.oracle.pojo.PersonalInformationBackupId;
import com.fet.db.oracle.pojo.TmpCoMaster;
import com.fet.db.oracle.service.coMaster.ICoMaster20200709HkService;
import com.fet.db.oracle.service.coMaster.ICoMasterService;
import com.fet.db.oracle.service.coMaster.ITmpCoMasterService;
import com.fet.db.oracle.service.crossCooperation.ICrossCooperationService;
import com.fet.db.oracle.service.fridayOrder.IFridayOrderService;
import com.fet.db.oracle.service.loyaltyRecord.ILoyaltyRecordService;
import com.fet.db.oracle.service.mailingList.IMailingListService;
import com.fet.db.oracle.service.nbaCoMaster.INbaCoMasterService;
import com.fet.db.oracle.service.nbaNameList.INbaNameListService;
import com.fet.db.oracle.service.ocrRecord.IOcrRecordService;
import com.fet.db.oracle.service.soTemp.ISoTempService;
import com.fet.db.oracle.service.sosaTemp.ISosaTempService;
import com.fet.soft.util.AESUtil;
import com.fet.soft.util.StringUtil;
import com.fet.spring.init.SpringbootWebApplication;



@Component
public class EstoreDbHiddenCodeJob {

	private Logger log = LogManager.getLogger(getClass());

	private final String encodeKey = "TW3CRZ8YTZKL38P4YDNGP56PRR6BCK93";

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ICoMaster20200709HkService coMaster20200709HkService;
	
	@Autowired
	private ICoMasterService coMasterService;
	
	@Autowired
	private ITmpCoMasterService tmpCoMasterService;
	
	@Autowired
	private ICrossCooperationService crossCooperationService;
	
	@Autowired
	private IFridayOrderService fridayOrderService;
	
	@Autowired
	private ILoyaltyRecordService loyaltyRecordService;

	@Autowired
	private IMailingListService mailingListService;
	
	@Autowired
	private INbaCoMasterService nbaCoMasterService;
	
	@Autowired
	private INbaNameListService nbaNameListService;
	
	@Autowired
	private IOcrRecordService ocrRecordService;
	
	@Autowired
	private ISosaTempService sosaTempService;
	
	
	@Autowired
	private ISoTempService soTempService;
	
	
	

	
	public void process() throws Exception {
//		//1.處理CO_MASTER
		processCoMaster();
//		//2.處理TMP_CO_MASTER
//		processTempCoMaster();
		//3.處理CROSS_COOPERATION
//		processCrossCooperation();
		//4.處理FRIDAY_ORDER
//		processFridayOrder();
		//5.處理LOYALTY_RECORD
//		processLoyaltyRecord();
		//6.處理MAILING_LIST
//	#	processMailingList();
		//7.處理NBA_CO_MASTER
//		processNbaCoMaster();
		
		//8.處理NBA_NAME_LIST
//		processNbaNameList();
		
		//9.處理OCR_RECORD
//	#	processOcrRecord();
		
		//10.處理SO_TEMP
//	#	processSoTemp();
		
		//11.處理SOSA_TEMP
//	#	processSosaTemp();
		
	}
	
	
	/**
	 * 處理CO_MASTER
	 * */
	public void processCoMaster() throws Exception{
		log.info("====== CO_MASTER ENCODE START");
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.MANUAL);
		Transaction transaction = session.beginTransaction();
		Query query = session.createSQLQuery(coMasterSqlStr());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,String>> result = query.list();
		Date date = new Date();
		int i = 0;
		int total = result.size();
		for (Map<String, String> rowDataMap : result) {
			PersonalInformationBackup personalInformationBackup = new PersonalInformationBackup();
			try{
				String cono = StringUtils.isBlank(rowDataMap.get("CONO")) ? "" : rowDataMap.get("CONO");
				log.info(">>>>>>hidden process cono:"+cono);
				//使用者 
				String userName = StringUtils.isBlank(rowDataMap.get("USER_NAME")) ? "" : rowDataMap.get("USER_NAME");
				//身分證/健保卡
				String rocId = StringUtils.isBlank(rowDataMap.get("ROC_ID")) ? "" : rowDataMap.get("ROC_ID");
				String mail = StringUtils.isBlank(rowDataMap.get("EMAIL")) ? "" : rowDataMap.get("EMAIL");
				//送貨聯絡電話
				String deliTel = StringUtils.isBlank(rowDataMap.get("DELI_TEL")) ? "" : rowDataMap.get("DELI_TEL");
				//申辦人聯絡電話一
				String tel1 = StringUtils.isBlank(rowDataMap.get("TEL1")) ? "" : rowDataMap.get("TEL1");
				//申辦人聯絡電話二
				String tel2 = StringUtils.isBlank(rowDataMap.get("TTEL2")) ? "" : rowDataMap.get("TTEL2");
				//送貨地址
				String deliAddr = StringUtils.isBlank(rowDataMap.get("DELI_ADDR")) ? "" : rowDataMap.get("DELI_ADDR");
				//生日
				String birthday = StringUtils.isBlank(rowDataMap.get("BIRTHDAY")) ? "" : rowDataMap.get("BIRTHDAY");
				//收件人
				String reccipient = StringUtils.isBlank(rowDataMap.get("RECCIPIENT")) ? "" : rowDataMap.get("RECCIPIENT");
				//帳單地址
				String billAddr = StringUtils.isBlank(rowDataMap.get("BILL_ADDR")) ? "" : rowDataMap.get("BILL_ADDR");
				//戶籍地址
				String pAddr = StringUtils.isBlank(rowDataMap.get("P_ADDR")) ? "" : rowDataMap.get("P_ADDR");
				//收件人
				String deliReceive = StringUtils.isBlank(rowDataMap.get("DELI_RECEIVER")) ? "" : rowDataMap.get("DELI_RECEIVER");
				//收件人信箱
				String deliEmail = StringUtils.isBlank(rowDataMap.get("DELI_EMAIL")) ? "" : rowDataMap.get("DELI_EMAIL");
				//門號
				String msisdn = StringUtils.isBlank(rowDataMap.get("MSISDN")) ? "" : rowDataMap.get("MSISDN");
				//複合主鍵
				PersonalInformationBackupId personalInformationBackupId = new PersonalInformationBackupId();
				personalInformationBackupId.setTableName("CO_MASTER");
				personalInformationBackupId.setTablePk(cono);
				personalInformationBackupId.setUuid(UUID.randomUUID().toString());
				
				personalInformationBackup.setId(personalInformationBackupId);
				personalInformationBackup.setUserName(AESUtil.encrypt(userName, encodeKey));
				personalInformationBackup.setRocId(AESUtil.encrypt(rocId, encodeKey));
				personalInformationBackup.setEmail(AESUtil.encrypt(mail, encodeKey));
				personalInformationBackup.setDeliTel(AESUtil.encrypt(deliTel, encodeKey));
				personalInformationBackup.setTel1(AESUtil.encrypt(tel1, encodeKey));
				personalInformationBackup.setTtel2(AESUtil.encrypt(tel2, encodeKey));
				personalInformationBackup.setDeliAddr(AESUtil.encrypt(deliAddr, encodeKey));
				personalInformationBackup.setBirthday(AESUtil.encrypt(birthday, encodeKey));
				personalInformationBackup.setReccipient(AESUtil.encrypt(reccipient, encodeKey));
				personalInformationBackup.setBillAddr(AESUtil.encrypt(billAddr, encodeKey));
				personalInformationBackup.setPAddr(AESUtil.encrypt(pAddr, encodeKey));
				personalInformationBackup.setDeliReceiver(AESUtil.encrypt(deliReceive, encodeKey));
				personalInformationBackup.setDeliEmail(AESUtil.encrypt(deliEmail, encodeKey));
				personalInformationBackup.setMsisdn(AESUtil.encrypt(msisdn, encodeKey));
				personalInformationBackup.setUpdateDate(date);
				
				userName = StringUtil.getInstance().getHiddenDataByName(userName);
				rocId = StringUtil.getInstance().getHiddenDataById(rocId);
				mail= StringUtil.getInstance().getHiddenDataByEmail(mail);
				deliTel = StringUtil.getInstance().getHiddenDataByNum(deliTel);
				tel1 = StringUtil.getInstance().getHiddenDataByNum(tel1);
				tel2 = StringUtil.getInstance().getHiddenDataByNum(tel2);
				deliAddr = StringUtil.getInstance().getHiddenDataByAddr(deliAddr);
				birthday = StringUtil.getInstance().getHiddenDataByBirthday(birthday);
				reccipient = StringUtil.getInstance().getHiddenDataByName(reccipient);
				billAddr = StringUtil.getInstance().getHiddenDataByAddr(billAddr);
				pAddr = StringUtil.getInstance().getHiddenDataByAddr(pAddr);
				deliReceive = StringUtil.getInstance().getHiddenDataByName(deliReceive);
				deliEmail = StringUtil.getInstance().getHiddenDataByEmail(deliEmail);
				msisdn = StringUtil.getInstance().getHiddenDataByNum(msisdn);
				
				
				CoMaster coMaster = coMasterService.get(cono);
				coMaster.setUserName(userName);
				coMaster.setRocId(rocId);
				coMaster.setEmail(mail);
				coMaster.setDeliTel(deliTel);
				coMaster.setTel1(tel1);
				coMaster.setTtel2(tel2);
				coMaster.setDeliAddr(deliAddr);
				coMaster.setBirthday(birthday);
				coMaster.setReccipient(reccipient);
				coMaster.setBillAddr(billAddr);
				coMaster.setPAddr(pAddr);
				coMaster.setDeliReceiver(deliReceive);
				coMaster.setDeliEmail(deliEmail);
				coMaster.setMsisdn(msisdn);
				
				session.saveOrUpdate(coMaster);
				session.saveOrUpdate(personalInformationBackup);
			}catch(Exception e){
				log.error(">>>>>>:"+e.getMessage());
				continue;
			}
			i = i + 1;
			if(i == total){
				session.flush();
	            session.clear();
			}else{
				if(i % 500 == 0){
		            session.flush();
		            session.clear();
				}
			}
			
			if(i%1000 == 0){
				System.out.println(">>>>>>>>>>>>處理完" + i + "筆");
				 transaction.commit();
				 transaction.begin();
			}
			if(i == total){
				System.out.println(">>>>>>>>>>>>總共處理完" + i + "筆");
				transaction.commit();
			}
		}
		log.info("====== CO_MASTER ENCODE FINISH");
	}
	
	/**
	 * 處理TMP_CO_MASTER
	 * */
	public void processTempCoMaster() throws Exception{
		log.info("====== TMP_CO_MASTER ENCODE START");
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.MANUAL);
		Transaction transaction = session.beginTransaction();
		Query query = session.createSQLQuery(tmpCoMasterSqlStr());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,String>> result = query.list();
		Date date = new Date();
		int i = 0;
		int total = result.size();
		for (Map<String, String> rowDataMap : result) {
			PersonalInformationBackup personalInformationBackup = new PersonalInformationBackup();
			try{
				String cono = StringUtils.isBlank(rowDataMap.get("CONO")) ? "" : rowDataMap.get("CONO");
				//使用者 
				String userName = StringUtils.isBlank(rowDataMap.get("USER_NAME")) ? "" : rowDataMap.get("USER_NAME");
				//身分證/健保卡
				String rocId = StringUtils.isBlank(rowDataMap.get("ROC_ID")) ? "" : rowDataMap.get("ROC_ID");
				String mail = StringUtils.isBlank(rowDataMap.get("EMAIL")) ? "" : rowDataMap.get("EMAIL");
				//送貨聯絡電話
				String deliTel = StringUtils.isBlank(rowDataMap.get("DELI_TEL")) ? "" : rowDataMap.get("DELI_TEL");
				//申辦人聯絡電話一
				String tel1 = StringUtils.isBlank(rowDataMap.get("TEL1")) ? "" : rowDataMap.get("TEL1");
				//申辦人聯絡電話二
				String tel2 = StringUtils.isBlank(rowDataMap.get("TTEL2")) ? "" : rowDataMap.get("TTEL2");
				//送貨地址
				String deliAddr = StringUtils.isBlank(rowDataMap.get("DELI_ADDR")) ? "" : rowDataMap.get("DELI_ADDR");
				//生日
				String birthday = StringUtils.isBlank(rowDataMap.get("BIRTHDAY")) ? "" : rowDataMap.get("BIRTHDAY");
				//收件人
				String reccipient = StringUtils.isBlank(rowDataMap.get("RECCIPIENT")) ? "" : rowDataMap.get("RECCIPIENT");
				//帳單地址
				String billAddr = StringUtils.isBlank(rowDataMap.get("BILL_ADDR")) ? "" : rowDataMap.get("BILL_ADDR");
				//戶籍地址
				String pAddr = StringUtils.isBlank(rowDataMap.get("P_ADDR")) ? "" : rowDataMap.get("P_ADDR");
				//收件人
				String deliReceive = StringUtils.isBlank(rowDataMap.get("DELI_RECEIVER")) ? "" : rowDataMap.get("DELI_RECEIVER");
				//收件人信箱
				String deliEmail = StringUtils.isBlank(rowDataMap.get("DELI_EMAIL")) ? "" : rowDataMap.get("DELI_EMAIL");
				//門號
				String msisdn = StringUtils.isBlank(rowDataMap.get("MSISDN")) ? "" : rowDataMap.get("MSISDN");
				//複合主鍵
				PersonalInformationBackupId personalInformationBackupId = new PersonalInformationBackupId();
				personalInformationBackupId.setTableName("TMP_CO_MASTER");
				personalInformationBackupId.setTablePk(cono);
				personalInformationBackupId.setUuid(UUID.randomUUID().toString());
				
				personalInformationBackup.setId(personalInformationBackupId);
				personalInformationBackup.setUserName(AESUtil.encrypt(userName, encodeKey));
				personalInformationBackup.setRocId(AESUtil.encrypt(rocId, encodeKey));
				personalInformationBackup.setEmail(AESUtil.encrypt(mail, encodeKey));
				personalInformationBackup.setDeliTel(AESUtil.encrypt(deliTel, encodeKey));
				personalInformationBackup.setTel1(AESUtil.encrypt(tel1, encodeKey));
				personalInformationBackup.setTtel2(AESUtil.encrypt(tel2, encodeKey));
				personalInformationBackup.setDeliAddr(AESUtil.encrypt(deliAddr, encodeKey));
				personalInformationBackup.setBirthday(AESUtil.encrypt(birthday, encodeKey));
				personalInformationBackup.setReccipient(AESUtil.encrypt(reccipient, encodeKey));
				personalInformationBackup.setBillAddr(AESUtil.encrypt(billAddr, encodeKey));
				personalInformationBackup.setPAddr(AESUtil.encrypt(pAddr, encodeKey));
				personalInformationBackup.setDeliReceiver(AESUtil.encrypt(deliReceive, encodeKey));
				personalInformationBackup.setDeliEmail(AESUtil.encrypt(deliEmail, encodeKey));
				personalInformationBackup.setMsisdn(AESUtil.encrypt(msisdn, encodeKey));
				personalInformationBackup.setUpdateDate(date);
				
				userName = StringUtil.getInstance().getHiddenDataByName(userName);
				rocId = StringUtil.getInstance().getHiddenDataById(rocId);
				mail= StringUtil.getInstance().getHiddenDataByEmail(mail);
				deliTel = StringUtil.getInstance().getHiddenDataByNum(deliTel);
				tel1 = StringUtil.getInstance().getHiddenDataByNum(tel1);
				tel2 = StringUtil.getInstance().getHiddenDataByNum(tel2);
				deliAddr = StringUtil.getInstance().getHiddenDataByAddr(deliAddr);
				birthday = StringUtil.getInstance().getHiddenDataByBirthday(birthday);
				reccipient = StringUtil.getInstance().getHiddenDataByName(reccipient);
				billAddr = StringUtil.getInstance().getHiddenDataByAddr(billAddr);
				pAddr = StringUtil.getInstance().getHiddenDataByAddr(pAddr);
				deliReceive = StringUtil.getInstance().getHiddenDataByName(deliReceive);
				deliEmail = StringUtil.getInstance().getHiddenDataByEmail(deliEmail);
				msisdn = StringUtil.getInstance().getHiddenDataByNum(msisdn);
				
				TmpCoMaster tmpCoMaster = tmpCoMasterService.get(cono);
				tmpCoMaster.setUserName(userName);
				tmpCoMaster.setRocId(rocId);
				tmpCoMaster.setEmail(mail);
				tmpCoMaster.setDeliTel(deliTel);
				tmpCoMaster.setTel1(tel1);
				tmpCoMaster.setTtel2(tel2);
				tmpCoMaster.setDeliAddr(deliAddr);
				tmpCoMaster.setBirthday(birthday);
				tmpCoMaster.setReccipient(reccipient);
				tmpCoMaster.setBillAddr(billAddr);
				tmpCoMaster.setPAddr(pAddr);
				tmpCoMaster.setDeliReceiver(deliReceive);
				tmpCoMaster.setDeliEmail(deliEmail);
				tmpCoMaster.setMsisdn(msisdn);
				
				session.saveOrUpdate(tmpCoMaster);
				session.saveOrUpdate(personalInformationBackup);
			}catch(Exception e){
				log.error(">>>>>>:"+e.getMessage());
				continue;
			}
			i = i + 1;
			if(i == total){
				session.flush();
	            session.clear();
			}else{
				if(i % 500 == 0){
		            session.flush();
		            session.clear();
				}
			}
			
			if(i%1000 == 0){
				System.out.println(">>>>>>>>>>>>處理完" + i + "筆");
			}
			if(i == total){
				System.out.println(">>>>>>>>>>>>總共處理完" + i + "筆");
			}
		}
		transaction.commit();
		log.info("====== TMP_CO_MASTER ENCODE FINISH");
	}
	
	
	/**
	 * 處理CROSS_COOPERATION
	 * */
	public void processCrossCooperation() throws Exception{
		log.info("====== CROSS_COOPERATION ENCODE START");
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.MANUAL);
		Transaction transaction = session.beginTransaction();
		Query query = session.createSQLQuery(crossCooperationSqlStr());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,String>> result = query.list();
		Date date = new Date();
		int i = 0;
		int total = result.size();
		for (Map<String, String> rowDataMap : result) {
			PersonalInformationBackup personalInformationBackup = new PersonalInformationBackup();
			try{
				String orderNo = StringUtils.isBlank(rowDataMap.get("ORDER_NO")) ? "" : rowDataMap.get("ORDER_NO");
				String userName = StringUtils.isBlank(rowDataMap.get("USER_NAME")) ? "" : rowDataMap.get("USER_NAME");
				String userMobile = StringUtils.isBlank(rowDataMap.get("USER_MOBILE")) ? "" : rowDataMap.get("USER_MOBILE");
				String msisdn = StringUtils.isBlank(rowDataMap.get("MSISDN")) ? "" : rowDataMap.get("MSISDN");
				
				//複合主鍵
				PersonalInformationBackupId personalInformationBackupId = new PersonalInformationBackupId();
				personalInformationBackupId.setTableName("CROSS_COOPERATION");
				personalInformationBackupId.setTablePk(orderNo);
				personalInformationBackupId.setUuid(UUID.randomUUID().toString());
				
				personalInformationBackup.setId(personalInformationBackupId);
				personalInformationBackup.setUserName(AESUtil.encrypt(userName, encodeKey));
				personalInformationBackup.setUserMobile(AESUtil.encrypt(userMobile, encodeKey));
				personalInformationBackup.setMsisdn(AESUtil.encrypt(msisdn, encodeKey));
				personalInformationBackup.setUpdateDate(date);
				
				
				userName = StringUtil.getInstance().getHiddenDataByName(userName);
				userMobile = StringUtil.getInstance().getHiddenDataByNum(userMobile);
				msisdn = StringUtil.getInstance().getHiddenDataByNum(msisdn);
				
				CrossCooperation crossCooperation = crossCooperationService.get(orderNo);
				crossCooperation.setUserName(userName);
				crossCooperation.setUserMobile(userMobile);
				crossCooperation.setMsisdn(msisdn);
				
				session.saveOrUpdate(crossCooperation);
				session.saveOrUpdate(personalInformationBackup);
			}catch(Exception e){
				log.error(">>>>>>:"+e.getMessage());
				continue;
			}
			i = i + 1;
			if(i == total){
				session.flush();
	            session.clear();
			}else{
				if(i % 500 == 0){
		            session.flush();
		            session.clear();
				}
			}
			
			if(i%1000 == 0){
				System.out.println(">>>>>>>>>>>>處理完" + i + "筆");
			}
			if(i == total){
				System.out.println(">>>>>>>>>>>>總共處理完" + i + "筆");
			}
		}
		transaction.commit();
		log.info("====== CROSS_COOPERATION ENCODE FINISH");
	}
	
	/**
	 * 處理FRIDAY_ORDER
	 * */
	public void processFridayOrder() throws Exception{
		log.info("====== FRIDAY_ORDER ENCODE START");
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.MANUAL);
		Transaction transaction = session.beginTransaction();
		Query query = session.createSQLQuery(fridayOrderSqlStr());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,String>> result = query.list();
		Date date = new Date();
		int i = 0;
		int total = result.size();
		for (Map<String, String> rowDataMap : result) {
			PersonalInformationBackup personalInformationBackup = new PersonalInformationBackup();
			try{
				String id = StringUtils.isBlank(rowDataMap.get("ID")) ? "" : rowDataMap.get("ID");
				String chineseName = StringUtils.isBlank(rowDataMap.get("CHINESE_NAME")) ? "" : rowDataMap.get("CHINESE_NAME");
				String email = StringUtils.isBlank(rowDataMap.get("EMAIL")) ? "" : rowDataMap.get("EMAIL");
				String msisdn = StringUtils.isBlank(rowDataMap.get("MSISDN")) ? "" : rowDataMap.get("MSISDN");
				String address = StringUtils.isBlank(rowDataMap.get("ADDRESS")) ? "" : rowDataMap.get("ADDRESS");
				
//				//複合主鍵
				PersonalInformationBackupId personalInformationBackupId = new PersonalInformationBackupId();
				personalInformationBackupId.setTableName("FRIDAY_ORDER");
				personalInformationBackupId.setTablePk(id);
				personalInformationBackupId.setUuid(UUID.randomUUID().toString());
				
				personalInformationBackup.setId(personalInformationBackupId);
				personalInformationBackup.setChineseName(AESUtil.encrypt(chineseName, encodeKey));
				personalInformationBackup.setEmail(AESUtil.encrypt(email, encodeKey));
				personalInformationBackup.setMsisdn(AESUtil.encrypt(msisdn, encodeKey));
				personalInformationBackup.setAddress(AESUtil.encrypt(address, encodeKey));
				personalInformationBackup.setUpdateDate(date);
				
				
				chineseName = StringUtil.getInstance().getHiddenDataByName(chineseName);
				email = StringUtil.getInstance().getHiddenDataByEmail(email);
				msisdn = StringUtil.getInstance().getHiddenDataByNum(msisdn);
				address = StringUtil.getInstance().getHiddenDataByAddr(address);
				
				FridayOrder fridayOrder = fridayOrderService.get(id);
				fridayOrder.setChineseName(chineseName);
				fridayOrder.setEmail(email);
				fridayOrder.setMsisdn(msisdn);
				fridayOrder.setAddress(address);
//				
				session.saveOrUpdate(fridayOrder);
				session.saveOrUpdate(personalInformationBackup);
			}catch(Exception e){
				log.error(">>>>>>:"+e.getMessage());
				continue;
			}
			i = i + 1;
			if(i == total){
				session.flush();
	            session.clear();
			}else{
				if(i % 500 == 0){
		            session.flush();
		            session.clear();
				}
			}
			
			if(i%1000 == 0){
				System.out.println(">>>>>>>>>>>>處理完" + i + "筆");
			}
			if(i == total){
				System.out.println(">>>>>>>>>>>>總共處理完" + i + "筆");
			}
		}
		transaction.commit();
		log.info("====== FRIDAY_ORDER ENCODE FINISH");
	}
	
	/**
	 * 處理LOYALTY_RECORD
	 * */
	public void processLoyaltyRecord() throws Exception{
		log.info("====== LOYALTY_RECORD ENCODE START");
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.MANUAL);
		Transaction transaction = session.beginTransaction();
		Query query = session.createSQLQuery(loyaltyRecordSqlStr());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,String>> result = query.list();
		Date date = new Date();
		int i = 0;
		int total = result.size();
		for (Map<String, String> rowDataMap : result) {
			PersonalInformationBackup personalInformationBackup = new PersonalInformationBackup();
			try{
				String id = StringUtils.isBlank(rowDataMap.get("ID")) ? "" : rowDataMap.get("ID");
				String msisdn = StringUtils.isBlank(rowDataMap.get("MSISDN")) ? "" : rowDataMap.get("MSISDN");
				String rocId = StringUtils.isBlank(rowDataMap.get("ROCID")) ? "" : rowDataMap.get("ROCID");
				
//				//複合主鍵
				PersonalInformationBackupId personalInformationBackupId = new PersonalInformationBackupId();
				personalInformationBackupId.setTableName("LOYALTY_RECORD");
				personalInformationBackupId.setTablePk(id);
				personalInformationBackupId.setUuid(UUID.randomUUID().toString());
				
				personalInformationBackup.setId(personalInformationBackupId);
				personalInformationBackup.setMsisdn(AESUtil.encrypt(msisdn, encodeKey));
				personalInformationBackup.setRocId(AESUtil.encrypt(rocId, encodeKey));
				personalInformationBackup.setUpdateDate(date);
				
				
				msisdn = StringUtil.getInstance().getHiddenDataByNum(msisdn);
				rocId = StringUtil.getInstance().getHiddenDataById(rocId);
				
				LoyaltyRecord loyaltyRecord = loyaltyRecordService.get(id);
				loyaltyRecord.setMsisdn(msisdn);
				loyaltyRecord.setRocid(rocId);
				
				
				session.saveOrUpdate(loyaltyRecord);
				session.saveOrUpdate(personalInformationBackup);
			}catch(Exception e){
				log.error(">>>>>>:"+e.getMessage());
				continue;
			}
			i = i + 1;
			if(i == total){
				session.flush();
	            session.clear();
			}else{
				if(i % 500 == 0){
		            session.flush();
		            session.clear();
				}
			}
			
			if(i%1000 == 0){
				System.out.println(">>>>>>>>>>>>處理完" + i + "筆");
			}
			if(i == total){
				System.out.println(">>>>>>>>>>>>總共處理完" + i + "筆");
			}
		}
		transaction.commit();
		log.info("====== LOYALTY_RECORD ENCODE FINISH");
	}
	
	
	/**
	 * 處理MAILING_LIST
	 * */
	public void processMailingList() throws Exception{
		log.info("====== MAILING_LIST ENCODE START");
//		Session session = hibernateTemplate.getSessionFactory().openSession();
//		session.setFlushMode(FlushMode.MANUAL);
//		Transaction transaction = session.beginTransaction();
//		Query query = session.createSQLQuery(loyaltyRecordSqlStr());
//		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//		List<Map<String,String>> result = query.list();
//		Date date = new Date();
//		int i = 0;
//		int total = result.size();
//		for (Map<String, String> rowDataMap : result) {
//			PersonalInformationBackup personalInformationBackup = new PersonalInformationBackup();
//			try{
//				String id = StringUtils.isBlank(rowDataMap.get("ID")) ? "" : rowDataMap.get("ID");
//				String msisdn = StringUtils.isBlank(rowDataMap.get("MSISDN")) ? "" : rowDataMap.get("MSISDN");
//				String rocId = StringUtils.isBlank(rowDataMap.get("ROCID")) ? "" : rowDataMap.get("ROCID");
//				
////				//複合主鍵
//				PersonalInformationBackupId personalInformationBackupId = new PersonalInformationBackupId();
//				personalInformationBackupId.setTableName("LOYALTY_RECORD");
//				personalInformationBackupId.setTablePk(id);
//				personalInformationBackupId.setUuid(UUID.randomUUID().toString());
//				
//				personalInformationBackup.setId(personalInformationBackupId);
//				personalInformationBackup.setMsisdn(AESUtil.encrypt(msisdn, encodeKey));
//				personalInformationBackup.setRocId(AESUtil.encrypt(rocId, encodeKey));
//				personalInformationBackup.setUpdateDate(date);
//				
//				
//				msisdn = StringUtil.getInstance().getHiddenDataByNum(msisdn);
//				rocId = StringUtil.getInstance().getHiddenDataById(rocId);
//				
//				LoyaltyRecord loyaltyRecord = loyaltyRecordService.get(id);
//				loyaltyRecord.setMsisdn(msisdn);
//				loyaltyRecord.setRocid(rocId);
//				
//				
//				session.saveOrUpdate(loyaltyRecord);
//				session.saveOrUpdate(personalInformationBackup);
//			}catch(Exception e){
//				log.error(">>>>>>:"+e.getMessage());
//				continue;
//			}
//			i = i + 1;
//			if(i == total){
//				session.flush();
//	            session.clear();
//			}else{
//				if(i % 500 == 0){
//		            session.flush();
//		            session.clear();
//				}
//			}
//			
//			if(i%1000 == 0){
//				System.out.println(">>>>>>>>>>>>處理完" + i + "筆");
//				transaction.commit();
//				transaction.begin();
//			}
//			if(i == total){
//				System.out.println(">>>>>>>>>>>>總共處理完" + i + "筆");
//				transaction.commit();
//			}
//		}
//		
//		log.info("====== MAILING_LIST ENCODE FINISH");
	}
	
	/**
	 * 處理NBA_CO_MASTER
	 * */
	public void processNbaCoMaster() throws Exception{
		log.info("====== NBA_CO_MASTER ENCODE START");
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.MANUAL);
		Transaction transaction = session.beginTransaction();
		Query query = session.createSQLQuery(nbaCoMasterSqlStr());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,String>> result = query.list();
		Date date = new Date();
		int i = 0;
		int total = result.size();
		for (Map<String, String> rowDataMap : result) {
			PersonalInformationBackup personalInformationBackup = new PersonalInformationBackup();
			try{
				String cono = StringUtils.isBlank(rowDataMap.get("CONO")) ? "" : rowDataMap.get("CONO");
				//使用者 
				String userName = StringUtils.isBlank(rowDataMap.get("USER_NAME")) ? "" : rowDataMap.get("USER_NAME");
				//身分證/健保卡
				String rocId = StringUtils.isBlank(rowDataMap.get("ROC_ID")) ? "" : rowDataMap.get("ROC_ID");
				String mail = StringUtils.isBlank(rowDataMap.get("EMAIL")) ? "" : rowDataMap.get("EMAIL");
				//送貨聯絡電話
				String deliTel = StringUtils.isBlank(rowDataMap.get("DELI_TEL")) ? "" : rowDataMap.get("DELI_TEL");
				//申辦人聯絡電話一
				String tel1 = StringUtils.isBlank(rowDataMap.get("TEL1")) ? "" : rowDataMap.get("TEL1");
				//申辦人聯絡電話二
				String tel2 = StringUtils.isBlank(rowDataMap.get("TTEL2")) ? "" : rowDataMap.get("TTEL2");
				//送貨地址
				String deliAddr = StringUtils.isBlank(rowDataMap.get("DELI_ADDR")) ? "" : rowDataMap.get("DELI_ADDR");
				//生日
				String birthday = StringUtils.isBlank(rowDataMap.get("BIRTHDAY")) ? "" : rowDataMap.get("BIRTHDAY");
				//收件人
				String reccipient = StringUtils.isBlank(rowDataMap.get("RECCIPIENT")) ? "" : rowDataMap.get("RECCIPIENT");
				//帳單地址
				String billAddr = StringUtils.isBlank(rowDataMap.get("BILL_ADDR")) ? "" : rowDataMap.get("BILL_ADDR");
				//戶籍地址
				String pAddr = StringUtils.isBlank(rowDataMap.get("P_ADDR")) ? "" : rowDataMap.get("P_ADDR");
				//收件人
				String deliReceive = StringUtils.isBlank(rowDataMap.get("DELI_RECEIVER")) ? "" : rowDataMap.get("DELI_RECEIVER");
				//收件人信箱
				String deliEmail = StringUtils.isBlank(rowDataMap.get("DELI_EMAIL")) ? "" : rowDataMap.get("DELI_EMAIL");
				//門號
				String msisdn = StringUtils.isBlank(rowDataMap.get("MSISDN")) ? "" : rowDataMap.get("MSISDN");
				//複合主鍵
				PersonalInformationBackupId personalInformationBackupId = new PersonalInformationBackupId();
				personalInformationBackupId.setTableName("NBA_CO_MASTER");
				personalInformationBackupId.setTablePk(cono);
				personalInformationBackupId.setUuid(UUID.randomUUID().toString());
				
				personalInformationBackup.setId(personalInformationBackupId);
				personalInformationBackup.setUserName(AESUtil.encrypt(userName, encodeKey));
				personalInformationBackup.setRocId(AESUtil.encrypt(rocId, encodeKey));
				personalInformationBackup.setEmail(AESUtil.encrypt(mail, encodeKey));
				personalInformationBackup.setDeliTel(AESUtil.encrypt(deliTel, encodeKey));
				personalInformationBackup.setTel1(AESUtil.encrypt(tel1, encodeKey));
				personalInformationBackup.setTtel2(AESUtil.encrypt(tel2, encodeKey));
				personalInformationBackup.setDeliAddr(AESUtil.encrypt(deliAddr, encodeKey));
				personalInformationBackup.setBirthday(AESUtil.encrypt(birthday, encodeKey));
				personalInformationBackup.setReccipient(AESUtil.encrypt(reccipient, encodeKey));
				personalInformationBackup.setBillAddr(AESUtil.encrypt(billAddr, encodeKey));
				personalInformationBackup.setPAddr(AESUtil.encrypt(pAddr, encodeKey));
				personalInformationBackup.setDeliReceiver(AESUtil.encrypt(deliReceive, encodeKey));
				personalInformationBackup.setDeliEmail(AESUtil.encrypt(deliEmail, encodeKey));
				personalInformationBackup.setMsisdn(AESUtil.encrypt(msisdn, encodeKey));
				personalInformationBackup.setUpdateDate(date);
				
				userName = StringUtil.getInstance().getHiddenDataByName(userName);
				rocId = StringUtil.getInstance().getHiddenDataById(rocId);
				mail= StringUtil.getInstance().getHiddenDataByEmail(mail);
				deliTel = StringUtil.getInstance().getHiddenDataByNum(deliTel);
				tel1 = StringUtil.getInstance().getHiddenDataByNum(tel1);
				tel2 = StringUtil.getInstance().getHiddenDataByNum(tel2);
				deliAddr = StringUtil.getInstance().getHiddenDataByAddr(deliAddr);
				birthday = StringUtil.getInstance().getHiddenDataByBirthday(birthday);
				reccipient = StringUtil.getInstance().getHiddenDataByName(reccipient);
				billAddr = StringUtil.getInstance().getHiddenDataByAddr(billAddr);
				pAddr = StringUtil.getInstance().getHiddenDataByAddr(pAddr);
				deliReceive = StringUtil.getInstance().getHiddenDataByName(deliReceive);
				deliEmail = StringUtil.getInstance().getHiddenDataByEmail(deliEmail);
				msisdn = StringUtil.getInstance().getHiddenDataByNum(msisdn);
				
				
				NbaCoMaster nbaCoMaster = nbaCoMasterService.get(cono);
				nbaCoMaster.setUserName(userName);
				nbaCoMaster.setRocId(rocId);
				nbaCoMaster.setEmail(mail);
				nbaCoMaster.setDeliTel(deliTel);
				nbaCoMaster.setTel1(tel1);
				nbaCoMaster.setTtel2(tel2);
				nbaCoMaster.setDeliAddr(deliAddr);
				nbaCoMaster.setBirthday(birthday);
				nbaCoMaster.setReccipient(reccipient);
				nbaCoMaster.setBillAddr(billAddr);
				nbaCoMaster.setPAddr(pAddr);
				nbaCoMaster.setDeliReceiver(deliReceive);
				nbaCoMaster.setDeliEmail(deliEmail);
				nbaCoMaster.setMsisdn(msisdn);
				
				session.saveOrUpdate(nbaCoMaster);
				session.saveOrUpdate(personalInformationBackup);
			}catch(Exception e){
				log.error(">>>>>>:"+e.getMessage());
				continue;
			}
			i = i + 1;
			if(i == total){
				session.flush();
	            session.clear();
			}else{
				if(i % 500 == 0){
		            session.flush();
		            session.clear();
				}
			}
			
			if(i%1000 == 0){
				System.out.println(">>>>>>>>>>>>處理完" + i + "筆");
				 transaction.commit();
				 transaction.begin();
			}
			if(i == total){
				System.out.println(">>>>>>>>>>>>總共處理完" + i + "筆");
				transaction.commit();
			}
		}
		log.info("====== NBA_CO_MASTER ENCODE FINISH");
	}
	
	/**
	 * NBA_NAME_LIST
	 * prod 沒有index
	 * */
	public void processNbaNameList() throws Exception{
		log.info("====== NBA_NAME_LIST ENCODE START");
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.MANUAL);
		Transaction transaction = session.beginTransaction();
		Query query = session.createSQLQuery(nbaNameListSqlStr());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,String>> result = query.list();
		Date date = new Date();
		int i = 0;
		int total = result.size();
		for (Map<String, String> rowDataMap : result) {
			PersonalInformationBackup personalInformationBackup = new PersonalInformationBackup();
			try{
				Object obj = rowDataMap.get("ID");
				BigDecimal bigDecimal = (BigDecimal) obj;
				Long id = bigDecimal.longValue();
				String msisdn = StringUtils.isBlank(rowDataMap.get("MSISDN")) ? "" : rowDataMap.get("MSISDN");
				String rocId = StringUtils.isBlank(rowDataMap.get("ROC_ID")) ? "" : rowDataMap.get("ROC_ID");
				
				
				//複合主鍵
				PersonalInformationBackupId personalInformationBackupId = new PersonalInformationBackupId();
				personalInformationBackupId.setTableName("NBA_NAME_LIST");
				personalInformationBackupId.setTablePk(String.valueOf(id));
				personalInformationBackupId.setUuid(UUID.randomUUID().toString());
				
				personalInformationBackup.setId(personalInformationBackupId);
				personalInformationBackup.setMsisdn(AESUtil.encrypt(msisdn, encodeKey));
				personalInformationBackup.setRocId(AESUtil.encrypt(rocId, encodeKey));
				personalInformationBackup.setUpdateDate(date);
				
				
				msisdn = StringUtil.getInstance().getHiddenDataByNum(msisdn);
				rocId = StringUtil.getInstance().getHiddenDataById(rocId);
				
				NbaNameList nbaNameList = nbaNameListService.get(id);
				nbaNameList.setId(id);
				nbaNameList.setMsisdn(msisdn);
				nbaNameList.setRocId(rocId);
				session.saveOrUpdate(nbaNameList);
				session.saveOrUpdate(personalInformationBackup);
			}catch(Exception e){
				log.error(">>>>>>:"+e.getMessage());
				continue;
			}
			i = i + 1;
			if(i == total){
				session.flush();
	            session.clear();
			}else{
				if(i % 500 == 0){
		            session.flush();
		            session.clear();
				}
			}
			
			if(i%1000 == 0){
				System.out.println(">>>>>>>>>>>>處理完" + i + "筆");
				 transaction.commit();
				 transaction.begin();
			}
			if(i == total){
				System.out.println(">>>>>>>>>>>>總共處理完" + i + "筆");
				transaction.commit();
			}
		}
		log.info("====== NBA_NAME_LIST ENCODE FINISH");
	}
	
	
	/**
	 * OCR_RECORD
	 * */
	public void processOcrRecord() throws Exception{
		
	}
	
	
	private String nbaNameListSqlStr() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append("  ID,  ");
		sql.append("  MSISDN,  ");
		sql.append("  ROC_ID  ");
		sql.append(" FROM NBA_NAME_LIST ");
		sql.append(" WHERE 1=1  ");
//		sql.append(" AND ROWNUM < 2 ");
		return sql.toString();
		
	}
	
	
	private String nbaCoMasterSqlStr() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append("	CONO, ");
		sql.append("	USER_NAME, ");
		sql.append("	ROC_ID, ");
		sql.append("	EMAIL, ");
		sql.append("	DELI_TEL, ");
		sql.append("	TEL1, ");
		sql.append("	TTEL2, ");
		sql.append("	DELI_ADDR, ");
		sql.append("	BIRTHDAY, ");
		sql.append("	RECCIPIENT, ");
		sql.append("	BILL_ADDR, ");
		sql.append("	P_ADDR, ");
		sql.append("	DELI_RECEIVER, ");
		sql.append("	DELI_EMAIL, ");
		sql.append("	MSISDN ");
		sql.append(" FROM NBA_CO_MASTER ");
		sql.append(" WHERE 1=1  ");
//		sql.append(" AND ROWNUM < 2 ");
		sql.append(" AND CO_DATE is not null ");
		sql.append(" AND TO_CHAR(CO_DATE,'yyyy-mm-dd') < TO_CHAR(SYSDATE-120,'yyyy-mm-dd') ");
		sql.append(" AND CONO not in(SELECT TABLE_PK from PERSONAL_INFORMATION_BACKUP where 1=1 and TABLE_NAME='NBA_CO_MASTER' ) ");
		sql.append(" order by cono ");
		
		return sql.toString();
	}
	
	
	
	private String loyaltyRecordSqlStr() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" id, ");
		sql.append(" MSISDN, ");
		sql.append(" ROCID, ");
		sql.append(" CREATE_TIME ");
		sql.append(" from LOYALTY_RECORD    ");
		sql.append(" where 1=1 ");
		sql.append(" AND TO_CHAR(CREATE_TIME,'yyyy-mm-dd') < TO_CHAR(SYSDATE-120,'yyyy-mm-dd') ");
		sql.append(" AND ROWNUM < 2 ");
		return sql.toString();
	}
	
	
	
	
	private String fridayOrderSqlStr() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" id, ");
		sql.append(" CHINESE_NAME, ");
		sql.append(" EMAIL, ");
		sql.append(" MSISDN, ");
		sql.append(" ADDRESS, ");
		sql.append(" MODIFY_TIME ");
		sql.append(" from FRIDAY_ORDER    ");
		sql.append(" where 1=1 ");
		sql.append(" AND ROWNUM <2  ");
//		sql.append(" AND TO_CHAR(MODIFY_TIME,'yyyy-mm-dd') < TO_CHAR(SYSDATE-120,'yyyy-mm-dd')  ");
		return sql.toString();
	}
	
	
	private String crossCooperationSqlStr() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT   ");
		sql.append(" ORDER_NO,  ");
		sql.append(" USER_NAME,  ");
		sql.append(" USER_MOBILE,  ");
		sql.append(" MSISDN,  ");
		sql.append(" CREATE_DATE  ");
		sql.append(" FROM CROSS_COOPERATION  ");
		sql.append(" WHERE 1=1  ");
		sql.append(" AND  1=1  ");
		sql.append(" AND CREATE_DATE is not null ");
		sql.append(" AND ROWNUM <2  ");
//		sql.append(" AND TO_CHAR(CREATE_DATE,'yyyy-mm-dd') < TO_CHAR(SYSDATE-120,'yyyy-mm-dd') ");
		return sql.toString();
	}
	
	private String coMasterSqlStr() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append("	CONO, ");
		sql.append("	USER_NAME, ");
		sql.append("	ROC_ID, ");
		sql.append("	EMAIL, ");
		sql.append("	DELI_TEL, ");
		sql.append("	TEL1, ");
		sql.append("	TTEL2, ");
		sql.append("	DELI_ADDR, ");
		sql.append("	BIRTHDAY, ");
		sql.append("	RECCIPIENT, ");
		sql.append("	BILL_ADDR, ");
		sql.append("	P_ADDR, ");
		sql.append("	DELI_RECEIVER, ");
		sql.append("	DELI_EMAIL, ");
		sql.append("	MSISDN ");
		sql.append(" FROM CO_MASTER ");
		sql.append(" WHERE 1=1  ");
		sql.append(" AND ROWNUM < 2 ");
		sql.append(" AND ACTIVATION_DATE is not null ");
		sql.append(" AND TO_CHAR(ACTIVATION_DATE,'yyyy-mm-dd') < TO_CHAR(SYSDATE-120,'yyyy-mm-dd') ");
		sql.append(" AND CONO not in(SELECT TABLE_PK from PERSONAL_INFORMATION_BACKUP where 1=1 and TABLE_NAME='CO_MASTER' ) ");
		sql.append(" order by cono ");
		
		return sql.toString();
	}
	
	private String tmpCoMasterSqlStr() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append("	CONO, ");
		sql.append("	USER_NAME, ");
		sql.append("	ROC_ID, ");
		sql.append("	EMAIL, ");
		sql.append("	DELI_TEL, ");
		sql.append("	TEL1, ");
		sql.append("	TTEL2, ");
		sql.append("	DELI_ADDR, ");
		sql.append("	BIRTHDAY, ");
		sql.append("	RECCIPIENT, ");
		sql.append("	BILL_ADDR, ");
		sql.append("	P_ADDR, ");
		sql.append("	DELI_RECEIVER, ");
		sql.append("	DELI_EMAIL, ");
		sql.append("	MSISDN ");
		sql.append(" FROM TMP_CO_MASTER ");
		sql.append(" WHERE 1=1 AND ROWNUM <2 ");
		sql.append(" and cono not in(select p.TABLE_PK from PERSONAL_INFORMATION_BACKUP p where 1=1 and p.TABLE_NAME ='TMP_CO_MASTER'  ) ");
		sql.append(" order by cono ");
		
		return sql.toString();
	}
	
	public static void main(String[] args) {
		try {
			ApplicationContext ctx = new SpringApplicationBuilder(SpringbootWebApplication.class).web(WebApplicationType.NONE).run(args);
			EstoreDbHiddenCodeJob estoreDbHiddenCodeJob = ctx.getBean(EstoreDbHiddenCodeJob.class);
			estoreDbHiddenCodeJob.process();
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
