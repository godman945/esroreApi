package com.fet.db.oracle.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public abstract class BaseDAO<T, PK extends Serializable> extends HibernateDaoSupport implements IBaseDAO<T, PK> {


	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	protected Class<T> getMyClass() {
		if (clazz == null) {
			clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return clazz;
	}

	@Autowired
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public SessionFactory  getSessionFactoryOverride() {
		return super.getSessionFactory();
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public PK save(T entity) {
		return (PK) super.getHibernateTemplate().save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		super.getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public List<T> loadAll() {
		return super.getHibernateTemplate().loadAll(getMyClass());
	}

	@Override
	public void delete(T entity) {
		super.getHibernateTemplate().delete(entity);
	}
	
	public T get(Serializable id) {
        return getHibernateTemplate().get(getMyClass(), id);
    }

}
