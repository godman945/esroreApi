package com.fet.db.oracle.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fet.db.oracle.dao.base.BaseDAO;

public abstract class BaseService<T, PK extends Serializable> implements IBaseService<T, PK> {

	@Autowired
	BaseDAO baseDAO;

	public PK save(T entity) {
		return (PK) baseDAO.save(entity);
	}

	public void saveOrUpdate(T entity) {
		baseDAO.saveOrUpdate(entity);
	}

	public List<T> loadAll() {
		return baseDAO.loadAll();
	}
}
