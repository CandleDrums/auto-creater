/**
 * @Project [module].server.starter
 * @Package com.cds.starter.application
 * @Class Application.java
 * @Date [date]
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds.starter.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description Server Start
 * @Notes 未填写备注
 * @author auto create
 * @Date [date]
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.cds")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
