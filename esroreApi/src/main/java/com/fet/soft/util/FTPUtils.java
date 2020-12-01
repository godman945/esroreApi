package com.fet.soft.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtils {

	private static final Log log = LogFactory.getLog(FTPUtils.class);

	private static FTPUtils fTPUtils = new FTPUtils();


	public static FTPUtils getInstance() {
		return fTPUtils;
	}

	public FTPClient getFTPClient(String host, String username, String password) {
		FTPClient ftpClient = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.setConnectTimeout(1000 * 30);
			ftpClient.connect(host, 21);
			ftpClient.login(username, password);
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				ftpClient.disconnect();
			} else {
				log.info(">>>>>>LOGIN FTP SUCCESS....");
			}
		} catch (Exception e) {
			log.error(">>>>>>LOGIN FTP FAIL...." + e);
			return null;
		}
		return ftpClient;
	}
}
