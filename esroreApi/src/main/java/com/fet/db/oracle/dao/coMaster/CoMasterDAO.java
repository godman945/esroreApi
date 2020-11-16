package com.fet.db.oracle.dao.coMaster;

import java.util.List;
import java.util.Map;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.fet.db.oracle.dao.base.BaseDAO;
import com.fet.db.oracle.pojo.CoMaster;

@Repository
public class CoMasterDAO extends BaseDAO<CoMaster, String> implements ICoMasterDAO {

	@Override
	public List<Map<String, String>> findCoMasterOrderDataForApi(List<String> coStatusList) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.ORDER_NO, ");
		sql.append("  		m.CONO, ");
		sql.append(" 		a.ORDER_STATUS, ");
		sql.append(" 		a.CO_STATUS cross_cooperation_CO_STATUS, ");
		sql.append("  		a.IA_STATUS cross_cooperation_IA_STATUS, ");
		sql.append("  		m.CO_STATUS master_CO_STATUS, ");
		sql.append(" 		m.IA_STATUS master_IA_STATUS, ");
		sql.append("  		a.USER_NAME, ");
		sql.append(" 		m.PROVIDER_NAME, ");
		sql.append("  		m.SHIPMENT_NO, ");
		sql.append("  		m.CS_STORE_NO, ");
		sql.append("  		m.CS_STORE_NAME ");
		sql.append(" FROM ");
		sql.append(" (	SELECT 	c.CONO, ");
		sql.append("  			c.USER_NAME, ");
		sql.append("  			c.CANCEL_FLAG, ");
		sql.append("  			c.ORDER_NO, ");
		sql.append("  			c.ORDER_STATUS, ");
		sql.append("  			c.IA_STATUS, ");
		sql.append(" 			c.CO_STATUS ");
		sql.append(" FROM cross_cooperation c ");
		sql.append(" WHERE 1=1 ");
		sql.append("	AND c.CONO IS NOT NULL ");
		sql.append("	AND NVL(c.CANCEL_FLAG, ' ') <> 'Y' ");
		sql.append("	)a, ");
		sql.append("  	CO_MASTER m ");
		sql.append(" WHERE 1=1 ");
		sql.append("  	AND m.CONO=a.CONO ");
		sql.append(" 	AND NVL(m.IA_STATUS, ' ') <> 'C' ");
		sql.append(" 	AND m.CO_STATUS in(:coStatusList) ");
		sql.append(" 	AND NVL(a.order_status, ' ') <> a.CO_STATUS ");
		
		NativeQuery query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createNativeQuery(sql.toString());
		query.setParameterList("coStatusList", coStatusList);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	public List<Map<String, String>> findCoMasterOrderCancelDataForApi() throws Exception {
		
		return null;
	}

}
