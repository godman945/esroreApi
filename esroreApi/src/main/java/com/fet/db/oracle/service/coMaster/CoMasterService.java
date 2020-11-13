package com.fet.db.oracle.service.coMaster;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.db.oracle.dao.coMaster.ICoMasterDAO;
import com.fet.db.oracle.pojo.CoMaster;
import com.fet.db.oracle.service.base.BaseService;

@Service
public class CoMasterService extends BaseService<CoMaster, String> implements ICoMasterService {

	@Autowired
	ICoMasterDAO coMasterDAO;

	@Override
	public List<Map<String, String>> findCoMasterOrderDataForApi(List<String> coStatusList) throws Exception {
		return coMasterDAO.findCoMasterOrderDataForApi(coStatusList);
	}

	public List<Map<String, String>> findCoMasterOrderCancelDataForApi() throws Exception {
		return coMasterDAO.findCoMasterOrderCancelDataForApi();
	}


}
