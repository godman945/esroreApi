package com.fet.db.oracle.dao.crossCooperation;

import java.util.List;
import java.util.Map;

import com.fet.db.oracle.dao.base.IBaseDAO;
import com.fet.db.oracle.pojo.CrossCooperation;

public interface ICrossCooperationDAO extends IBaseDAO<CrossCooperation, String> {

	public List<CrossCooperation> findShopeeCancelOverTimeData() throws Exception;

	public List<Map<String, String>> findCancelOrderDataStatus() throws Exception;
}
