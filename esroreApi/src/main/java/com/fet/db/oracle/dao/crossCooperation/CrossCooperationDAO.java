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
	public List<CrossCooperation> findShopeeUpdateJobData() throws Exception {
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
		sql.append("  a.order_status, ");
		sql.append("  a.ia_status, ");
		sql.append("  m.cono, ");
		sql.append("  m.ia_status co_master_ia_status, ");
		sql.append(" m.co_status co_master_co_status ");
		sql.append(" FROM ");
		sql.append(" (SELECT c.order_no, ");
		sql.append("  c.cono, ");
		sql.append(" c.order_status, ");
		sql.append("  c.ia_status ");
		sql.append(" FROM CROSS_COOPERATION c ");
		sql.append("  WHERE 1=1 ");
		sql.append(" AND cono IS NOT NULL ");
		sql.append(" AND cono <> 'null' )a, ");
		sql.append("  co_master m ");
		sql.append(" WHERE 1=1 ");
		sql.append("  AND m.IA_STATUS ='D' ");
		sql.append("  AND a.cono = m.cono ");
		sql.append("  AND NVL(a.order_status, ' ') <> m.ia_status  ");
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createNativeQuery(sql.toString());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println(query.list());
		return query.list();
	}

}