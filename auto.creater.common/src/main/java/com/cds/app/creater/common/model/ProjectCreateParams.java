/**
 * @Project auto-creater-common
 * @Package com.cds.app.creater.common.constants
 * @Class ProjectCreateParams.java
 * @Date 2018年4月16日 下午3:46:46
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds.app.creater.common.model;

import javax.validation.constraints.NotNull;

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
    @NotNull
    private Integer connectionConfigId;
    private String projectName;
    @NotNull
    private String dbName;
    @NotNull
    private String tableName;
    @NotNull
    private boolean pomCreate;
    @NotNull
    private String outputPath;
    @NotNull
    private String port;
    @NotNull
    private String author;
    private TableDetail tableDetail;

}