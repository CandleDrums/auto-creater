/**
 * @Project auto.creater.common
 * @Package com.cds.app.creater.common.model
 * @Class TableDetail.java
 * @Date Apr 6, 2020 10:20:34 AM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved
 */
package com.cds.app.creater.common.model;

import java.util.List;

import lombok.Data;

/**
 * @Description 表信息
 * @Notes 未填写备注
 * @author liming
 * @Date Apr 6, 2020 10:20:34 AM
 */
@Data
public class TableDetail {
    // 库名
    private String dbName;
    // 表名
    private String tableName;
    // 备注
    private String remark;
    // 列
    private List<TableColumn> columnList;
}
