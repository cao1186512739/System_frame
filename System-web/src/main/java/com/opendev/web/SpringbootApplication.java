package com.opendev.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author 86173
 * @Description: 指定项目为springboot，由此类当作程序入口，自动装配 web 依赖的环境
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.opendev.*")
public class SpringbootApplication {
	public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
