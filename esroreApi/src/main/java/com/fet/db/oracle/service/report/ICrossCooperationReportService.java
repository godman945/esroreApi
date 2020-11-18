package com.fet.db.oracle.service.report;

import java.util.List;

//import org.json.JSONObject;

import com.fet.db.oracle.service.base.IBaseService;

public interface ICrossCooperationReportService extends IBaseService<Object, String> {

	// shopee 對帳日報表
	public List<List<String>> findShopeeDailyReport(int days) throws Exception;

}
