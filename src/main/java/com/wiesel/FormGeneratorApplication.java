package com.wiesel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication
// @MapperScan("com.wiesel.**.mapper")
public class FormGeneratorApplication  {

	public static void main(String[] args) {
		SpringApplication.run(FormGeneratorApplication.class, args);
	}


}
