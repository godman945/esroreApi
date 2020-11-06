package com.fet.db.oracle.dao;

import java.io.Serializable;

import java.util.List;

public abstract class BaseDAO<T, PK extends Serializable> implements IBaseDAO<T, PK> {

	public List<T> findAll() {

		return null;
	}

}
