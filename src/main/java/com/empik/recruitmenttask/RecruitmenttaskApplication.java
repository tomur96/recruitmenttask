package com.empik.recruitmenttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RecruitmenttaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmenttaskApplication.class, args);
	}

}
