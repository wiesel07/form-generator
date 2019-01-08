package com.wiesel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication
// @MapperScan("com.wiesel.**.mapper")
public class FormGeneratorApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(FormGeneratorApplication.class, args);
	}

	/**
	 * 兼容war部署
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FormGeneratorApplication.class);
	}	

	

}
