package com.WechatBackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages="com.WechatBackEnd.*")
@EnableJpaRepositories(basePackages="com.WechatBackEnd.*")
@EntityScan(basePackages="com.WechatBackEnd.*")

@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})

public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
