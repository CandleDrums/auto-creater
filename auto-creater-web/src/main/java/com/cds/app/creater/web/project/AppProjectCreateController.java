/**
 * @Project auto-creater-web
 * @Package com.cds.app.creater.web.project
 * @Class AppProjectCreateController.java
 * @Date Jul 10, 2020 4:05:50 PM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.web.project;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cds.app.creater.common.model.ProjectCreateParams;
import com.cds.auto.creater.service.ProjectCreateService;
import com.cds.base.common.result.ResponseResult;

/**
 * @Description TODO 填写描述信息
 * @Notes 未填写备注
 * @author liming
 * @Date Jul 10, 2020 4:05:50 PM
 */
@Controller
public class AppProjectCreateController {

    @Autowired
    private ProjectCreateService projectCreateService;

    /**
     * @description 首页
     * @return ModelAndView
     */
    @RequestMapping(value = "/app/index.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView view = new ModelAndView();
        view.setViewName("html/modules/app/index");
        return view;
    }

    @RequestMapping(value = "/app/create.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseResult<Boolean> create(ProjectCreateParams params, HttpServletRequest request,
        HttpServletResponse response) throws IOException {
        ModelAndView view = new ModelAndView();
        view.setViewName("html/modules/app/index");
        try {
            projectCreateService.createAppProject(params);
        } catch (Exception e) {
            return ResponseResult.returnFail(false, e.getMessage());
        }

        return ResponseResult.returnSuccess(true);
    }
}
