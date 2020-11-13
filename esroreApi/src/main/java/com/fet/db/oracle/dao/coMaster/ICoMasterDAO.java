package com.fet.db.oracle.dao.coMaster;

import java.util.List;
import java.util.Map;

import com.fet.db.oracle.dao.base.IBaseDAO;
import com.fet.db.oracle.pojo.CoMaster;

public interface ICoMasterDAO extends IBaseDAO<CoMaster, String> {
	
	public List<Map<String, String>> findCoMasterOrderDataForApi(List<String> coStatusList) throws Exception;
	
	public List<Map<String, String>> findCoMasterOrderCancelDataForApi() throws Exception;
}
