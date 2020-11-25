package com.fet.db.oracle.service.report;

import java.util.List;
import java.util.Map;

//import org.json.JSONObject;

import com.fet.db.oracle.service.base.IBaseService;

public interface IFetReportService extends IBaseService<Object, String> {

	// shopee 對帳日報表
	public List<List<String>> findShopeeDailyReport(int days) throws Exception;
	// shopee 料號日報表
	public List<Map<String,String>> findShopeeFetNoDailyReport() throws Exception;
}
