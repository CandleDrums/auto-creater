/**
 * @Project auto-creater-service
 * @Package com.cds.auto.creater.service.impl
 * @Class MavenCleanServiceImpl.java
 * @Date Jul 17, 2020 5:54:10 PM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.auto.creater.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cds.auto.creater.service.MavenCleanService;
import com.cds.base.util.bean.CheckUtils;
import com.cds.base.util.file.FileUtils;

/**
 * @Description Maven清理Service实现
 * @Notes 未填写备注
 * @author liming
 * @Date Jul 17, 2020 5:54:10 PM
 */
@Service
public class MavenCleanServiceImpl implements MavenCleanService {

    @Override
    public List<File> getFileList(String path, List<String> junkList) {
        List<File> fileList = FileUtils.traverseFolder(path);
        if (CheckUtils.isEmpty(fileList)) {
            return null;
        }
        List<File> resultList = new ArrayList<File>();
        for (File file : fileList) {
            String name = file.getName();
            for (String junk : junkList) {
                if (name.lastIndexOf(junk) > 0) {
                    resultList.add(file);
                }
            }
        }
        return resultList;
    }

    @Override
    public void clean(String path, List<String> junkList) {
        List<File> fileList = this.getFileList(path, junkList);
        for (File file : fileList) {
            // 防止误删
            if (file.isFile()) {
                file.delete();
            }
        }
    }

}
