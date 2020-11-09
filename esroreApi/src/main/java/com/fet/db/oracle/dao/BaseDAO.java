package com.fet.db.oracle.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public abstract class BaseDAO<T, PK extends Serializable> extends HibernateDaoSupport implements IBaseDAO<T, PK> {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	protected Class<T> getMyClass() {
		if (clazz == null) {
			clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return clazz;
	}

//	public HibernateTemplate getHibernateTemplate() {
//		return hibernateTemplate;
//	}

	public void saveOrUpdate(T entity) {
		hibernateTemplate.saveOrUpdate(entity);
	}

	public List<T> loadAll() {
		return hibernateTemplate.loadAll(getMyClass());
	}
}
