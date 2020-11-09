package com.fet.db.oracle.service.CrossCooperation;

import java.util.List;

import com.fet.db.oracle.pojo.CrossCooperation;
import com.fet.db.oracle.service.IBaseService;

public interface ICrossCooperationService extends IBaseService<CrossCooperation, String> {

	public List<CrossCooperation> findShopeeUpdateJobData() throws Exception;

}
