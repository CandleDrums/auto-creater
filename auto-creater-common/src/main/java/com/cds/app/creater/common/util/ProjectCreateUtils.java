/**
 * @Project auto.creater.common
 * @Package com.cds.app.creater.common.util
 * @Class ProjectCreateUtils
 * @Date 2018年3月2日 下午2:57:56
 * @Copyright (c) 2019 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jgit.lib.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cds.app.creater.common.model.ExampleProjectConfig;
import com.cds.app.creater.common.model.ProjectCreateParams;
import com.cds.app.creater.common.model.TableColumn;
import com.cds.app.creater.common.model.TableDetail;
import com.cds.base.util.bean.CheckUtils;
import com.cds.base.util.file.FileUtils;
import com.cds.base.util.misc.DateUtils;
import com.cds.base.util.system.OSInfoUtils;

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
@Component
public class ProjectCreateUtils {

    public static final char UNDER_LINE = '_';
    public static final char MIDDLE_LINE = '-';
    private static final String MODEL_NAME = "TableName";

    @Autowired
    private ExampleProjectConfig exampleProjectConfig;

    /**
     * @description 获取模板项目列表
     * @return void
     */
    public Map<String, String> getExampleProjectMap(boolean isServer) {
        Map<String, String> projectsMap = new HashMap<String, String>();

        List<Map<String, String>> projects = exampleProjectConfig.getServerProjects();
        if (!isServer) {
            projects = exampleProjectConfig.getAppProjects();
        }
        for (Map<String, String> map : projects) {
            for (String key : map.keySet()) {
                projectsMap.put(key, map.get(key));
            }
        }
        return projectsMap;
    }

    /**
     * @description 获取模板文件
     * @return void
     */
    public Map<String, String> getExampleProjectsPathMap(ProjectCreateParams params, Map<String, String> projectsMap) {
        if (CheckUtils.isEmpty(projectsMap)) {
            return null;
        }
        Map<String, String> templatePathMap = new HashMap<String, String>();
        for (String key : projectsMap.keySet()) {
            try {
                Repository repository =
                    GitUtils.cloneRepository(projectsMap.get(key), params.getOutputPath() + "/" + key,
                        exampleProjectConfig.getUserName(), exampleProjectConfig.getPasswd());
                File workTree = repository.getWorkTree();
                templatePathMap.put(key, workTree.getPath());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return templatePathMap;
    }

    /**
     * @description 创建文件
     * @return void
     */
    public void createFile(Map<String, String> localPathMap, TableDetail tableDetail, Map<String, String> replaceMap,
        String packageName) {
        if (CheckUtils.isEmpty(localPathMap)) {
            return;
        }
        String splitSlash = OSInfoUtils.getSplitSlash();
        String packagePath = packageName.replaceAll("\\.", splitSlash);
        for (String name : localPathMap.keySet()) {
            String path = localPathMap.get(name);
            List<File> fileList = FileUtils.traverseFolder(path);
            for (File file : fileList) {
                String absolutePath = file.getAbsolutePath();
                // 过滤无用文件
                if (isIgnore(absolutePath)) {
                    continue;
                }
                // 如果为JavaBean，是否有要额外添加的动态成员变量，可以根据情况自行修改
                List<String> extraAttributes = getExtraAttributes(tableDetail, file);

                writeFile(replaceMap, extraAttributes, absolutePath,
                    getOutputPath(absolutePath, replaceMap, packagePath, splitSlash));
            }
        }
    }

    /**
     * @description 获取要额外添加的成员变量
     * @return List<String>
     */
    private List<String> getExtraAttributes(TableDetail tableDetail, File file) {
        List<String> extraAttributes = null;
        if ("TableNameDO.java".equals(file.getName())) {
            extraAttributes = getExtraAttributeList(tableDetail, getDOExcludeList());
        } else if ("TableNameVO.java".equals(file.getName())) {
            extraAttributes = getExtraAttributeList(tableDetail, getVOExcludeList());
        }
        return extraAttributes;
    }

    /**
     * @description 获取输出目录
     * @return String
     */
    private String getOutputPath(String absolutePath, Map<String, String> replaceMap, String packagePath,
        String splitSlash) {
        String outputPath =
            absolutePath.replaceAll(exampleProjectConfig.getPrefix(), replaceMap.get(exampleProjectConfig.getPrefix()));
        outputPath = outputPath.replaceAll(upperFirstLatter(exampleProjectConfig.getPrefix()),
            replaceMap.get(upperFirstLatter(exampleProjectConfig.getPrefix())));
        outputPath = outputPath.replaceAll(MODEL_NAME, replaceMap.get(MODEL_NAME));
        return outputPath.replaceAll("com" + splitSlash + "cds", packagePath);
    }

    /**
     * @description 过滤无用文件
     * @return boolean
     */
    private boolean isIgnore(String absolutePath) {
        for (String igFile : exampleProjectConfig.getIgnore()) {
            if (absolutePath.contains(igFile)) {
                return true;
            }
        }
        return false;
    }

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