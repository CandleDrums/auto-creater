/**
 * @Project auto-creater-common
 * @Package com.cds.app.creater.common.constants
 * @Class ProjectCreateParams.java
 * @Date 2018年4月16日 下午3:46:46
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds.app.creater.common.model;

import lombok.Data;

/**
 * @Description 项目创建参数
 * @Notes 未填写备注
 * @author ming.li
 * @Date 2018年4月16日 下午3:46:46
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class ProjectCreateParams {
    private Integer connectionConfigId;
    private String projectName;
    private String dbName;
    private String tableName;
    private boolean pomCreate;
    private String outputPath;
    private String port;
    private String author;
    private TableDetail tableDetail;

}