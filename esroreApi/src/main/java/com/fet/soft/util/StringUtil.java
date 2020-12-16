package com.fet.soft.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	/**
	 * 姓名隱碼
	 * 姓名字數<=3 第2碼隱藏
	 * 姓名字數>=4 第3及第4碼隱藏
	 * */
	public String getHiddenDataByName(String data) {
		if(StringUtils.isBlank(data)){
			return data;
		}
		
		if(data.length() < 2){
			return data;
		}
		
		StringBuffer sb = new StringBuffer();
		//姓名字數<=3 第2碼隱藏
		if(data.length() <=3 ){
			String newData = sb.append(data.substring(0,1)).append("*").append(data.substring(2, data.length())).toString();
			return newData;
		}
			
		//姓名字數>=4 第3及第4碼隱藏
		if(data.length() >=4 ){
			String lastName = data.substring(2, data.length());
			if(lastName.length() == 2){
				lastName = "**";
			}
			if(lastName.length() > 2){
				lastName = "**"+lastName.substring(2,lastName.length());
			}
			String newData = sb.append(data.substring(0,2)).append(lastName).toString();
			return newData;
		}
		return data;
	}
	
	
	/**
	 * 身分證字號 / 護照號碼/健保卡號碼 .. 隱藏末5碼
	 * 
	 * */
	public String getHiddenDataById(String data) {
		if(StringUtils.isBlank(data)){
			return data;
		}
		
		if(data.length() < 5){
			return data;
		}
		
		StringBuffer lastCodeSb = new StringBuffer();
		String lastCode = data.substring(data.length() -5,data.length());
		for (int i = 0; i < lastCode.length(); i++) {
			lastCodeSb.append("*");
		}
		StringBuffer sb = new StringBuffer();
		data = sb.append(data.substring(0,data.length() -5)).append(lastCodeSb).toString();
		return data;
	}
	
	/**
	 * 行動電話號碼/市話/節費電話號碼 .. 隱中間三碼(5~7)碼
	 * 
	 * */
	public String getHiddenDataByNum(String data) {
		if(StringUtils.isBlank(data)){
			return data;
		}
		
		if(data.length() < 7){
			return data;
		}
		
		StringBuffer sb = new StringBuffer();
		int [] codeRuleArr = {5,6,7};
		for (int i = 0; i < data.length(); i++) {
			boolean needHidden = false;
			for (int index : codeRuleArr) {
				if( i == index){
					needHidden = true;
					break;
				}
			}
			
			if(needHidden){
				sb.append("*");
			}else{
				sb.append(data.charAt(i));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 扣款帳號隱藏末5碼
	 * 除了i-pay BPM與NCCC信用卡對帳，保留前6後4碼
	 * */
	public String getHiddenDataByAccount(String data,String accountType) {
		if(StringUtils.isBlank(data) || StringUtils.isBlank(accountType)){
			return data;
		}
		
		if(data.length() < 5){
			return data;
		}
		
		StringBuffer sb = new StringBuffer();
		int[] accountTypeCodeRule1 ={6,4};
		int codeStartRule1 = 0;
		int[] accountTypeCodeRule2 ={5};
		int codeStartRule2 = 0;
		int dataIntLength = data.replaceAll("(?:-|\\(|\\))", "").length();
		Pattern pattern = Pattern.compile("[0-9]*");
		for (int i = 0; i < data.length(); i++) {
			if(accountType.equals("A01")){
				Matcher isNum = pattern.matcher(String.valueOf(data.charAt(i)));  
				//數字保留前6後4碼
				if(isNum.matches()){
					if(codeStartRule1 < accountTypeCodeRule1[0]){
						sb.append(data.charAt(i));
						codeStartRule1 = codeStartRule1 + 1;
					}else if(i >= data.length() - accountTypeCodeRule1[1]){
						sb.append(data.charAt(i));
					}else{
						sb.append("*");
					}
				}else{
					//非數字不納入隱碼規則
					sb.append(data.charAt(i));
				}
			}else{
				Matcher isNum = pattern.matcher(String.valueOf(data.charAt(i)));  
				//隱藏末5碼
				if(isNum.matches()){
					if(codeStartRule2 < dataIntLength - accountTypeCodeRule2[0]){
						sb.append(data.charAt(i));
					}else{
						sb.append("*");
					}
					codeStartRule2 = codeStartRule2 + 1;
				}else{
					//非數字不納入隱碼規則
					sb.append(data.charAt(i));
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 完整地址
	 * 顯示到路名前兩碼(路/街/道)之後隱碼
	 * */
	public String getHiddenDataByAddr(String data) {
		if(StringUtils.isBlank(data)){
			return data;
		}
		
		String[] codeRule = {"路","街","道"};
		boolean startHiddenFlag = false;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.length(); i++) {
			if(!startHiddenFlag){
				for (String ruleStr : codeRule) {
					if(ruleStr.equals((String.valueOf(data.charAt(i))))){
						startHiddenFlag = true;
						break;
					}
				}
			}
			if(startHiddenFlag){
				sb.append("*");
			}else{
				sb.append(data.charAt(i));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 電子郵件
	 * 只顯示信箱名稱的前2個字元
	 * */
	public String getHiddenDataByEmail(String data) {
		if(StringUtils.isBlank(data) || data.indexOf("@") < 0){
			return data;
		}
		StringBuffer sb = new StringBuffer();
		int hiddenIndex = data.indexOf("@");
		for (int i = 0; i < data.length(); i++) {
			if(i < 2 ){
				sb.append(data.charAt(i));
			}else if(i >= 2 && i < hiddenIndex){
				sb.append("*");
			}else{
				sb.append(data.charAt(i));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 密碼
	 * 全部隱碼
	 * */
	public String getHiddenDataByPWD(String data) {
		if(StringUtils.isBlank(data)){
			return data;
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.length(); i++) {
			sb.append("*");
		}
		return sb.toString();
	}
	
	/**
	 * 隱藏出生的日期
	 * */
	public String getHiddenDataByBirthday(String data) {
		if(StringUtils.isBlank(data)){
			return data;
		}
		int dataIntLength = data.replaceAll("(?:/|月|年|月|日|-)", "").length();
		int hiddenIndex = 0;
		Pattern pattern = Pattern.compile("[0-9]*");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.length(); i++) {
			Matcher isNum = pattern.matcher(String.valueOf(data.charAt(i)));
			
			if(isNum.matches()){
				if(hiddenIndex >= dataIntLength -2){
					sb.append("*");
				}else{
					sb.append(data.charAt(i));
				}
				hiddenIndex = hiddenIndex + 1;
			}
			if(!isNum.matches()){
				sb.append(data.charAt(i));
			}
		}
		return sb.toString();
	}
	
	public static void main(String args[]){
		
		
		System.out.println(StringUtil.getInstance().getHiddenDataByAddr("新北市三峽區成Ｘ路Ｘ號Ｘ樓"));
	}
}
