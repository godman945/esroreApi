package com.fet.db.oracle.service.crossCooperation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.db.oracle.dao.crossCooperation.ICrossCooperationDAO;
import com.fet.db.oracle.pojo.CrossCooperation;
import com.fet.db.oracle.service.base.BaseService;

@Service
public class CrossCooperationService extends BaseService<CrossCooperation, String> implements ICrossCooperationService {

	@Autowired
	ICrossCooperationDAO crossCooperationDAO;

	public List<CrossCooperation> findShopeeUpdateJobData() throws Exception {
		return crossCooperationDAO.findShopeeUpdateJobData();
	}

	@Override
	public List<Map<String, String>> findCancelOrderDataStatus() throws Exception {
		return crossCooperationDAO.findCancelOrderDataStatus();
	}

}
