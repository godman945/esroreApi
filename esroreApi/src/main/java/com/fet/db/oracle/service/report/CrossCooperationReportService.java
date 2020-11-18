package com.fet.db.oracle.service.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fet.db.oracle.dao.report.ICrossCooperationReportDAO;
import com.fet.db.oracle.service.base.BaseService;
import com.fet.enumerate.EnumFetShopeeDalityReportColumn;
import com.fet.soft.util.StringUtil;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Service
public class CrossCooperationReportService extends BaseService<Object, String>
		implements ICrossCooperationReportService {

	@Autowired
	ICrossCooperationReportDAO crossCooperationReportDAO;

	@SuppressWarnings("deprecation")
	public List<List<String>> findShopeeDailyReport(int days) throws Exception {

		List<Map<String, String>> dbDataList = crossCooperationReportDAO.findShopeeDailyReport(days);
		List<List<String>> returnDataList = new ArrayList<List<String>>();

		ObjectMapper objectMapper = new ObjectMapper();
		JSONParser jsonParser = new JSONParser();

		for (Map<String, String> rowDataMap : dbDataList) {
			String jsonStr = objectMapper.writeValueAsString(rowDataMap);
			JSONObject dataJson = jsonParser.parse(jsonStr.toString().getBytes(), JSONObject.class);

			List<String> rowDataList = new ArrayList<String>();
			for (EnumFetShopeeDalityReportColumn enumFetShopeeDalityReportColumn : EnumFetShopeeDalityReportColumn.values()) {
				String data = dataJson.getAsString(enumFetShopeeDalityReportColumn.getColumn()) == null ? "" : dataJson.getAsString(enumFetShopeeDalityReportColumn.getColumn());
				//針對姓名隱碼
				if(enumFetShopeeDalityReportColumn.getColumn().equals(EnumFetShopeeDalityReportColumn.USER_NAME.getColumn())){
					data = StringUtil.getInstance().getHiddenData(data);
				}
				//避免csv 開頭0去掉
				data ="=\""+data+"\"";
				rowDataList.add(data);
			}
			returnDataList.add(rowDataList);
		}
		return returnDataList;
	}
}
