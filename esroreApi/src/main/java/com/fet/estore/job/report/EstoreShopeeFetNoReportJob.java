package com.fet.estore.job.report;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fet.db.oracle.service.report.IFetReportService;
import com.fet.enumerate.EnumFetShopeeFetNoDalityReportColumn;
import com.fet.spring.init.SpringbootWebApplication;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Component
public class EstoreShopeeFetNoReportJob {

	private Logger log = LogManager.getLogger(getClass());

	@Autowired
	IFetReportService crossCooperationReportService;

	@Value("${spring.profiles.active}")
	private String activeEnv;

	@Value("${shopee.daily.report.fetno.mail.to}")
	private String mailTo;

	@Value("${shopee.daily.report.fetno.mail.from}")
	private String mailFrom;

	@Value("${mail.server}")
	private String mailServer;

	private String filename = "遠傳料號日報表";

	private SimpleDateFormat dformat = new SimpleDateFormat("yyyyMMddhhmmss");

	/*
	 * 1.撈取shopee報表
	 */
	@Transactional
	public void process() throws Exception {
		try {

			log.info("========EMAIL EstoreShopeeFetNoReportJob.process() START========");
			log.info(">>>>>> env:" + activeEnv);
			log.info(">>>>>> mailFrom:" + mailFrom);
			log.info(">>>>>> mailTo:" + mailTo);
			log.info(">>>>>> mailServer:" + mailServer);
			log.info(">>>>>> filename:" + filename);

			StringBuilder mailContent = new StringBuilder();

			int index = 0;
			for (EnumFetShopeeFetNoDalityReportColumn enumFetShopeeFetNoDalityReportColumn : EnumFetShopeeFetNoDalityReportColumn
					.values()) {
				if (index == 0) {
					mailContent.append(enumFetShopeeFetNoDalityReportColumn.getName());
				} else {
					mailContent.append(",").append(enumFetShopeeFetNoDalityReportColumn.getName());
				}
				index = index + 1;
			}

			mailContent.append("\n");

			ObjectMapper objectMapper = new ObjectMapper();
			JSONParser jsonParser = new JSONParser();
			List<Map<String, String>> data = crossCooperationReportService.findShopeeFetNoDailyReport();
			for (Map<String, String> rowDataMap : data) {
				index = 0;
				String jsonStr = objectMapper.writeValueAsString(rowDataMap);
				JSONObject dataJson = jsonParser.parse(jsonStr.toString(), JSONObject.class);

				for (EnumFetShopeeFetNoDalityReportColumn enumFetShopeeFetNoDalityReportColumn : EnumFetShopeeFetNoDalityReportColumn
						.values()) {
					if (index == 0) {
						mailContent.append(dataJson.getAsString(enumFetShopeeFetNoDalityReportColumn.getColumn()));
					} else {
						mailContent.append(",")
								.append(dataJson.getAsString(enumFetShopeeFetNoDalityReportColumn.getColumn()));
					}
					index = index + 1;
				}
				mailContent.append("\n");
			}

			Properties props = System.getProperties();
			props.put("mail.host", mailServer);
			props.put("mail.transport.protocol", "smtp");
			Session session = Session.getDefaultInstance(props);

			InternetAddress from = new InternetAddress(mailFrom);

			String[] mailToArr = mailTo.split(",", -1);
			InternetAddress[] to = new InternetAddress[mailToArr.length];
			for (int i = 0; i < mailToArr.length; i++) {
				to[i] = new InternetAddress(mailToArr[i]);
			}

			MimeMessage mimeMessage = new MimeMessage(session);

			mimeMessage.setFrom(from);
			mimeMessage.setRecipients(RecipientType.TO, to);
			mimeMessage.setSubject(MimeUtility.encodeText(filename, "UTF-8", "B"));

			MimeBodyPart attachFilePart = new MimeBodyPart();
			attachFilePart.addHeader("Content-Type", "application/octet-stream; charset=big5");
			attachFilePart.setDataHandler(new DataHandler(new ByteArrayDataSource(
					new ByteArrayInputStream(mailContent.toString().getBytes("big5")), "text/csv")));
			attachFilePart.setFileName(
					MimeUtility.encodeText(filename + "_" + dformat.format(new Date()) + ".csv", "UTF-8", "B"));

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(attachFilePart);

			mimeMessage.setContent(multipart);
			Transport.send(mimeMessage);

			log.info("========EMAIL EstoreShopeeFetNoReportJob.process() END========");
		} catch (Exception e) {
			log.error(e.getMessage());
			log.info("========EMAIL EstoreShopeeFetNoReportJob.process() FAIL END========");
		}
	}

	public static void main(String[] args) {
		try {
			ApplicationContext ctx = new SpringApplicationBuilder(SpringbootWebApplication.class)
					.web(WebApplicationType.NONE).run(args);
			EstoreShopeeFetNoReportJob estoreShopeeReportJob = ctx.getBean(EstoreShopeeFetNoReportJob.class);
			estoreShopeeReportJob.process();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
