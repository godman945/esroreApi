package com.fet.db.oracle.service;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T, PK extends Serializable> {
	
	public void saveOrUpdate(T entity);
	
	public List<T> loadAll();
}
