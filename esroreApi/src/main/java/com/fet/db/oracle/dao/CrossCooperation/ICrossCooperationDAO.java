package com.fet.db.oracle.dao.CrossCooperation;

import java.util.List;

import com.fet.db.oracle.dao.IBaseDAO;
import com.fet.db.oracle.pojo.CrossCooperation;

public interface ICrossCooperationDAO extends IBaseDAO<CrossCooperation, String> {

	public List<CrossCooperation> findShopeeUpdateJobData() throws Exception;

}
