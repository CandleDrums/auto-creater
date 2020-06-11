/**
 * @Project auto.creater.common
 * @Package com.cds.app.creater.common.model
 * @Class ExampleProjectConfig.java
 * @Date Apr 13, 2020 12:09:32 PM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.common.model;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 示例项目
 * @Notes 未填写备注
 * @author liming
 * @Date Apr 13, 2020 12:09:32 PM
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix = "example")
public class ExampleProjectConfig extends BaseParams {

    private static final long serialVersionUID = 1L;
    private String prefix;
    private List<Map<String, String>> projects;
    private List<String> ignore;

}
