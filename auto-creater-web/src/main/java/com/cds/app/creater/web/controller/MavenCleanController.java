/**
 * @Project auto-creater-web
 * @Package com.cds.app.creater.web.controller
 * @Class MavenCleanController.java
 * @Date Jul 17, 2020 11:17:37 AM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cds.auto.creater.service.MavenCleanService;
import com.cds.base.common.result.ResponseResult;
import com.cds.base.util.bean.CheckUtils;

/**
 * @Description TODO 填写描述信息
 * @Notes 未填写备注
 * @author liming
 * @Date Jul 17, 2020 11:17:37 AM
 */
@Controller
@RequestMapping("/mvn")
public class MavenCleanController {
    @Autowired
    private MavenCleanService mavenCleanService;

    @RequestMapping(value = "/index.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(@RequestParam(value = "repPath", required = false) String repPath,
        @RequestParam(value = "junkList", required = false) List<String> junkList, HttpServletRequest request,
        HttpServletResponse response) throws IOException {
        ModelAndView view = new ModelAndView();
        view.setViewName("html/modules/mvn/index");
        if (CheckUtils.isNotEmpty(repPath) && CheckUtils.isNotEmpty(junkList)) {
            List<File> fileList = mavenCleanService.getFileList(repPath, junkList);
            view.addObject("fileList", fileList);
            view.addObject("repPath", repPath);
        }
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/clean.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseResult<Boolean> create(@RequestParam(value = "repPath", required = false) String repPath,
        @RequestParam(value = "junkList", required = false) List<String> junkList, HttpServletRequest request,
        HttpServletResponse response) throws IOException {
        if (CheckUtils.isEmpty(repPath) && CheckUtils.isEmpty(junkList)) {
            mavenCleanService.clean(repPath, junkList);
            return ResponseResult.returnFail(false);
        }
        return ResponseResult.returnSuccess(true);

    }
}
