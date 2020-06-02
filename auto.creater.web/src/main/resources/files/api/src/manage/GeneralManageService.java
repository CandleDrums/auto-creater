/**
 * @Project [module].api
 * @Package com.cds.[module].api.manage
 * @Class [model]ManageService.java
 * @Date [date]
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds.[module].api.manage;

import java.util.Collection;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cds.[module].api.model.[model];

/**
 * @Description [model_desc]管理
 * @Notes 未填写备注
 * @author auto create
 * @Date [date]
 * @version 1.0
 * @since JDK 1.8
 */
public interface [model]ManageService {

    /**
     * @description 保存
     * @param [model_lowcase]
     * @return
     * @returnType boolean
     * @author auto create
     */
    boolean save([model] [model_lowcase]);

    /**
     * @description 批量保存
     * @param [model_lowcase]s
     * @return
     * @returnType int
     * @author auto create
     */
    int saveAll(List<[model]> [model_lowcase]s);

    /**
     * @description 修改
     * @param [model_lowcase]
     * @return
     * @returnType boolean
     * @author auto create
     */
    boolean modify([model] [model_lowcase]);

    /**
     * @description 删除（逻辑删除，即deleted=1）
     * @param num
     * @return
     * @returnType boolean
     * @author auto create
     */
    boolean delete(String num);

    /**
     * @description 批量删除（逻辑删除，即deleted=1）
     * @param nums
     * @return
     * @returnType int
     * @author auto create
     */
    int deleteAll(Collection<String> nums);
 
}
