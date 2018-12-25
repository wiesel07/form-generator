package com.wiesel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;


@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication
@EnableDubboConfiguration
// @MapperScan("com.wiesel.**.mapper")
public class FormGeneratorApplication  {

	public static void main(String[] args) {
		SpringApplication.run(FormGeneratorApplication.class, args);
	}


}
