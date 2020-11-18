package com.fet.db.oracle.dao.report;

import java.util.List;
import java.util.Map;

import com.fet.db.oracle.dao.base.IBaseDAO;

public interface ICrossCooperationReportDAO extends IBaseDAO<Object, String> {

	public List<Map<String,String>> findShopeeDailyReport(int days) throws Exception;

}
