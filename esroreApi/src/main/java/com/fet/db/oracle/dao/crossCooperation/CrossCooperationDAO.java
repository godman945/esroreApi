package com.fet.db.oracle.dao.crossCooperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.query.NativeQuery;
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
		hql.append("AND (cancelFlag <> 'Y' or cancelFlag is null)");
		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> findOrderStatusByType(List<String> coStatusList) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.order_no, ");
		sql.append(" m.ia_status,  ");
		sql.append(" m.co_status  ");
		sql.append(" FROM   (SELECT c.order_no,  ");
		sql.append(" c.cono  ");
		sql.append(" FROM   cross_cooperation c  ");
		sql.append(" WHERE  1 = 1  ");
		sql.append("  AND ( cono IS NOT NULL  ");
		sql.append(" AND cono != 'null' ))a,  ");
		sql.append(" co_master m  ");
		sql.append(" WHERE  1 = 1  ");
		sql.append(" AND m.ia_status != 'C'  ");
		sql.append(" AND a.cono = m.cono  ");
		sql.append(" AND m.co_status IN( :coStatus )  ");
		NativeQuery query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createNativeQuery(sql.toString());
		query.setParameterList("coStatus", coStatusList);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

}
