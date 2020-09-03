/**
 * @Project auto.creater.web
 * @Package com.cds.app.creater.web.project
 * @Class ProjectCreateController.java
 * @Date 2018年3月1日 下午3:56:02
 * @Copyright (c) 2019 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.web.controller;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cds.app.creater.common.model.ExampleProjectConfig;
import com.cds.app.creater.common.model.ProjectCreateParams;
import com.cds.app.creater.common.model.TableDetail;
import com.cds.app.creater.common.util.DatabaseMetaDateManager;
import com.cds.auto.creater.service.DBConnectionService;
import com.cds.auto.creater.service.ProjectCreateService;
import com.cds.base.common.result.ResponseResult;
import com.cds.base.generator.mybatis.config.DBConnectionConfig;
import com.cds.base.util.bean.CheckUtils;

/**
 * @Description 项目创建
 * @Notes 未填写备注
 * @author ming.li
 * @Date 2018年3月1日 下午3:56:02
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/server")
public class ServerProjectCreateController {

    @Autowired
    private ProjectCreateService projectCreateService;
    @Autowired
    private ExampleProjectConfig exampleProjectConfig;
    @Autowired
    private DBConnectionService dbConnectionService;
    @Autowired
    private DatabaseMetaDateManager databaseMetaDateManager;

    /**
     * @description 首页
     * @return ModelAndView
     */
    @RequestMapping(value = "/index.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView projectIndex(
        @RequestParam(value = "connectionConfigId", required = false) Integer connectionConfigId,
        HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DBConnectionConfig> connectionList = dbConnectionService.queryAll(new DBConnectionConfig());
        ModelAndView view = new ModelAndView();
        view.addObject("connectionList", connectionList);
        view.setViewName("html/modules/server/index");
        // 如果第一次进入
        if (CheckUtils.isEmpty(connectionConfigId)) {
            return view;
        }
        DBConnectionConfig connectionConfig = dbConnectionService.detail(connectionConfigId);
        if (connectionConfig == null) {
            return view;
        }
        view.addObject("connectionConfigId", connectionConfigId);
        DatabaseMetaData databaseMetaData = databaseMetaDateManager.getDatabaseMetaData(connectionConfig);
        if (databaseMetaData == null) {
            view.addObject("error", "连接失败，请确认数据库连接是否配置正确！");
            return view;
        }
        Map<String, List<TableDetail>> allTablesMap = databaseMetaDateManager.getAllTables(databaseMetaData);
        view.addObject("outputPath", exampleProjectConfig.getOutputPath());
        view.addObject("author", exampleProjectConfig.getAuthor());
        view.addObject("port", exampleProjectConfig.getPort());
        view.addObject("packageName", exampleProjectConfig.getPackageName());
        view.setViewName("html/modules/server/index");
        view.addObject("allTablesMap", allTablesMap);
        return view;
    }

    /**
     * @description 项目创建
     * @return ModelAndView
     */
    @ResponseBody
    @RequestMapping(value = "/create.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseResult<Boolean> create(ProjectCreateParams params, HttpServletRequest request,
        HttpServletResponse response) throws IOException {
        if (CheckUtils.isEmpty(params) || CheckUtils.isEmpty(params.getConnectionConfigId())
            || CheckUtils.isEmpty(params) || CheckUtils.isEmpty(params.getDbName())
            || CheckUtils.isEmpty(params.getTableName())) {
            return ResponseResult.returnFail(false, "必填项必须填写！");
        }
        DBConnectionConfig connectionConfig = dbConnectionService.detail(params.getConnectionConfigId());
        if (CheckUtils.isEmpty(params)) {
            return ResponseResult.returnFail(false, "数据库连接失败，请重试！");
        }
        DatabaseMetaData databaseMetaData = databaseMetaDateManager.getDatabaseMetaData(connectionConfig);
        if (CheckUtils.isEmpty(params)) {
            return ResponseResult.returnFail(false, "数据库连接失败，请重试！");
        }
        Map<String, List<TableDetail>> allTablesMap = databaseMetaDateManager.getAllTables(databaseMetaData);
        if (CheckUtils.isEmpty(allTablesMap)) {
            return ResponseResult.returnFail(false, "数据库连接失败，请重试！");
        }
        String dbName = params.getDbName();
        String tableName = params.getTableName();
        // 默认与数据库名相同
        String projectName = dbName;

        if (CheckUtils.isNotEmpty(params.getProjectName())) {
            projectName = params.getProjectName();
        }
        for (TableDetail tableDetail : allTablesMap.get(dbName)) {
            if (tableDetail.getDbName().equals(dbName) && tableDetail.getTableName().equals(tableName)) {
                TableDetail td = databaseMetaDateManager.getTableDetail(databaseMetaData, dbName, tableName);
                td.setRemark(tableDetail.getRemark());
                params.setTableDetail(td);
            }
        }
        params.setProjectName(projectName);
        try {
            projectCreateService.createServerProject(params);
        } catch (Exception e) {
            return ResponseResult.returnFail(false, e.getMessage());
        }

        return ResponseResult.returnSuccess(true);
    }

}
