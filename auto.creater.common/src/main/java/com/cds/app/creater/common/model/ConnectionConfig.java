/**
 * @Project auto.creater.common
 * @Package com.vanilla.app.creater.common.model
 * @Class DBConfig.java
 * @Date Apr 5, 2020 7:04:46 PM
 * @Copyright (c) 2020 CandleDrums.com All Right Reserved.
 */
package com.cds.app.creater.common.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @Description 数据库配置
 * @Notes 未填写备注
 * @author liming
 * @Date Apr 5, 2020 7:04:46 PM
 */
@Data
@Component
@ConfigurationProperties(prefix = "db.config")
public class ConnectionConfig {
    private String name;
    private String host;
    private String userName;
    private String passwd;
    private String port;

    public ConnectionConfig(String host, String port, String userName, String passwd) {
        super();
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.passwd = passwd;
    }

    public ConnectionConfig() {}
}
