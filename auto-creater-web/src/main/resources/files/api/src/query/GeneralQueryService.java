/**
 * @Project [module].api
 * @Package com.cds.[module].api.query
 * @Class [model]QueryService.java
 * @Date [date]
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds.[module].api.query;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cds.base.common.page.Page;
import com.cds.base.common.page.PageResult;
import com.cds.[module].api.model.[model];

/**
 * @Description [model_desc]查询
 * @Notes 未填写备注
 * @author auto create
 * @Date [date]
 * @version 1.0
 * @since JDK 1.8
 */
public interface [model]QueryService {

    /**
     * @description 详情
     * @param num
     * @return
     * @returnType [model]
     * @author auto create
     */
    [model] detail(String num);

    /**
     * @description 判断是否存在
     * @param [model_lowcase]
     * @return
     * @returnType boolean
     * @author auto create
     */
    boolean contains([model] [model_lowcase]);

    /**
     * @description 根据属性查询
     * @param [model_lowcase]
     * @return
     * @returnType List<[model]>
     * @author auto create
     */
    List<[model]> queryAll([model] [model_lowcase]);

    /**
     * @description 分页查询
     * @param page
     * @return
     * @returnType PageResult<[model]>
     * @author auto create
     */
    PageResult<[model]> queryPaging(Page<[model]> page);
}
