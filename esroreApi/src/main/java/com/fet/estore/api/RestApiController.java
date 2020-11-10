package com.fet.estore.api;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestApiController {

	 
	@Value("${alex.msg}")
	private String alexMsg;
	
	

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	@ResponseBody
	public String createProduct() {
		System.out.println("alexMsg:"+alexMsg);
//		{"rtnCode":"00000","rtnMsg":"SUCCESS","restData":{"rtnCode":"00000","rtnMsg":"SUCCESS"}}
		
		
		JSONObject json2 = new JSONObject();
		json2.put("rtnCode", "00000");
		json2.put("rtnMsg", "SUCCESS");
		
		
		JSONObject json = new JSONObject();
		json.put("rtnCode", "00000");
		json.put("rtnMsg", "SUCCESS");
		json.put("restData", json2);
		
		return json.toString();
	}
}
