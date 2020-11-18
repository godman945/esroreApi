package com.fet.soft.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {

	private static final Log log = LogFactory.getLog(StringUtil.class);
	
	private static StringUtil stringUtil = new StringUtil();

	public static StringUtil getInstance() {
		return stringUtil;
	}

	public String getHiddenData(String data) {
		try{
			if (StringUtils.isNotBlank(data) && data.length() > 1) {
				String start = data.substring(0, 1);
				String end = data.substring(2, data.length());
				return start + "*" + end;
			}
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return null;
	}
}
