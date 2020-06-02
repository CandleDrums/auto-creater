/**
 * @Project [module].web
 * @Package com.cds.[module].web.[model_lowcase]
 * @Class LoginController.java
 * @Date [date]
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds.[module].web.[model_lowcase];

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cds.[module].service.[model_lowcase].[model]Service;

/**
 * @Description [model] Controller
 * @Notes 未填写备注
 * @author auto create
 * @Date [date]
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class [model]Controller {

    @Autowired
    private [model]Service [model_lowcase]Service;
    
    /**
     * 示例
     */
    @RequestMapping(value = "/[model_lowcase].htm", method = RequestMethod.GET)
    public ModelAndView [model_lowcase](@RequestParam String [model_lowcase], HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView view = new ModelAndView();
        view.setViewName("[model_lowcase]");
        return view;
    }

}
