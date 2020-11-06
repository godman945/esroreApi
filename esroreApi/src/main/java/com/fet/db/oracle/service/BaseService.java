package com.fet.db.oracle.service;

import java.io.Serializable;
import java.util.List;

import com.fet.db.oracle.dao.IBaseDAO;

public abstract class BaseService<T, PK extends Serializable> implements IBaseService<T, PK> {

	protected IBaseDAO<T, PK> dao;

	
}
