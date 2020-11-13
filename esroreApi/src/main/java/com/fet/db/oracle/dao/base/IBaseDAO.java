package com.fet.db.oracle.dao.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;


public interface IBaseDAO<T, PK extends Serializable> {
	
	public PK save(T entity);
	
	public List<T> loadAll();
	
	public void saveOrUpdate(T entity);
	
	public void delete(T entity);
	
	public T get(Serializable id);
}