/**
 * @Project auto.creater.common
 * @Package com.cds.app.creater.common.util
 * @Class TypesUtils.java
 * @Date May 29, 2020 3:35:21 PM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.common.util;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cds.app.creater.common.model.TableColumn;
import com.cds.base.util.bean.CheckUtils;
import com.mysql.cj.MysqlType;

/**
 * @Description TODO 填写描述信息
 * @Notes 未填写备注
 * @author liming
 * @Date May 29, 2020 3:35:21 PM
 */
public class TypesUtils {
    private final static Map<Integer, String> map;
    static {
        map = new HashMap<Integer, String>();
        map.put(Types.BOOLEAN, "Boolean");
        map.put(Types.BIT, "Boolean");
        map.put(Types.TINYINT, "Byte");
        map.put(Types.SMALLINT, "Short");
        map.put(Types.INTEGER, "Integer");
        map.put(Types.FLOAT, "Float");
        map.put(Types.DOUBLE, "Double");
        map.put(Types.CHAR, "String");
        map.put(Types.CLOB, "Clob");
        map.put(Types.VARCHAR, "String");
        map.put(Types.LONGVARCHAR, "String");
        map.put(Types.NVARCHAR, "String");
        map.put(Types.NCHAR, "String");
        map.put(Types.NCLOB, "NClob");
        map.put(Types.ARRAY, "Array");
        map.put(Types.BIGINT, "Long");
        map.put(Types.REAL, "BigDecimal");
        map.put(Types.DECIMAL, "BigDecimal");
        map.put(Types.NUMERIC, "BigDecimal");
        map.put(Types.BLOB, "Blob");
        map.put(Types.LONGVARBINARY, "Blob");
        map.put(Types.OTHER, "Object");
        map.put(Types.DATE, "DateOnly");
        map.put(Types.TIME, "Time");
        map.put(Types.TIMESTAMP, "Date");
        map.put(Types.SQLXML, "Sqlxml");
    }

    public static List<String> getAttributeString(TableColumn tableColumn, Set<String> excludeSet) {

        String columnName = tableColumn.getColumnName();
        if (excludeSet.contains(columnName)) {
            return null;
        }
        List<String> attributeList = new ArrayList<String>();
        String dataTypeName = tableColumn.getDataTypeName();
        String remark = tableColumn.getRemark();
        if (CheckUtils.isNotEmpty(remark)) {
            attributeList.add("// " + remark);
        }
        MysqlType type = MysqlType.valueOf(dataTypeName);
        if (type == null) {
            return null;
        }
        String className = type.getClassName();
        className = className.substring(className.lastIndexOf(".") + 1);
        if ("Timestamp".equals(className)) {
            className = "Date";
        }
        attributeList.add("private " + className + " " + ProjectCreateUtil.getModelLowcaseStr(columnName) + ";");
        return attributeList;
    }

}
