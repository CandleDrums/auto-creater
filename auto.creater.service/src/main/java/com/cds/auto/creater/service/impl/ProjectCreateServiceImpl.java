/**
 * @Project auto.creater.service
 * @Package com.cds.app.creater.service.impl
 * @Class ProjectCreateServiceImpl.java
 * @Date 2018年3月1日 下午4:44:48
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds.auto.creater.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jgit.lib.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cds.app.creater.common.model.ConnectionConfig;
import com.cds.app.creater.common.model.ExampleProjectConfig;
import com.cds.app.creater.common.model.ProjectCreateParams;
import com.cds.app.creater.common.model.TableColumn;
import com.cds.app.creater.common.model.TableDetail;
import com.cds.app.creater.common.util.DatabaseMetaDateManager;
import com.cds.app.creater.common.util.GitUtils;
import com.cds.app.creater.common.util.ProjectCreateUtil;
import com.cds.auto.creater.service.ProjectCreateService;
import com.cds.base.util.bean.CheckUtils;
import com.cds.base.util.file.FileUtils;

/**
 * @Description 项目创建
 * @Notes 未填写备注
 * @author ming.li
 * @Date 2018年3月1日 下午4:44:48
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class ProjectCreateServiceImpl implements ProjectCreateService {
    @Autowired
    private ExampleProjectConfig exampleProjectConfig;
    @Autowired
    private ConnectionConfig connectionConfig;

    @Override
    public boolean createPorject(ProjectCreateParams params) {
        // 获取模板项目列表
        Map<String, String> exampleprojectsMap = getExampleProjectMap();
        if (CheckUtils.isEmpty(exampleprojectsMap)) {
            return false;
        }
        // clone模板项目
        Map<String, String> exampleProjectsPathMap = getExampleProjectsPathMap(params, exampleprojectsMap);
        if (CheckUtils.isEmpty(exampleProjectsPathMap)) {
            return false;
        }
        // 获取要创建的表信息
        TableDetail tableDetail = params.getTableDetail();
        if (CheckUtils.isEmpty(tableDetail)) {
            return false;
        }
        // 需要替换的相关内容，可以自己重写
        Map<String, String> replaceMap = ProjectCreateUtil.getReplaceMap(tableDetail, params.getProjectName(),
            params.getAuthor(), params.getPort(), exampleProjectConfig);
        if (CheckUtils.isEmpty(replaceMap)) {
            return false;
        }
        // 开始创建文件
        createFile(exampleProjectsPathMap, tableDetail, replaceMap);

        return true;
    }

    /**
     * @description 获取模板项目列表
     * @return void
     */
    private Map<String, String> getExampleProjectMap() {
        Map<String, String> projectsMap = new HashMap<String, String>();

        List<Map<String, String>> projects = exampleProjectConfig.getProjects();
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
    private Map<String, String> getExampleProjectsPathMap(ProjectCreateParams params, Map<String, String> projectsMap) {
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
    private void createFile(Map<String, String> localPathMap, TableDetail tableDetail, Map<String, String> replaceMap) {
        if (CheckUtils.isEmpty(localPathMap)) {
            return;
        }
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

                ProjectCreateUtil.writeFile(replaceMap, extraAttributes, absolutePath,
                    getOutputPath(absolutePath, replaceMap));
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
            extraAttributes =
                ProjectCreateUtil.getExtraAttributeList(tableDetail, ProjectCreateUtil.getDOExcludeList());
        } else if ("TableNameVO.java".equals(file.getName())) {
            extraAttributes =
                ProjectCreateUtil.getExtraAttributeList(tableDetail, ProjectCreateUtil.getVOExcludeList());
        }
        return extraAttributes;
    }

    /**
     * @description 获取输出目录
     * @return String
     */
    private String getOutputPath(String absolutePath, Map<String, String> replaceMap) {
        String outputPath =
            absolutePath.replaceAll(exampleProjectConfig.getPrefix(), replaceMap.get(exampleProjectConfig.getPrefix()));
        outputPath = outputPath.replaceAll(ProjectCreateUtil.upperFirstLatter(exampleProjectConfig.getPrefix()),
            replaceMap.get(ProjectCreateUtil.upperFirstLatter(exampleProjectConfig.getPrefix())));
        return outputPath.replaceAll("TableName", replaceMap.get("TableName"));
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

    private boolean isGeneral(String dbName, String tableName) {
        if (tableName == null) {
            return false;
        }
        DatabaseMetaDateManager dmdm = new DatabaseMetaDateManager(connectionConfig);
        TableDetail tableDetail = dmdm.getTableDetail(dbName, tableName);
        List<TableColumn> columnList = tableDetail.getColumnList();
        for (TableColumn tableColumn : columnList) {
            if (tableColumn.getColumnName().equals("num")) {
                return true;
            }
        }
        return false;
    }

}
