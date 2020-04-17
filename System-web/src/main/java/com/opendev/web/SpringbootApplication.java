package com.opendev.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author
 * @Description: 指定项目为springboot，由此类当作程序入口，自动装配 web 依赖的环境
 *
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.opendev")
@MapperScan("com.opendev.dao")
public class SpringbootApplication {
	public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
