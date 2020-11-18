package com.fet.soft.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {

	private static final Log log = LogFactory.getLog(DateUtil.class);

	private static DateUtil dateUtil = new DateUtil();

	public static DateUtil getInstance() {
		return dateUtil;
	}

	// 取得年分
	public String getYear() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			return String.valueOf(calendar.get(Calendar.YEAR));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	// 取得年分
	public String getMonth() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int month = calendar.get(Calendar.MONTH) + 1;
			if(month <10){
				return "0"+String.valueOf(month);
			}else{
				return String.valueOf(month);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	// 取得日
	public String getDay() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			if(day <10){
				return "0"+String.valueOf(day);
			}else{
				return String.valueOf(day);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
}
