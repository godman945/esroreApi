package com.fet.db.oracle.service.crossCooperation;

import java.util.List;
import java.util.Map;

import com.fet.db.oracle.pojo.CrossCooperation;
import com.fet.db.oracle.service.base.IBaseService;

public interface ICrossCooperationService extends IBaseService<CrossCooperation, String> {

	// 訂單超時資料(蝦皮合作案) 超時
	public List<CrossCooperation> findShopeeCancelOverTimeData() throws Exception;

	// 取消訂單資料
	public List<Map<String, String>> findCancelOrderDataStatus() throws Exception;
}
