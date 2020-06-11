/**
 * @Project auto-creater-common
 * @Package com.cds.app.creater.common.constants
 * @Class ProjectCreateParams.java
 * @Date 2018年4月16日 下午3:46:46
 * @Copyright (c) 2019 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 项目创建参数
 * @Notes 未填写备注
 * @author ming.li
 * @Date 2018年4月16日 下午3:46:46
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectCreateParams extends BaseParams {

    private static final long serialVersionUID = 1L;
    private Integer connectionConfigId;
    private String projectName;
    private boolean pomCreate;
    private String dbName;
    private String tableName;
    private TableDetail tableDetail;

}