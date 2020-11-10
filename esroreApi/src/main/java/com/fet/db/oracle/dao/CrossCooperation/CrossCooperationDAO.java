package com.fet.db.oracle.dao.CrossCooperation;

import java.util.List;

import org.hibernate.query.Query;
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
		
//		StringBuffer sql = new StringBuffer();
//		sql.append(" SELECT ");
//		sql.append("  		a.* ");
//		sql.append(" FROM ");
//		sql.append(" ( ");
//		sql.append(" 	SELECT  ");
//		sql.append(" 		USER_NAME, ");
//		sql.append(" 		CREATE_TIMESTAMP, ");
//		sql.append(" 		(:jobStartTimestamp - CREATE_TIMESTAMP)/(1000 * 60 * 60) hours ");
//		sql.append(" FROM CROSS_COOPERATION ");
//		sql.append(" WHERE 1=1 ");
//		sql.append(" AND  cono is null ");
//		sql.append(" AND (CANCEL_FLAG <> 'Y' or CANCEL_FLAG is null) ");
//		sql.append(" )a ");
//		sql.append(" WHERE 1 = 1 ");
//		sql.append(" AND hours > :validHours ");
//		Query query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
//		query.setParameter("jobStartTimestamp", System.currentTimeMillis());
//		query.setParameter("validHours",24 );
//		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

}
