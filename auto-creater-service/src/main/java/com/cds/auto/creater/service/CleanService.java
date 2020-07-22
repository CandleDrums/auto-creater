/**
 * @Project auto-creater-service
 * @Package com.cds.auto.creater.service
 * @Class MavenCleanService.java
 * @Date Jul 17, 2020 4:15:40 PM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.auto.creater.service;

import java.io.File;
import java.util.List;

/**
 * @Description Maven清理Service
 * @Notes 未填写备注
 * @author liming
 * @Date Jul 17, 2020 4:15:40 PM
 */
public interface CleanService {
    /**
     * @description 加载文件
     * @return List<File>
     */
    List<File> getFileList(String path, List<String> junkList);

    /**
     * @description 清理
     * @return void
     */
    boolean clean(String path, List<String> junkList);

}
