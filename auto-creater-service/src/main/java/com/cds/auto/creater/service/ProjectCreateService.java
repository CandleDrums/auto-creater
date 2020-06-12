/**
 * @Project auto.creater.service
 * @Package com.cds.app.creater.service.project
 * @Class ProjectCreateService.java
 * @Date 2018年3月1日 下午4:44:06
 * @Copyright (c) 2019 CandleDrumS.com All Right Reserved.
 */
package com.cds.auto.creater.service;

import javax.servlet.http.HttpSession;

import com.cds.app.creater.common.model.ProjectCreateParams;

/**
 * @Description TODO 填写描述信息
 * @Notes 未填写备注
 * @author ming.li
 * @Date 2018年3月1日 下午4:44:06
 * @version 1.0
 * @since JDK 1.8
 */
public interface ProjectCreateService {

    /**
     * @description 创建项目目录
     * @param projectName
     * @return
     * @returnType boolean
     * @author ming.li
     */
    boolean createProject(ProjectCreateParams params);
}
