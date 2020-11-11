package com.fet.db.oracle.service.coMaster;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.db.oracle.dao.crossCooperation.ICrossCooperationDAO;
import com.fet.db.oracle.pojo.CoMaster;
import com.fet.db.oracle.pojo.CrossCooperation;
import com.fet.db.oracle.service.base.BaseService;

@Service
public class CoMasterService extends BaseService<CoMaster, String> implements ICoMasterService {

	@Autowired
	ICrossCooperationDAO crossCooperationDAO;

	public List<CrossCooperation> findShopeeUpdateJobData() throws Exception {
		return crossCooperationDAO.findShopeeUpdateJobData();
	}

}
