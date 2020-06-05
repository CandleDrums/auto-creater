/**
 * @Project auto-creater-web
 * @Package com.cds.app.creater.web.project
 * @Class DBConnectionController.java
 * @Date Jun 4, 2020 4:29:40 PM
 * @Copyright (c) 2020 CandleDrums.com All Right Reserved.
 */
package com.cds.app.creater.web.project;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cds.app.creater.common.model.DBConnectionVO;
import com.cds.auto.creater.service.DBConnectionService;
import com.cds.base.common.result.ResponseResult;

/**
 * @Description 数据库连接
 * @Notes 未填写备注
 * @author liming
 * @Date Jun 4, 2020 4:29:40 PM
 */
@Controller
@RequestMapping("/db/")
public class DBConnectionController {
    @Autowired
    private DBConnectionService dbConnectionService;

    @PostMapping(value = "/add.htm")
    @ResponseBody
    public ResponseResult<DBConnectionVO> add(DBConnectionVO connection, HttpServletRequest request,
        HttpServletResponse response) throws IOException {
        DBConnectionVO param = new DBConnectionVO();
        param.setHost(connection.getHost());
        param.setPort(connection.getPasswd());
        param.setUserName(connection.getUserName());
        boolean contains = dbConnectionService.contains(param);
        if (contains) {
            return ResponseResult.returnFail(connection, "该配置已存在，请确认");
        }
        DBConnectionVO result = dbConnectionService.save(connection);
        return ResponseResult.returnSuccess(result);
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
