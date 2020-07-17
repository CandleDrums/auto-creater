/**
 * @Project auto-creater-web
 * @Package com.cds.app.creater.web.project
 * @Class DBConnectionController.java
 * @Date Jun 4, 2020 4:29:40 PM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.web.controller;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cds.app.creater.common.model.DBConnectionVO;
import com.cds.app.creater.common.util.DatabaseMetaDateManager;
import com.cds.auto.creater.service.DBConnectionService;
import com.cds.base.common.result.ResponseResult;

/**
 * @Description 数据库连接
 * @Notes 未填写备注
 * @author liming
 * @Date Jun 4, 2020 4:29:40 PM
 */
@Controller
@RequestMapping("/db")
public class DBConnectionController {
    @Autowired
    private DBConnectionService dbConnectionService;
    @Autowired
    private DatabaseMetaDateManager databaseMetaDateManager;

    @RequestMapping(value = "/index.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView view = new ModelAndView();
        view.setViewName("html/modules/db/index");
        List<DBConnectionVO> connectionList = dbConnectionService.queryAll(new DBConnectionVO());
        view.addObject("connectionList", connectionList);

        return view;
    }

    @RequestMapping(value = "/toadd.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView view = new ModelAndView();
        view.setViewName("html/modules/db/addConnection");
        return view;
    }

    @PostMapping(value = "/add.htm")
    public ModelAndView add(DBConnectionVO connection, HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        ModelAndView view = new ModelAndView();
        view.setViewName("html/modules/db/addConnection");
        DBConnectionVO param = new DBConnectionVO();
        param.setHost(connection.getHost());
        param.setPort(connection.getPasswd());
        param.setUserName(connection.getUserName());
        boolean contains = dbConnectionService.contains(param);
        if (contains) {
            view.addObject("error", "该配置已存在，请确认");
            view.addObject("connection", connection);
            return view;
        }
        dbConnectionService.save(connection);
        view.addObject("info", "添加成功");
        return view;
    }

    @PostMapping(value = "/test.htm")
    @ResponseBody
    public ResponseResult<Boolean> test(DBConnectionVO connection, HttpServletRequest request,
        HttpServletResponse response) throws IOException {

        DatabaseMetaData databaseMetaData = databaseMetaDateManager.getDatabaseMetaData(connection);
        if (databaseMetaData == null) {
            return ResponseResult.returnFail(false, "连接失败，请检查配置信息！");
        }
        return ResponseResult.returnSuccess(true);
    }

    @PostMapping(value = "/delete.htm")
    @ResponseBody
    public ResponseResult<Boolean> add(@RequestParam(value = "id", required = true) Integer id,
        HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean result = dbConnectionService.delete(id);
        if (!result) {
            return ResponseResult.returnFail(false, "删除失败");
        }
        return ResponseResult.returnSuccess(true);
    }

}
