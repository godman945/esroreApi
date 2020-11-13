package com.fet.db.oracle.service.coMaster;

import java.util.List;
import java.util.Map;

import com.fet.db.oracle.pojo.CoMaster;
import com.fet.db.oracle.service.base.IBaseService;

public interface ICoMasterService extends IBaseService<CoMaster, String> {

	//訂單狀態更新後需呼叫蝦皮API的資料
	List<Map<String, String>> findCoMasterOrderDataForApi(List<String> coStatusList) throws Exception;
	
	//訂單狀態為C資料，發送給蝦皮API取消訂單
	List<Map<String, String>> findCoMasterOrderCancelDataForApi() throws Exception; 
}
