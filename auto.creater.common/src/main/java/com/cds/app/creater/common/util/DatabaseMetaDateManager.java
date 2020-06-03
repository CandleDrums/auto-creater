/**
 * @Project auto.creater.common
 * @Package com.vanilla.app.creater.common.util
 * @Class DBUtil2.java
 * @Date Apr 5, 2020 11:49:03 AM
 * @Copyright (c) 2020 CandleDrums.com All Right Reserved.
 */
package com.cds.app.creater.common.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.cds.app.creater.common.model.ConnectionConfig;
import com.cds.app.creater.common.model.TableColumn;
import com.cds.app.creater.common.model.TableDetail;
import com.cds.base.util.bean.CheckUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO 填写描述信息
 * @Notes 未填写备注
 * @author liming
 * @Date Apr 5, 2020 11:49:03 AM
 */
@Slf4j
public class DatabaseMetaDateManager {

    private DatabaseMetaData dbMetaData = null;
    private Connection con = null;
    private ConnectionConfig config;

    public DatabaseMetaDateManager(ConnectionConfig config) {
        this.config = config;
        this.initDatabaseMetaData(config.getHost(), config.getPort(), config.getUserName(), config.getPasswd());
    }

    private void initDatabaseMetaData(String host, String port, String userName, String passwd) {
        Properties props = new Properties();

        try {
            if (dbMetaData == null) {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://" + host + ":" + port;
                props.setProperty("user", userName);
                props.setProperty("password", passwd);
                props.setProperty("remarks", "true"); // 设置可以获取remarks信息
                props.setProperty("useInformationSchema", "true");// 设置可以获取tables remarks信息

                con = DriverManager.getConnection(url, props);
                dbMetaData = con.getMetaData();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得数据库的一些相关信息
     */
    public void getDataBaseInformations() {
        try {
            log.info("数据库已知的用户: " + dbMetaData.getUserName());
            log.info("数据库的系统函数的逗号分隔列表: " + dbMetaData.getSystemFunctions());
            log.info("数据库的时间和日期函数的逗号分隔列表: " + dbMetaData.getTimeDateFunctions());
            log.info("数据库的字符串函数的逗号分隔列表: " + dbMetaData.getStringFunctions());
            log.info("数据库供应商用于 'schema' 的首选术语: " + dbMetaData.getSchemaTerm());
            log.info("数据库URL: " + dbMetaData.getURL());
            log.info("是否允许只读:" + dbMetaData.isReadOnly());
            log.info("数据库的产品名称:" + dbMetaData.getDatabaseProductName());
            log.info("数据库的版本:" + dbMetaData.getDatabaseProductVersion());
            log.info("驱动程序的名称:" + dbMetaData.getDriverName());
            log.info("驱动程序的版本:" + dbMetaData.getDriverVersion());
            log.info("数据库中使用的表类型");
            ResultSet rs = dbMetaData.getTableTypes();
            while (rs.next()) {
                log.info(rs.getString(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<TableDetail>> getAllTables() {

        return this.getTableList(null);
    }

    /**
     * 获得该用户下面的所有表
     */
    public Map<String, List<TableDetail>> getTableList(String schemaName) {
        try {
            // table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY",
            // "ALIAS", "SYNONYM".
            String[] types = {"TABLE"};
            ResultSet rs = dbMetaData.getTables(schemaName, "", "%", types);

            ResultSetMetaData rsmd = rs.getMetaData();

            int count = rsmd.getColumnCount();

            String[] name = new String[count];

            for (int i = 0; i < count; i++) {
                name[i] = rsmd.getColumnName(i + 1);
            }
            Map<String, List<TableDetail>> map = new LinkedHashMap<String, List<TableDetail>>();
            List<String> includList = config.getIncludList();
            List<String> excludeList = config.getExcludeList();
            while (rs.next()) {
                String dbName = rs.getString("TABLE_CAT"); // 库名
                if (CheckUtils.isNotEmpty(includList) && !includList.contains(dbName)) {
                    continue;
                }
                if (CheckUtils.isNotEmpty(excludeList) && excludeList.contains(dbName)) {
                    continue;
                }
                String tableName = rs.getString("TABLE_NAME"); // 表名
                // String tableType = rs.getString("TABLE_TYPE"); // 表类型
                String remarks = rs.getString("REMARKS"); // 表备注
                List<TableDetail> tableList = map.get(dbName);
                TableDetail td = new TableDetail();
                td.setTableName(tableName);
                td.setRemark(remarks);
                td.setDbName(dbName);
                if (CheckUtils.isEmpty(tableList)) {
                    tableList = new ArrayList<TableDetail>();
                }
                tableList.add(td);
                map.put(dbName, tableList);
            }
            return map;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得该用户下面的所有视图
     */
    public void getAllViewList(String schemaName) {
        try {
            String[] types = {"VIEW"};
            ResultSet rs = dbMetaData.getTables(null, schemaName, "%", types);
            while (rs.next()) {
                String viewName = rs.getString("TABLE_NAME"); // 视图名
                String viewType = rs.getString("TABLE_TYPE"); // 视图类型
                String remarks = rs.getString("REMARKS"); // 视图备注
                log.info(viewName + "-" + viewType + "-" + remarks);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得数据库中所有方案名称
     */
    public void getAllSchemas() {
        try {
            ResultSet rs = dbMetaData.getSchemas();
            ResultSetMetaData rsmd = rs.getMetaData();

            int count = rsmd.getColumnCount();

            String[] name = new String[count];

            for (int i = 0; i < count; i++) {

                name[i] = rsmd.getColumnName(i + 1);

            }
            while (rs.next()) {
                String tableSchem = rs.getString("TABLE_SCHEM");
                log.info(tableSchem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得表或视图中的所有列信息
     */
    public TableDetail getTableDetail(String schemaName, String tableName) {
        if (schemaName == null) {
            return null;
        }
        try {
            List<TableColumn> columnList = new ArrayList<TableColumn>();
            ResultSet rs = dbMetaData.getColumns(null, schemaName, tableName, null);
            Set<String> columnNameSet = new HashSet<String>();
            while (rs.next()) {
                String tableCat = rs.getString("TABLE_CAT");// 表目录（库名）
                // String tableSchemaName = rs.getString("TABLE_SCHEM");// 表的架构（可能为空）
                String tableName_ = rs.getString("TABLE_NAME");// 表名
                String columnName = rs.getString("COLUMN_NAME");// 列名
                int dataType = rs.getInt("DATA_TYPE"); // 对应的java.sql.Types类型
                String dataTypeName = rs.getString("TYPE_NAME");// java.sql.Types类型 名称
                int columnSize = rs.getInt("COLUMN_SIZE");// 列大小
                int decimalDigits = rs.getInt("DECIMAL_DIGITS");// 小数位数
                int numPrecRadix = rs.getInt("NUM_PREC_RADIX");// 基数（通常是10或2）
                int nullAble = rs.getInt("NULLABLE");// 是否允许为null
                String remark = rs.getString("REMARKS");// 列描述
                String columnDef = rs.getString("COLUMN_DEF");// 默认值
                int sqlDataType = rs.getInt("SQL_DATA_TYPE");// sql数据类型
                int sqlDatetimeSub = rs.getInt("SQL_DATETIME_SUB"); // SQL日期时间分?
                int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH"); // char类型的列中的最大字节数
                int ordinalPosition = rs.getInt("ORDINAL_POSITION"); // 表中列的索引（从1开始）
                // ISO规则用来确定某一列的为空性。 是---如果该参数可以包括空值; 无---如果参数不能包含空值 空字符串---如果参数为空性是未知的
                // String isNullAble = rs.getString("IS_NULLABLE");
                // 指示此列是否是自动递增 是---如果该列是自动递增 无---如果不是自动递增列 空字串---如果不能确定它是否 列是自动递增的参数是未知
                String isAutoincrement = rs.getString("IS_AUTOINCREMENT");
                // 如果已经存在则跳过，主要解决列获取重复问题
                if (columnNameSet.contains(columnName)) {
                    continue;
                }
                TableColumn column = new TableColumn();
                column.setDbName(tableCat);
                column.setTableName(tableName_);
                column.setCharOctetLength(charOctetLength);
                column.setColumnDefaultValue(columnDef);
                column.setColumnName(columnName);
                column.setColumnSize(columnSize);
                column.setDataType(dataType);
                column.setDataTypeName(dataTypeName);
                column.setDecimalDigits(decimalDigits);
                column.setIsAutoincrement(isAutoincrement);
                column.setNullAble(nullAble);
                column.setNumPrecRadix(numPrecRadix);
                column.setOrdinalPosition(ordinalPosition);
                column.setRemark(remark);
                column.setSqlDataType(sqlDataType);
                column.setSqlDatetimeSub(sqlDatetimeSub);
                columnList.add(column);
                columnNameSet.add(columnName);
            }
            TableDetail tableDetail = new TableDetail();
            tableDetail.setDbName(schemaName);
            tableDetail.setTableName(tableName);
            tableDetail.setColumnList(columnList);
            return tableDetail;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<TableDetail> getTableDetailList(List<TableColumn> columnList) {
        if (columnList.size() <= 0) {
            return null;
        }
        List<TableDetail> tableDetailList = new ArrayList<TableDetail>();
        Map<String, TableDetail> tableMap = new HashMap<String, TableDetail>();
        for (TableColumn tableColumn : columnList) {
            TableDetail tableDetail = tableMap.get(tableColumn.getTableName());
            if (tableDetail == null) {
                tableDetail = new TableDetail();
                tableDetail.setDbName(tableColumn.getDbName());
                tableDetail.setTableName(tableColumn.getTableName());
                tableDetail.setColumnList(new ArrayList<TableColumn>());
            }
            List<TableColumn> cl = tableDetail.getColumnList();
            cl.add(tableColumn);
            tableMap.put(tableColumn.getTableName(), tableDetail);
        }
        for (String key : tableMap.keySet()) {
            tableDetailList.add(tableMap.get(key));
        }
        return tableDetailList;

    }

    /**
     * 获得一个表的索引信息
     */
    public void getIndexInfo(String schemaName, String tableName) {
        try {
            ResultSet rs = dbMetaData.getIndexInfo(null, schemaName, tableName, true, true);
            while (rs.next()) {
                boolean nonUnique = rs.getBoolean("NON_UNIQUE");// 非唯一索引(Can index values be non-unique. false when TYPE
                                                                // is tableIndexStatistic )
                String indexQualifier = rs.getString("INDEX_QUALIFIER");// 索引目录（可能为空）
                String indexName = rs.getString("INDEX_NAME");// 索引的名称
                short type = rs.getShort("TYPE");// 索引类型
                short ordinalPosition = rs.getShort("ORDINAL_POSITION");// 在索引列顺序号
                String columnName = rs.getString("COLUMN_NAME");// 列名
                String ascOrDesc = rs.getString("ASC_OR_DESC");// 列排序顺序:升序还是降序
                int cardinality = rs.getInt("CARDINALITY"); // 基数
                log.info(nonUnique + "-" + indexQualifier + "-" + indexName + "-" + type + "-" + ordinalPosition + "-"
                    + columnName + "-" + ascOrDesc + "-" + cardinality);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得一个表的主键信息
     */
    public void getAllPrimaryKeys(String schemaName, String tableName) {
        try {
            ResultSet rs = dbMetaData.getPrimaryKeys(null, schemaName, tableName);
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");// 列名
                short keySeq = rs.getShort("KEY_SEQ");// 序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
                String pkName = rs.getString("PK_NAME"); // 主键名称
                log.info(columnName + "-" + keySeq + "-" + pkName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得一个表的外键信息
     */
    public void getAllExportedKeys(String schemaName, String tableName) {

        try {
            ResultSet rs = dbMetaData.getExportedKeys(null, schemaName, tableName);
            while (rs.next()) {
                String pkTableCat = rs.getString("PKTABLE_CAT");// 主键表的目录（可能为空）
                String pkTableSchem = rs.getString("PKTABLE_SCHEM");// 主键表的架构（可能为空）
                String pkTableName = rs.getString("PKTABLE_NAME");// 主键表名
                String pkColumnName = rs.getString("PKCOLUMN_NAME");// 主键列名
                String fkTableCat = rs.getString("FKTABLE_CAT");// 外键的表的目录（可能为空）出口（可能为null）
                String fkTableSchem = rs.getString("FKTABLE_SCHEM");// 外键表的架构（可能为空）出口（可能为空）
                String fkTableName = rs.getString("FKTABLE_NAME");// 外键表名
                String fkColumnName = rs.getString("FKCOLUMN_NAME"); // 外键列名
                short keySeq = rs.getShort("KEY_SEQ");// 序列号（外键内值1表示第一列的外键，值2代表在第二列的外键）。

                /**
                 * hat happens to foreign key when primary is updated: importedNoAction - do not allow update of primary
                 * key if it has been imported importedKeyCascade - change imported key to agree with primary key update
                 * importedKeySetNull - change imported key to NULL if its primary key has been updated
                 * importedKeySetDefault - change imported key to default values if its primary key has been updated
                 * importedKeyRestrict - same as importedKeyNoAction (for ODBC 2.x compatibility)
                 */
                short updateRule = rs.getShort("UPDATE_RULE");

                /**
                 * What happens to the foreign key when primary is deleted. importedKeyNoAction - do not allow delete of
                 * primary key if it has been imported importedKeyCascade - delete rows that import a deleted key
                 * importedKeySetNull - change imported key to NULL if its primary key has been deleted
                 * importedKeyRestrict - same as importedKeyNoAction (for ODBC 2.x compatibility) importedKeySetDefault
                 * - change imported key to default if its primary key has been deleted
                 */
                short delRule = rs.getShort("DELETE_RULE");
                String fkName = rs.getString("FK_NAME");// 外键的名称（可能为空）
                String pkName = rs.getString("PK_NAME");// 主键的名称（可能为空）

                /**
                 * can the evaluation of foreign key constraints be deferred until commit importedKeyInitiallyDeferred -
                 * see SQL92 for definition importedKeyInitiallyImmediate - see SQL92 for definition
                 * importedKeyNotDeferrable - see SQL92 for definition
                 */
                short deferRability = rs.getShort("DEFERRABILITY");

                log.info(pkTableCat + "-" + pkTableSchem + "-" + pkTableName + "-" + pkColumnName + "-" + fkTableCat
                    + "-" + fkTableSchem + "-" + fkTableName + "-" + fkColumnName + "-" + keySeq + "-" + updateRule
                    + "-" + delRule + "-" + fkName + "-" + pkName + "-" + deferRability);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void colseCon() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}