package com.fet.alex;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fet.db.oracle.dao.base.BaseDAO;
import com.fet.db.oracle.dao.coMaster.ICoMasterDAO;
import com.fet.db.oracle.pojo.CoMaster;
import com.fet.db.oracle.pojo.CrossCooperation;
import com.fet.db.oracle.service.coMaster.ICoMasterService;
import com.fet.db.oracle.service.crossCooperation.ICrossCooperationService;
import com.fet.spring.init.SpringbootWebApplication;





@Component
public class AlexTest extends BaseDAO{
	
	
	@Autowired
	private ICrossCooperationService crossCooperationService;
	
	@Autowired
	private ICoMasterService coMasterService;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	@Autowired
	private ICoMasterDAO CoMasterDAO;
	
	@Transactional
	public void test() throws Exception{
		
		//修改資料 START
		CoMaster coMaster = coMasterService.get("TG201113000061S");
		
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(new Date());
		rightNow.add(Calendar.DAY_OF_YEAR,-20);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		System.out.println(sdf.format(rightNow.getTime()));
		
		coMaster.setActivationDate(rightNow.getTime());
//		coMaster.setIaStatus("I");
		
//		CrossCooperation crossCooperation = crossCooperationService.get("ALEX-TEST2");
//		crossCooperation
//		crossCooperation.setOrderStatus("");
		
		
//		CoMaster coMaster = coMasterService.get("TG201112000174S");
//		coMaster.setIaStatus("D");
		//修改資料 END
		
		//狀態D取消API START
//		List<Map<String, String>> dataList = crossCooperationService.findCancelOrderDataStatus();
		//狀態D取消API END
		
		
		//超過24小時
//		List<CrossCooperation> crossCooperation = crossCooperationService.findShopeeUpdateJobData();
//		System.out.println(crossCooperation.size());
		
//		//發送狀態API START
//		List<String> coStatusList = new ArrayList<String>();
//		coStatusList.add(EnumFetOrderStatus.FET_TI.getType());
//		coStatusList.add(EnumFetOrderStatus.FET_BD.getType());
//		coStatusList.add(EnumFetOrderStatus.FET_BO.getType());
//		coStatusList.add(EnumFetOrderStatus.FET_TGR.getType());
//		List<Map<String, String>> dataList = coMasterService.findCoMasterOrderDataForApi(coStatusList);
//		System.out.println(">>>>>>>>>>>"+dataList.size());
//		for (Map<String, String> map : dataList) {
//			if(map.get("ORDER_NO").equals("201111PY5N0VFR")
//					|| map.get("ORDER_NO").equals("201111Q7M4M6TJ")
//					|| map.get("ORDER_NO").equals("201111Q858YTBG")
//					|| map.get("ORDER_NO").equals("201111Q8A85Q8T")) {
//				
//				
//				CrossCooperation crossCooperation = crossCooperationService.get(map.get("ORDER_NO"));
//				System.out.println(crossCooperation.getCono());
//				System.out.println(crossCooperation.getOrderStatus());
//				crossCooperation.setOrderStatus("");
//				System.out.println("======");
//				
//			}
//			
//		}
//		//發送狀態API END
		
		
		
		
		
		
		
		
//		List<CrossCooperation> crossCooperationList = crossCooperationService.findShopeeUpdateJobData();
//		for (CrossCooperation crossCooperation : crossCooperationList) {
//			System.out.println(crossCooperation.getOrderNo());
//		}
		
//		List<String> list = new ArrayList<String>();
//		list.add(EnumFetOrderStatus.FET_TI.getType());
//		list.add(EnumFetOrderStatus.FET_BD.getType());
//		list.add(EnumFetOrderStatus.FET_BO.getType());
//		list.add(EnumFetOrderStatus.FET_TGR.getType());
//		List<Map<String, String>> dataList = coMasterService.findCoMasterOrderDataForApi(list);
//		for (Map<String, String> map : dataList) {
//			System.out.println(map);
//		}
//		System.out.println(dataList.size());
	
		
//		CrossCooperation crossCooperation = new CrossCooperation();
//		crossCooperation.setOrderNo("ALEX-TEST3");
//		crossCooperation.setCono("RG200114000034");
//		crossCooperation.setUserName("alex_test");
//		crossCooperation.setModelId("12345");
//		hibernateTemplate.getSessionFactory().getCurrentSession().saveOrUpdate(crossCooperation);
////		
//		List<CrossCooperation> crossCooperationList = crossCooperationService.loadAll();
//		for (CrossCooperation crossCooperationObj : crossCooperationList) {
//			int i = crossCooperationList.indexOf(crossCooperationObj);
//			if(crossCooperationObj.getOrderNo().indexOf("ALEX")>= 0) {
//				System.out.println(crossCooperationObj.getOrderNo());
//				System.out.println(crossCooperationObj.getUserName());
//				System.out.println(crossCooperationObj.getCono());
//				
//				if("ALEX-TEST3".equals(crossCooperationObj.getOrderNo())) {
////					crossCooperationObj.setOrderStatus("BD");
//					crossCooperationObj.setCancelFlag("Y");
//				}
////				CoMaster coMaster = coMasterService.get(crossCooperationObj.getCono());
////				if("RG200114000034".equals(coMaster.getCono())) {
////					coMaster.setCoStatus("BD");
////					coMaster.setProviderName("HTC");
////					coMaster.setShipmentNo("CCC");
////				}
////				crossCooperationObj.setCancelFlag("1");
////				
////				if(i==2) {
////					List g = new ArrayList();
////					
////					g.get(10);
////					
////				}
//			}
//		}
		
//		ALEX-TEST
//		alex_test@@
//		TG191021810136M
//		ALEX-TEST2
//		alex_test
//		TG191021810444M
//		ALEX-TEST3
//		alex_test
//		RG200114000034
		
		
//		
//		
//		sql.append(" SELECT a.order_no, ");
//		sql.append(" m.ia_status,  ");
//		sql.append(" m.co_status  ");
//		sql.append(" FROM   (SELECT c.order_no,  ");
//		sql.append(" c.cono  ");
//		sql.append(" FROM   cross_cooperation c  ");
//		sql.append(" WHERE  1 = 1  ");
//		sql.append("  AND ( cono IS NOT NULL  ");
//		sql.append(" AND cono != 'null' ))a,  ");
//		sql.append(" co_master m  ");
//		sql.append(" WHERE  1 = 1  ");
//		sql.append(" AND m.ia_status != 'C'  ");
//		sql.append(" AND a.cono = m.cono  ");
//		sql.append(" AND m.co_status IN( 'BO', 'BD' )  ");
//			
//		
//		
////		select c.cono from CROSS_COOPERATION c where 1=1 and (cono is not null and  cono !='null')
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select a.order_no,m.IA_STATUS,m.CO_STATUS from (  select c.order_no,c.cono from CROSS_COOPERATION c where 1=1 and (cono is not null and  cono !='null') )a,CO_MASTER m where 1=1 and a.cono = m.cono and m.CO_STATUS in('BO','BD') ");
//		NativeQuery q = hibernateTemplate.getSessionFactory().getCurrentSession().createNativeQuery(sql.toString());
//		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
////		
//		List<Map<String,Object>> map = q.list();
//		System.out.println(map.size());
//		for (Object object : map) {
//			System.out.println(map.indexOf(object));
//			System.out.println(object);
//			if (map.indexOf(object) == 10) {
//				break;
//			}
//		}
		
////		{RECOMMEND_DESC=null, NEW_BILLING_TYPE_ID=7, EBU_RECOM_NAME=null, NP_STATUS=null, USER_ID=null, NP_COMPANY_CODE=null, ID_CARD_TYPE=null, MDM_ACCOUNT_ID=null, EXTEND_EMP_NO=null, VAT_TITLE=null, DELI_AREACODE=114, CO_SUBS_TYPE=G, PROMOTION_ONSALE_DATE=2018-05-17 00:00:00.0, EXTRASERVICE=ADSMS-1;, BIRTHDAY=80/03/07, PREPAID=600, BUNDLE_ID=TG191021810136M, CS_STORE_ADDRESS=null, NDS_ORDER=null, USE_OCR=null, OFFERID=null, ROC_ID=A160436741, CO_PRICE=600, TSS_EXPIRE_DAY=null, CHANNEL=NEIL, SEC_DOC=1, SOASN=null, OFFNET_OFFERID=null, DELI_ALT=2, PROVIDER=null, STORENO=null, ACTIVATION_DATE=null, SEQ=null, CARD_CRC=null, LZIPCODE=null, ADD_ON_MSISDN=null, DELI_TEL=0124567890, SEX=M, SOLD_DATE=null, IS_MULTIPROD=N, EINVOICE_DEVICE_TYPE=null, IS_MAYANN_SEND=null, PREORDER_NO=null, EBU_VAT_NO=null, RECCIPIENT=Tracking Code (All), MDM_DATA_DISABLED=null, SEC_ID=A160436741, USER_NAME=Tracking Code (All), D_OFFERID=3000176346, SKIP_EXTRASERVICE=null, BD_TIME=null, CANCEL=null, CNC_RETURN_DATE=null, TSS_EXTEND=null, IS_EXTEND=N, NPX_ORDER=Y, DELI_REMARK=null, EXTEND_EXPIRE_DATE=null, TX_ID=115693058073982, VAT_ADDRESS=null, CARD_NO=null, PAY_TYPE=C, DELI_TIME=4, RECOMMEND_SOURCE=null, EMAIL=dark_moon@gmail.com, IS_EXPORTED=N, OLD_BILLING_TYPE_ID=null, BUNDLE_IDENTIFIER=null, PROMOTIONCODE=BLD0254-05, INVOICE_TYPE=ELECTRONIC, CNC_DELIVERY_ORDER=N, THREE_2_FOUR=N, COH_TX=999354520380607267", EFORM_RESULT=SUCCESS, CO_STAGE=OI, CNC_DELIVERY_DATE=null, STATUS_CHANGE_DATE=2019-10-22 09:42:46.0, BCS_SMS_TIME=null, LOYALTY_UPGRADE_TYPE=null, CS_PROD_VALUE=0, SHIPMENT_NO=null, LAST_PAYMENT_ANS=null, WAIVE_CONTRACT=N, REFUND_MEMO=null, LAST_PAYMENT=null, VOICEPLAN_ID=VR_3000164055, CHURN_INDEX=null, DELI_ROCID=A160436741, EBU_VAT_TITLE=null, EBU_TYPE=null, MDM_SIX_ID=null, VIRTUAL_ACCT=null, MDM_SUBSCRIBER_ID=null, VAT_NO=null, MVPN_CID=null, SELF_HELPING=N, BS_MAILING_TIME=null, ADD_ON_DELI_ADDR=N, BILL_AREACODE=114, EXCLUSIVE_ORDER=N, SHIP_DATE=null, CS_GET_TYPE=null, CO_CHANGE=null, IGNORE_TWID=N, CS_STORE_NO=null, TRANS_TO_O2O=null, VAT_AREACODE=null, IMEI=null, PORTIN_DATE=null, INVOICE_NO=null, RID=null, ONSALE_PROMO_LIST_ID=8ac0a31c67c5d4ef0167caabed6c003b, KEEP_MSISDN=null, SYNC_MDM=F, IVR_CODE=null, PADDR_AREACODE=114, IS_INVOICE_DEMAND=N, CS_STORE_CVSID=null, EFORM_TRANS_ID=999354520380607267, MDM_CUSTOMER_ID=null, INSTALLMENT_TENOR=null, IS_AUCTION=N, CNC_STATUS=null, FAST_RENEW=null, DELI_RECEIVER=Tracking Code (All), CREDENTIAL_TYPE=null, EINVOICE_ID=null, PROMO_NAME=LINE MOBILE限定_LINE上網299_單門號限12_2%, CO_STATUS=BO, BILL_ADDR=臺北市內湖區瑞光路４６７號, COH_ORDER_ID=2000065709392731, PROBLEM_ORDER_DATE=null, TOKEN=null, MEMO=null, CNC_RETURN_EMPNO=null, CS_STORE_NAME=null, MVPN_DATE=null, TTEL2=0909128589, SUBSCRIBER_ID=null, CONO=TG191021810136M, LADDRESS=null, AGREE_RETURN_SPEEDUP=null, DELI_TYPE=H, IS_IA_UPDATE=null, PROVIDER_URL=null, EBU_RECOM_MSISDN=null, DELI_EMAIL=dark_moon@gmail.com, CNC_ACCEPT_DATE=null, CNC_DELIVERY_EMPNO=null, IS_IPHONE=N, LOYALTY_COMPANY=null, IS_BUNDLE=N, EXTRASERVICE_DONE=null, CNC_ACCEPT_EMPNO=null, BD_SMS_TIME=null, P_ADDR=臺北市內湖區瑞光路４６７號, DELI_TEL1=0909128589, ADD_ON_INVOICE_ADDR=N, CO_TYPE=NC, ACTI_NO=null, BOG_STATUS=BO, SIMCARD_NO=null, CHANGE_CONTRACT=N, TSS_MEMO=null, IS_PREPAYMENT=N, IA_STATUS=I, INFORM_ID_UPLOAD_TIME=null, O2O_ORDER_ID=null, RETURN_STATUS=null, LOYALTY_LEVEL=null, FAKE4G=N, CS_STORE_TEL=null, SUBSCRIBER_NO=null, TSS_ID=null, CS_STORE_CVSNAME=null, NPFLAG=N, WEB_POS_ID=null, TSS_STAUS=TI, FAKE3G=N, OUID=null, IS_BILLINGTYPE_SENT=N, PACK_ID=P191021810136M, TEL1=0124567890, CARD_MMYY=null, IS_EXCEPT_ORDER=null, ADD_ON_RECEIVER=N, ID_CARD_DATE=2019-10-21 17:47:08.0, CS_NAME=null, PRE_SUBSCRIBE_ORDER=N, DONATE_UNIT_ID=25885, MSISDN=0966020103, COMPANYCODE=null, TRANS_TYPE=null, CO_DATE=2019-10-21 17:47:08.0, RECOM_MSISDN=null, ONNET_OFFERID=null, CS_STORE_AREACODE=null, TRANS_TYPE2=null, ARRIVAL_DATE=null, PROVIDER_NAME=null, APP_SOURCE=null, ORDER_CHANNEL=ESTORE, BCS_TIME=null, ACTIVITY_ID=null, SAFLAG=Y, SALES_NO=null, MVPN_VAT_NO=null, DELI_ADDR=臺北市內湖區瑞光路４６７號, CO_ACTION=null, PORTIN_REVISE_DATE=null, PAYMENT_CONSOLIDATION=Y, ID_CARD_CITY=null, EINVOICE_DEVICE_ID=null, ARPB=null, CLICK_ID=null}
////		TG191021810136M
//		RG200114000034  IA_STATUS > Y
//		String sql = "select * from co_master where 1=1 and cono is not null and CO_STATUS in('BO','BD')   and IA_STATUS ='C'  ";
//		NativeQuery q = hibernateTemplate.getSessionFactory().getCurrentSession().createNativeQuery(sql);
//		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//		
//		List map = q.list();
//		
//		System.out.println(">>>>>>>>>"+map.size());
//		for (Object object : map) {
//			System.out.println(map.indexOf(object));
//			System.out.println(object);
//			
//			if(map.indexOf(object) == 10) {
//				break;
//			}
//		}
//		System.out.println(q.list().size());
//		
	} 
	
	public static void main(String[] args) {
		try {
			ApplicationContext ctx = new SpringApplicationBuilder(SpringbootWebApplication.class).web(WebApplicationType.NONE).run(args);
			AlexTest alexTest = ctx.getBean(AlexTest.class);
			alexTest.test();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
