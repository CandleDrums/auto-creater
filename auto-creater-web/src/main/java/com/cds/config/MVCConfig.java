/**
 * @Project auto.creater.web
 * @Package com.cds.config
 * @Class MVCConfig.java
 * @Date Apr 7, 2020 10:29:18 AM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @Description freemarker配置
 * @Notes 未填写备注
 * @author liming
 * @Date Apr 7, 2020 10:29:18 AM
 */
@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter {
    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html;charset=UTF-8");
        // resolver.setContextType("text/html;charset=UTF-8");
        resolver.setRequestContextAttribute("rc");
        return resolver;

    }
}
