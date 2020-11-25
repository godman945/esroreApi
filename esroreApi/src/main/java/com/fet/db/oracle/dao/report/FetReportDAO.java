package com.fet.db.oracle.dao.report;

import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.fet.db.oracle.dao.base.BaseDAO;

@Repository
public class FetReportDAO extends BaseDAO<Object, String> implements IFetReportDAO {

	@Override
	public List<Map<String,String>> findShopeeDailyReport(int days) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" a.order_no, ");
		sql.append(" a.PCODE, ");
		sql.append(" a.USER_MOBILE, ");
		sql.append(" a.ORDER_TYPE, ");
		sql.append(" a.MSISDN, ");
		sql.append(" a.ONSALE_NAME, ");
		sql.append(" a.PRODUCT_NAME phone_name, ");
		sql.append(" a.FETNO, ");
		sql.append(" a.PREPAYMENT, ");
		sql.append(" a.PRODUCT_PRICE, ");
		sql.append(" a.TOTAL_AMOUNT, ");
		sql.append(" DECODE(a.CO_STATUS, 'ES', '訂單傳送中', ");
		sql.append(" 'TI', '訂單成立', ");
		sql.append(" 'TC', '客服與客戶聯絡中', ");
		sql.append(" 'TGA', '訂單確認完成', ");
		sql.append(" 'BO', '備貨中', ");
		sql.append(" 'BD', '物流人員配送中', ");
		sql.append(" 'BS', '備貨完成待客領取', ");
		sql.append(" 'LHS', '續約客戶簽收完成', ");
		sql.append(" 'GHS', '新啟用客戶簽收完成', ");
		sql.append(" 'SHS', '門市客戶簽收完成', ");
		sql.append(" 'TGR', '新門號/NP啟用完成', ");
		sql.append(" 'TLO', '續約啟用完成', ");
		sql.append(" 'SGR', '新門號/NP啟用完成(門市取貨件)', ");
		sql.append(" 'SLO', '續約啟用完成(門市取貨件)', ");
		sql.append(" 'T7C', '換貨處理中', ");
		sql.append(" 'T7R', '退貨處理中', ");
		sql.append(" 'T7I', '退貨完成', ");
		sql.append(" 'BR', '退貨處理中', ");
		sql.append(" 'BRI', '退貨完成', ");
		sql.append(" 'TOT1', '取消訂單', ");
		sql.append(" 'TOT2', '刪除訂單', ");
		sql.append(" 'TOT3', '無法與用戶聯絡完成', ");
		sql.append(" 'TOB', 'NP失敗原業者退件', ");
		sql.append(" 'BR1', '配送失敗', ");
		sql.append(" 'BR2', '客戶拒收取消訂單', ");
		sql.append(" 'BSN', '門市訂單取消完成', ");
		sql.append(" 'TOC', '退貨完成', ");
		sql.append(" 'TON', '退貨完成', ");
		sql.append(" 'TOP', '更改促銷方案', ");
		sql.append(" a.CO_STATUS) CROSS_COOPERATION_CO_STATUS, ");
		sql.append(" a.CROSS_COOPERATION_CREATE_DATE, ");
		sql.append(" TO_CHAR(m.CO_DATE, 'yyyy-mm-dd hh24:mm ss') master_CO_DATE, ");
		sql.append(" m.USER_NAME, ");
		sql.append(" a.cono, ");
		sql.append(" (SELECT ");
		sql.append(" (CASE ");
		sql.append(" WHEN SUM(d.QUANTITY_APPLIED) < COUNT(d.QUANTITY) THEN '缺貨' ");
		sql.append("  ELSE '有貨' ");
		sql.append(" END) ");
		sql.append(" FROM CO_DETAIL d ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND d.cono = m.cono ");
		sql.append(" AND a.cono = d.cono ");
		sql.append(" AND a.FETNO = d.FET_NO ");
		sql.append(" GROUP BY d.cono, ");
		sql.append(" d.FET_NO) ");
		sql.append("  QUANTITY_FLAG, ");
		sql.append("  DECODE(m.CO_STATUS, 'ES', '訂單傳送中', ");
		sql.append("  'TI', '訂單成立', ");
		sql.append("  'TC', '客服與客戶聯絡中', ");
		sql.append("  'TGA', '訂單確認完成', ");
		sql.append(" 'BO', '備貨中', ");
		sql.append("  'BD', '物流人員配送中', ");
		sql.append("  'BS', '備貨完成待客領取', ");
		sql.append(" 'LHS', '續約客戶簽收完成', ");
		sql.append(" 'GHS', '新啟用客戶簽收完成', ");
		sql.append(" 'SHS', '門市客戶簽收完成', ");
		sql.append(" 'TGR', '新門號/NP啟用完成', ");
		sql.append(" 'TLO', '續約啟用完成', ");
		sql.append(" 'SGR', '新門號/NP啟用完成(門市取貨件)', ");
		sql.append(" 'SLO', '續約啟用完成(門市取貨件)', ");
		sql.append("  'T7C', '換貨處理中', ");
		sql.append("  'T7R', '退貨處理中', ");
		sql.append("  'T7I', '退貨完成', ");
		sql.append("  'BR', '退貨處理中', ");
		sql.append("  'BRI', '退貨完成', ");
		sql.append("  'TOT1', '取消訂單', ");
		sql.append(" 'TOT2', '刪除訂單', ");
		sql.append("  'TOT3', '無法與用戶聯絡完成', ");
		sql.append(" 'TOB', 'NP失敗原業者退件', ");
		sql.append(" 'BR1', '配送失敗', ");
		sql.append(" 'BR2', '客戶拒收取消訂單', ");
		sql.append(" 'BSN', '門市訂單取消完成', ");
		sql.append(" 'TOC', '退貨完成', ");
		sql.append(" 'TON', '退貨完成', ");
		sql.append(" 'TOP', '更改促銷方案', ");
		sql.append(" m.CO_STATUS) master_CO_STATUS, ");
		sql.append("  TO_CHAR(m.ACTIVATION_DATE, 'yyyy-mm-dd hh24:mm ss') master_ACTIVATION_DATE, ");
		sql.append(" DECODE(m.NP_STATUS,'P', '處理中','F','失敗','S','成功',m.NP_STATUS) master_NP_STATUS, ");
		sql.append("  TO_CHAR(m.ACTIVATION_DATE + 10, 'yyyy-mm-dd hh24:mm ss') ACTIVATION_DATE_add_10, ");
		sql.append("  ( ");
		sql.append(" CASE ");
		sql.append("   WHEN TO_CHAR(sysdate, 'yyyy-mm-dd hh24:mm ss') > TO_CHAR(m.ACTIVATION_DATE + 10, 'yyyy-mm-dd hh24:mm ss') THEN 'Yes' ");
		sql.append("   ELSE 'NO' ");
		sql.append("  END ");
		sql.append("  ) master_over_ACTIVATION_DATE, ");
		sql.append(" TO_CHAR(m.co_date, 'yyyy-mm-dd') co_date, ");
		sql.append("  TO_CHAR(sysdate - :days, 'yyyy-mm-dd') sys_date ");
		sql.append(" FROM (SELECT ");
		sql.append("  order_no, ");
		sql.append("  cono, ");
		sql.append(" PRODUCT_NAME, ");
		sql.append(" USER_MOBILE, ");
		sql.append("  ORDER_TYPE, ");
		sql.append("  MSISDN, ");
		sql.append("  ONSALE_NAME, ");
		sql.append(" FETNO, ");
		sql.append("  PREPAYMENT, ");
		sql.append("  PRODUCT_PRICE, ");
		sql.append("  TOTAL_AMOUNT, ");
		sql.append("  CO_STATUS, ");
		sql.append(" TO_CHAR(CREATE_DATE, 'yyyy-mm-dd hh24:mm ss') CROSS_COOPERATION_CREATE_DATE, ");
		sql.append(" USER_NAME, ");
		sql.append("  PCODE ");
		sql.append(" FROM CROSS_COOPERATION ");
		sql.append(" WHERE 1 = 1 ");
		sql.append("  AND cono IS NOT NULL ");
		sql.append(" ) a, ");
		sql.append("  CO_MASTER m ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND m.cono = a.cono ");
		sql.append(" AND (TO_CHAR(m.co_date, 'yyyy-mm-dd') >= TO_CHAR(sysdate - :days, 'yyyy-mm-dd') and TO_CHAR(m.co_date, 'yyyy-mm-dd') < TO_CHAR(sysdate, 'yyyy-mm-dd') ) ");
		sql.append(" ORDER BY m.co_date DESC ");
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createNativeQuery(sql.toString());
		query.setParameter("days", days);
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public List<Map<String, String>> findShopeeFetNoDailyReport() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT  ");
		sql.append("  	b.FET_NO,  ");
		sql.append("  	b.NAME,  ");
		sql.append(" 	b.INVENTORY  ");
		sql.append("  FROM ");
		sql.append("  handset_group a, product b  ");
		sql.append("   WHERE ");
		sql.append("  a.PRODUCT_ID = b.product_id  ");
		sql.append("  AND a.onsale = 'Y'  ");
		sql.append("  ORDER BY ");
		sql.append("  a.PRODUCT_ID ");
		
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createNativeQuery(sql.toString());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

}
