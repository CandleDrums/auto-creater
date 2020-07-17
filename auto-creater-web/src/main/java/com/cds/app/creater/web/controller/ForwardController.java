/**
 * @Project auto-creater-web
 * @Package com.cds.app.creater.web.project
 * @Class ForwardController.java
 * @Date Jul 10, 2020 11:48:12 AM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cds.base.util.bean.CheckUtils;

/**
 * @Description TODO 填写描述信息
 * @Notes 未填写备注
 * @author liming
 * @Date Jul 10, 2020 11:48:12 AM
 */
@Controller
public class ForwardController {

    @RequestMapping(value = "/to.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView toPage(@RequestParam(value = "path", required = false) String path) {
        ModelAndView view = new ModelAndView();
        view.setViewName("html/common/welcome");
        if (CheckUtils.isNotEmpty(path)) {
            view.setViewName(path);
        }
        return view;
    }

    @RequestMapping(value = "/welcome.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView view = new ModelAndView();
        view.setViewName("html/common/welcome");
        return view;
    }

    /**
     * @description 首页
     * @return ModelAndView
     */
    @RequestMapping(value = "/index.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView view = new ModelAndView();
        view.setViewName("html/common/welcome");
        return view;
    }

    /**
     * @description 首页
     * @return ModelAndView
     */
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }
}
