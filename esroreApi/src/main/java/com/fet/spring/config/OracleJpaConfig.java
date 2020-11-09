package com.fet.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
public class OracleJpaConfig {

//	@Value("${oracle.jdbc.driver.OracleDriver}")
//	private String oracleDriver;
	
//	@Value("${spring.datasource.url}")
//	private String datasourceUrl;
//	@Value("${spring.datasource.username}")
//	private String username;
//	@Value("${spring.datasource.password}")
//	private String password;

	@Bean
	public DataSource getDataSource() throws Exception{
		
	//	System.out.println(oracleDriver);
		
//		OracleDataSource dataSource = new OracleDataSource();
//		dataSource.setUser("es_dev");
//		dataSource.setPassword("es_dev");
//		dataSource.setURL("jdbc:oracle:thin:@10.64.70.99:1530:estoreT3");
		
		
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setUser("system");
		dataSource.setPassword("admin");
		dataSource.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
		
		dataSource.setImplicitCachingEnabled(true);
		dataSource.setFastConnectionFailoverEnabled(true);
		return dataSource;
//		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//		dataSourceBuilder.driverClassName(oracleDriver);
//		dataSourceBuilder.url(datasourceUrl);
//		dataSourceBuilder.username(username);
//		dataSourceBuilder.password(password);
//		return dataSourceBuilder.build();
	}
}
