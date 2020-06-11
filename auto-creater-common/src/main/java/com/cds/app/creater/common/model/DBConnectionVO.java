/**
 * @Project auto-creater-common
 * @Package com.cds.app.creater.common.model
 * @Class DBConnectionVO.java
 * @Date Jun 4, 2020 10:49:18 AM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.common.model;

import lombok.Data;

/**
 * @Description 数据库连接信息VO
 * @Notes 未填写备注
 * @author liming
 * @Date Jun 4, 2020 10:49:18 AM
 */
@Data
public class DBConnectionVO {
    private Integer id;
    private String name;
    private String host;
    private String userName;
    private String passwd;
    private String port;

}
