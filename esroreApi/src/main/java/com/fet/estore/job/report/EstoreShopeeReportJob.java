package com.fet.estore.job.report;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.csvreader.CsvWriter;
import com.fet.db.oracle.service.report.ICrossCooperationReportService;
import com.fet.enumerate.EnumFetShopeeDalityReportColumn;
import com.fet.soft.util.DateUtil;
import com.fet.spring.init.SpringbootWebApplication;

@Component
public class EstoreShopeeReportJob {

	private Logger log = LogManager.getLogger(getClass());

	@Autowired
	ICrossCooperationReportService crossCooperationReportService;
	
	
	@Value("${shopee.daily.report.path}")
	private String dailyReportPath;

	@Value("${shopee.daily.report.day}")
	private int dailyReporDay;
	
	
	@Value("${spring.profiles.active}")
	private String activeEnv;
	
	
	/*
	 * 1.撈取shopee報表
	 * 2.email寄出
	 * */
	@Transactional
	public void process() throws Exception {
		try {
			
			log.info("========EstoreShopeeReportJob.process() START========");
			log.info(">>>>>> env:"+activeEnv);
			log.info(">>>>>> dailyReporDay:"+dailyReporDay);
			
			List<List<String>> returnDataList = crossCooperationReportService.findShopeeDailyReport();
			
			
			String year = DateUtil.getInstance().getYear();
			String month = DateUtil.getInstance().getMonth();
			String day = DateUtil.getInstance().getDay();
			String fileName = "shopee_"+year+month+day+"_job.csv";
			
	        File path = new File(dailyReportPath + year + "/" + month);
	        path.mkdirs();
			
	        FileOutputStream fileOutputStream = new FileOutputStream(path+"/"+fileName);
	        fileOutputStream.write(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF});
            CsvWriter csvWriter = new CsvWriter(fileOutputStream, ',', Charset.forName("utf-8"));
	        
            String[] headers = new String[EnumFetShopeeDalityReportColumn.values().length];
            int i = 0;
            for (EnumFetShopeeDalityReportColumn enumFetShopeeDalityReportColumn : EnumFetShopeeDalityReportColumn.values()) {
            	headers[i] = enumFetShopeeDalityReportColumn.getName();
            	i= i+1;
            }
            csvWriter.writeRecord(headers);
	        
            
            //寫row data
            for (List<String> list : returnDataList) {
            	String[] rowData = new String[list.size()];
				rowData = list.toArray(rowData);
				csvWriter.writeRecord(rowData);
			}
            csvWriter.close();
			log.info("========EstoreShopeeReportJob.process() END========");
		}catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			ApplicationContext ctx = new SpringApplicationBuilder(SpringbootWebApplication.class).web(WebApplicationType.NONE).run(args);
			EstoreShopeeReportJob estoreShopeeReportJob = ctx.getBean(EstoreShopeeReportJob.class);
			estoreShopeeReportJob.process();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}