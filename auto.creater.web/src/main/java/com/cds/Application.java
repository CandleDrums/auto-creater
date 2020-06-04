/**
 * @Project auto.creater.stater
 * @Package com.cds.starter.application
 * @Class Application.java
 * @Date 2018年2月28日 下午6:24:13
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description 启动
 * @Notes 未填写备注
 * @author ming.li
 * @Date 2018年2月28日 下午6:24:13
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.cds")
@EnableAutoConfiguration
@MapperScan(basePackages = {"com.cds.app.creater.dal.dao"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
