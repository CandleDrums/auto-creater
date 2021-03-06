/**
 * @Project auto.creater.service
 * @Package com.cds.app.creater.service.impl
 * @Class ProjectCreateServiceImpl.java
 * @Date 2018年3月1日 下午4:44:48
 * @Copyright (c) 2019 CandleDrumS.com All Right Reserved.
 */
package com.cds.auto.creater.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cds.app.creater.common.model.ExampleProjectConfig;
import com.cds.app.creater.common.model.ProjectCreateParams;
import com.cds.app.creater.common.util.ProjectCreateUtils;
import com.cds.auto.creater.service.ProjectCreateService;
import com.cds.base.module.progress.listener.ProgressListener;
import com.cds.base.util.bean.CheckUtils;

/**
 * @Description 项目创建
 * @Notes 未填写备注
 * @author ming.li
 * @Date 2018年3月1日 下午4:44:48
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class ProjectCreateServiceImpl implements ProjectCreateService {
    @Autowired
    private ExampleProjectConfig exampleProjectConfig;
    @Autowired
    private ProjectCreateUtils projectCreateUtils;

    @Resource
    private ProgressListener progressLocalCacheListener;
    private static final String PROGRESS_NAME = "create_project_progress";

    @Override
    public boolean createServerProject(ProjectCreateParams params) {
        return this.create(params, true);
    }

    @Override
    public boolean createAppProject(ProjectCreateParams params) {
        return this.create(params, false);
    }

    private boolean create(ProjectCreateParams params, boolean isServer) {
        // 获取模板项目列表
        Map<String, String> exampleProjectsMap = projectCreateUtils.getExampleProjectMap(isServer);
        if (CheckUtils.isEmpty(exampleProjectsMap)) {
            return false;
        }
        // 开启进度条
        progressLocalCacheListener.startProgress(PROGRESS_NAME, 15, 100);
        progressLocalCacheListener.step(PROGRESS_NAME, "创建开始");
        // clone模板项目
        Map<String, String> exampleProjectsPathMap =
            projectCreateUtils.getExampleProjectsPathMap(params, exampleProjectsMap);
        if (CheckUtils.isEmpty(exampleProjectsPathMap)) {
            return false;
        }

        // 需要替换的相关内容，可以自己重写
        Map<String, String> replaceMap = ProjectCreateUtils.getReplaceMap(params, exampleProjectConfig, isServer);
        if (CheckUtils.isEmpty(replaceMap)) {
            return false;
        }
        progressLocalCacheListener.step(PROGRESS_NAME, "参数获取完成");
        // 开始创建文件
        projectCreateUtils.createFile(exampleProjectsPathMap, params.getTableDetail(), replaceMap,
            params.getPackageName(), params);

        progressLocalCacheListener.finish(PROGRESS_NAME, "创建完成");

        return true;
    }

}
