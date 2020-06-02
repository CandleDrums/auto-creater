/**
 * @Project auto.creater.common
 * @Package com.vanilla.app.creater.common.model
 * @Class TableColumns.java
 * @Date Apr 5, 2020 4:35:05 PM
 * @Copyright (c) 2020 CandleDrums.com All Right Reserved.
 */
package com.cds.app.creater.common.model;

import lombok.Data;

/**
 * @Description 列信息
 * @Notes 未填写备注
 * @author liming
 * @Date Apr 5, 2020 4:35:05 PM
 */
@Data
public class TableColumn {
    // 库名
    private String dbName;
    // 表名
    private String tableName;
    // 列名
    private String columnName;
    // 对应的java.sql.Types类型
    private int dataType;
    // java.sql.Types类型 名称
    private String dataTypeName;
    // 列大小
    private int columnSize;
    // 小数位数
    private int decimalDigits;
    // 基数（通常是10或2）
    private int numPrecRadix;
    // 是否允许为null
    private int nullAble;
    // 列描述
    private String remark;
    // 默认值
    private String columnDefaultValue;
    // sql数据类型
    private int sqlDataType;
    // SQL日期时间分?
    private int sqlDatetimeSub;
    // char类型的列中的最大字节数
    private int charOctetLength;
    // 表中列的索引（从1开始）
    private int ordinalPosition;
    // 指示此列是否是自动递增 是---如果该列是自动递增 无---如果不是自动递增列 空字串---如果不能确定它是否 列是自动递增的参数是未知
    private String isAutoincrement;
}
