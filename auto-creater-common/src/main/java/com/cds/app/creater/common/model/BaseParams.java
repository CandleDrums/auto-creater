/**
 * @Project auto-creater-common
 * @Package com.cds.app.creater.common.model
 * @Class BaseParams.java
 * @Date Jun 10, 2020 10:22:42 AM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.common.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description 基础参数
 * @Notes 未填写备注
 * @author liming
 * @Date Jun 10, 2020 10:22:42 AM
 */
@Data
public class BaseParams implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userName;
    private String passwd;
    private String outputPath;
    private String port;
    private String author;
    private String packageName;

}
