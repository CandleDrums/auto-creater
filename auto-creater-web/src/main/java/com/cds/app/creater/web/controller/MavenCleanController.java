/**
 * @Project auto-creater-web
 * @Package com.cds.app.creater.web.controller
 * @Class MavenCleanController.java
 * @Date Jul 17, 2020 11:17:37 AM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cds.app.creater.common.model.ProjectCreateParams;
import com.cds.base.common.result.ResponseResult;

/**
 * @Description TODO 填写描述信息
 * @Notes 未填写备注
 * @author liming
 * @Date Jul 17, 2020 11:17:37 AM
 */
@Controller
@RequestMapping("/mvn")
public class MavenCleanController {
    @RequestMapping(value = "/index.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView view = new ModelAndView();
        view.setViewName("html/modules/mvn/index");
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/clean.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseResult<Boolean> create(ProjectCreateParams params, HttpServletRequest request,
        HttpServletResponse response) throws IOException {
        ModelAndView view = new ModelAndView();
        view.setViewName("html/modules/mvn/index");
        return ResponseResult.returnSuccess(true);
    }
}
