package com.fet.soft.util;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestTemplateUtil {
    private static final Log log = LogFactory.getLog(RestTemplateUtil.class);
    
    private static RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
    
    private RestTemplate restTemplate;
	
    public static RestTemplateUtil getInstance() {
		return restTemplateUtil;
	}
    
    public synchronized String doPost(String url,MediaType contentType,Map<String, String> parameter) throws Exception {
    	log.info(">>>>>> START doPost PARAMETER:"+parameter);
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    	HttpEntity<Map<String, String>> entity = new HttpEntity<>(parameter, headers);
    	ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
    	log.info(">>>>>> END doPost RESULT:"+response.getBody().toString());
    	return response.getBody().toString();
    	
    }

	private RestTemplateUtil() {
		try {
			configureClient();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

    
	private void configureClient() throws Exception {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		//ms
		factory.setReadTimeout(5000);
		factory.setConnectTimeout(15000);
		restTemplate = new RestTemplate(factory);
	}


}