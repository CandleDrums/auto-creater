/**
 * @Project auto.creater.common
 * @Package com.cds.app.creater.common.util
 * @Class java
 * @Date 2018年3月2日 下午2:57:56
 * @Copyright (c) 2019 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cds.app.creater.common.model.ExampleProjectConfig;
import com.cds.app.creater.common.model.ProjectCreateParams;
import com.cds.app.creater.common.model.TableColumn;
import com.cds.app.creater.common.model.TableDetail;
import com.cds.base.util.bean.CheckUtils;
import com.cds.base.util.file.FileUtils;
import com.cds.base.util.misc.DateUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 项目创建util
 * @Notes 未填写备注
 * @author ming.li
 * @Date 2018年3月2日 下午2:57:56
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
public class ProjectCreateUtil {

    public static final char UNDER_LINE = '_';
    public static final char MIDDLE_LINE = '-';

    /**
     * @description 获取要替换的内容
     * @return Map<String,String>
     */
    public static Map<String, String> getReplaceMap(ProjectCreateParams params, TableDetail tableDetail,
        ExampleProjectConfig exampleProjectConfig) {
        String projectName = underlineToCamel(params.getProjectName());
        String upcaseProjectName = upperFirstLatter(params.getProjectName());
        String tableName = tableDetail.getTableName();
        String remark = tableDetail.getRemark();

        Map<String, String> map = new HashMap<String, String>();
        map.put("TableName", getModelStr(tableName));
        map.put("tableName", getModelLowcaseStr(tableName));
        map.put("table_name", tableName);
        map.put("isGeneral", "true");
        map.put(exampleProjectConfig.getPrefix(), projectName);
        map.put(upperFirstLatter(exampleProjectConfig.getPrefix()), upcaseProjectName);
        map.put("9999", params.getPort());
        map.put("com.cds", params.getPackageName());

        map.put("[name]", "");
        map.put("[author]", "autoCreater");
        map.put("[date]", DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HH_MM_SS));
        if (remark != null) {
            int lastIndexOf = remark.lastIndexOf("表");
            if (lastIndexOf > 0) {
                remark = remark.substring(0, remark.length() - 1);
            }
            map.put("[name]", remark);
        }

        if (params.getAuthor() != null) {
            map.put("[author]", params.getAuthor());
        }

        return map;
    }

    /**
     * @description 写入文件
     * @return boolean
     */
    public static boolean writeFile(Map<String, String> map, List<String> extraAttributes, String sourcePath,
        String targetPath) {
        log.info(sourcePath);
        log.info(targetPath);

        List<String> lineList = FileUtils.readFileToList(sourcePath, FileUtils.CHARSET);
        List<String> contentList = new ArrayList<String>();
        for (String line : lineList) {
            String replacedLine = replaceLine(line, map);
            // log.info(replacedLine);
            contentList.add(replacedLine);
            // 在serialVersionUID后开始添加
            if (replacedLine.indexOf("serialVersionUID") > 0 && CheckUtils.isNotEmpty(extraAttributes)) {
                // 开始添加属性
                for (String attribute : extraAttributes) {
                    contentList.add("\t\t" + attribute);
                }
            }
        }
        log.info("创建：" + targetPath);
        return FileUtils.writeFile(targetPath, contentList);
    }

    /**
     * @description 替换占位符
     * @param line
     * @param map
     * @return
     * @returnType String
     * @author ming.li
     */
    private static String replaceLine(String line, Map<String, String> map) {
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String value = map.get(key);
            line = line.replace(key, value);
        }
        return line;
    }

    /**
     * @description 首字母小写Model名称
     * @param tableName
     * @return
     * @returnType String
     * @author ming.li
     */
    public static String getModelLowcaseStr(String tableName) {
        return underlineToCamel(tableName);
    }

    /**
     * @description 首字母大写Model名称
     * @param tableName
     * @return
     * @returnType String
     * @author ming.li
     */
    private static String getModelStr(String tableName) {
        String m = underlineToCamel(tableName);
        return m.substring(0, 1).toUpperCase() + m.substring(1);
    }

    /**
     * @description 驼峰命名
     * @param param
     * @return
     * @returnType String
     * @author ming.li
     */
    private static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDER_LINE || c == MIDDLE_LINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * @description 获取额外添加的属性
     * @return List<String>
     */
    public static List<String> getExtraAttributeList(TableDetail tableDetail, Set<String> excludeList) {
        List<String> result = new ArrayList<String>();
        List<TableColumn> columnList = tableDetail.getColumnList();
        for (TableColumn tableColumn : columnList) {
            if (CheckUtils.isNotEmpty(excludeList)) {
                // 如果排除则跳过
                if (excludeList.contains(tableColumn.getTableName())) {
                    continue;
                }
            }
            List<String> attributeList = TypesUtils.getAttributeString(tableColumn, excludeList);
            if (attributeList == null) {
                continue;
            }
            result.addAll(attributeList);
        }
        return result;
    }

    /**
     * @description 获取DO不需要添加的属性
     * @return Set<String>
     */
    public static Set<String> getDOExcludeList() {
        Set<String> excludeSet = new HashSet<String>();
        excludeSet.add("id");
        excludeSet.add("num");
        excludeSet.add("version");
        excludeSet.add("update_date");
        excludeSet.add("deleted");
        excludeSet.add("create_date");
        return excludeSet;
    }

    /**
     * @description 获取VO不需要添加的属性
     * @return Set<String>
     */
    public static Set<String> getVOExcludeList() {
        Set<String> excludeSet = new HashSet<String>();
        excludeSet.add("id");
        excludeSet.add("version");
        excludeSet.add("deleted");
        return excludeSet;
    }

    /**
     * @description 首字母大写
     * @return String
     */
    public static String upperFirstLatter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
