package com.fet.db.oracle.service.base;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T, PK extends Serializable> {
	
	public PK save(T entity);
	
	public void saveOrUpdate(T entity);
	
	public List<T> loadAll();
	
	public void delete(T entity);
	
	public T get(Serializable id);
}
