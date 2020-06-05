/**
 * @Project auto.creater.web
 * @Package com.cds.app.creater.web.project
 * @Class ProjectCreateController.java
 * @Date 2018年3月1日 下午3:56:02
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds.app.creater.web.project;

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

import com.cds.app.creater.common.model.DBConnectionVO;
import com.cds.app.creater.common.model.ExampleProjectConfig;
import com.cds.app.creater.common.model.ProjectCreateParams;
import com.cds.app.creater.common.model.TableDetail;
import com.cds.app.creater.common.util.DatabaseMetaDateManager;
import com.cds.auto.creater.service.DBConnectionService;
import com.cds.auto.creater.service.ProjectCreateService;
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
public class ProjectCreateController {

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
    public ModelAndView index(@RequestParam(value = "connectionConfigId", required = false) Integer connectionConfigId,
        HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DBConnectionVO> connectionList = dbConnectionService.queryAll(new DBConnectionVO());
        ModelAndView view = new ModelAndView();
        view.addObject("connectionList", connectionList);
        view.setViewName("index");
        // 如果第一次进入
        if (CheckUtils.isEmpty(connectionConfigId)) {
            return view;
        }
        view.addObject("connectionConfigId", connectionConfigId);

        DBConnectionVO connectionConfig = dbConnectionService.detail(connectionConfigId);
        DatabaseMetaData databaseMetaData = databaseMetaDateManager.getDatabaseMetaData(connectionConfig);
        if (databaseMetaData == null) {
            view.addObject("error", "连接失败，请确认数据库连接是否配置正确！");
            return view;
        }
        Map<String, List<TableDetail>> allTablesMap = databaseMetaDateManager.getAllTables(databaseMetaData);

        view.addObject("allTablesMap", allTablesMap);
        return view;
    }

    /**
     * @description 项目创建
     * @return ModelAndView
     */
    @ResponseBody
    @RequestMapping(value = "/create.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView create(ProjectCreateParams params, HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        if (CheckUtils.isEmpty(params) || CheckUtils.isEmpty(params.getConnectionConfigId())) {
            return index(params.getConnectionConfigId(), request, response);
        }
        DBConnectionVO connectionConfig = dbConnectionService.detail(params.getConnectionConfigId());
        DatabaseMetaData databaseMetaData = databaseMetaDateManager.getDatabaseMetaData(connectionConfig);
        Map<String, List<TableDetail>> allTablesMap = databaseMetaDateManager.getAllTables(databaseMetaData);
        String outputPath = exampleProjectConfig.getOutputPath();
        ModelAndView view = new ModelAndView();
        view.addObject("allTablesMap", allTablesMap);
        view.addObject("outputPath", outputPath);
        view.addObject("author", "自动创建");
        view.addObject("port", "10000");
        view.setViewName("index");
        if (CheckUtils.isEmpty(params) || CheckUtils.isEmpty(params.getDbName())
            || CheckUtils.isEmpty(params.getTableName())) {
            return view;
        }
        String dbName = params.getDbName();
        String tableName = params.getTableName();
        // 默认与数据库名相同
        String projectName = dbName;
        if (CheckUtils.isNotEmpty(params.getOutputPath())) {
            view.addObject("outputPath", params.getOutputPath());
        }
        if (CheckUtils.isNotEmpty(params.getAuthor())) {
            view.addObject("author", params.getAuthor());
        }
        if (CheckUtils.isNotEmpty(params.getPort())) {
            view.addObject("port", params.getPort());
        }
        if (CheckUtils.isNotEmpty(params.getProjectName())) {
            view.addObject("projectName", params.getProjectName());
        }

        if (CheckUtils.isNotEmpty(params.getProjectName())) {
            projectName = params.getProjectName();
        }
        projectName = projectName.replaceAll("_", "-");
        for (TableDetail tableDetail : allTablesMap.get(dbName)) {
            if (tableDetail.getDbName().equals(dbName) && tableDetail.getTableName().equals(tableName)) {
                TableDetail td = databaseMetaDateManager.getTableDetail(databaseMetaData, dbName, tableName);
                td.setRemark(tableDetail.getRemark());
                params.setTableDetail(td);
            }
        }
        params.setProjectName(projectName);
        projectCreateService.createPorject(params);

        return view;
    }

}
