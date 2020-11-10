package com.fet.db.oracle.service.CrossCooperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.db.oracle.dao.CrossCooperation.ICrossCooperationDAO;
import com.fet.db.oracle.pojo.CrossCooperation;
import com.fet.db.oracle.service.base.BaseService;

@Service
public class CrossCooperationService extends BaseService<CrossCooperation, String> implements ICrossCooperationService {

	@Autowired
	ICrossCooperationDAO crossCooperationDAO;

	public List<CrossCooperation> findShopeeUpdateJobData() throws Exception {
		return crossCooperationDAO.findShopeeUpdateJobData();
	}

}
