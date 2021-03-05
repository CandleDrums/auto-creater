/**
 * @Project auto-creater-dal
 * @Package com.cds.app.creater.dal.model
 * @Class DBConnectionDO.java
 * @Date Jun 4, 2020 10:00:22 AM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.dal.model;

import javax.persistence.Table;

import com.cds.base.dal.model.BasicModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Description 数据库连接信息
 * @Notes 未填写备注
 * @author liming
 * @Date Jun 4, 2020 10:00:22 AM
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "db_connection")
public class DBConnectionDO extends BasicModel {

    private static final long serialVersionUID = 1L;
    private String name;
    private String host;
    private String userName;
    private String passwd;
    private String port;

}
