package com.fet.estore.shopee.api;

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
		
		return "AAAAAA";
	}
}
