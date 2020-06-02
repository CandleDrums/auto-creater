/**
 * @Project [module].starter
 * @Package com.cds.starter.application
 * @Class Application.java
 * @Date [date]
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds.starter.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description App Start
 * @Notes 未填写备注
 * @author auto create
 * @Date [date]
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.cds")
@EnableAutoConfiguration
@EnableFeignClients(basePackages = "com.cds")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
