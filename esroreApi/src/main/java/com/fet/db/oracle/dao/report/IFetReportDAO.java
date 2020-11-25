package com.fet.db.oracle.dao.report;

import java.util.List;
import java.util.Map;

import com.fet.db.oracle.dao.base.IBaseDAO;

public interface IFetReportDAO extends IBaseDAO<Object, String> {

	public List<Map<String,String>> findShopeeDailyReport(int days) throws Exception;
	
	public List<Map<String,String>> findShopeeFetNoDailyReport() throws Exception;

}
