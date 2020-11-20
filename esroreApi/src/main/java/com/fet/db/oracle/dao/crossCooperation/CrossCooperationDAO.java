package com.fet.db.oracle.dao.crossCooperation;

import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.fet.db.oracle.dao.base.BaseDAO;
import com.fet.db.oracle.pojo.CrossCooperation;

@Repository
public class CrossCooperationDAO extends BaseDAO<CrossCooperation, String> implements ICrossCooperationDAO {

	@Override
	public List<CrossCooperation> findShopeeCancelOverTimeData() throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM ");
		hql.append(" CrossCooperation ");
		hql.append("WHERE 1=1 ");
		hql.append("AND cono is null ");
		hql.append("AND cancelFlag !='Y' ");
		hql.append("AND NVL(orderStatus, ' ') != 'CN24' ");
		
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<Map<String, String>> findCancelOrderDataStatus() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.order_no, ");
		sql.append(" a.CO_STATUS CROSS_COOPERATION_CO_STATUS, ");
		sql.append(" a.ia_status CROSS_COOPERATION_ia_status, ");
		sql.append(" a.order_status, ");
		sql.append(" m.cono, ");
		sql.append(" m.co_status co_master_co_status, ");
		sql.append(" m.ia_status co_master_ia_status ");
		sql.append(" FROM ");
		sql.append(" (SELECT c.order_no, ");
		sql.append(" c.cono, ");
		sql.append(" c.order_status, ");
		sql.append(" c.ia_status, ");
		sql.append(" c.CO_STATUS ");
		sql.append(" FROM CROSS_COOPERATION c ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND cono IS NOT NULL ");
		sql.append(" AND cono <> 'null' )a, ");
		sql.append(" co_master m ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND m.IA_STATUS ='C' ");
		sql.append(" AND a.cono = m.cono ");
		sql.append(" AND NVL(a.CO_STATUS, ' ') <> m.co_status  ");
		
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createNativeQuery(sql.toString());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

}
