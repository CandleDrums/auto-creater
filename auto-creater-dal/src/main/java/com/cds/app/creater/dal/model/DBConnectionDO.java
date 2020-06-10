/**
 * @Project auto-creater-dal
 * @Package com.cds.app.creater.dal.model
 * @Class DBConnectionDO.java
 * @Date Jun 4, 2020 10:00:22 AM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved
 */
package com.cds.app.creater.dal.model;

import com.cds.base.util.generator.mybatis.MybatisMapperGenerator;
import com.cds.base.util.generator.mybatis.annotaion.TableAnnotation;

import lombok.Data;
import lombok.ToString;

/**
 * @Description 数据库连接信息
 * @Notes 未填写备注
 * @author liming
 * @Date Jun 4, 2020 10:00:22 AM
 */
@Data
@ToString(callSuper = true)
@TableAnnotation(value = "db_connection")
public class DBConnectionDO {

    private Integer id;
    private String name;
    private String host;
    private String userName;
    private String passwd;
    private String port;

    public static void main(String[] args) {
        MybatisMapperGenerator.build(DBConnectionDO.class);
    }
}
