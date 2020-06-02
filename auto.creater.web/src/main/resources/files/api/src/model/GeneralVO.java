/**
 * @Project [module].api
 * @Package com.cds.[module].api.model
 * @Class [model].java
 * @Date [date]
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds.[module].api.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @Description [model_desc]
 * @Notes 未填写备注
 * @author auto create
 * @Date [date]
 * @version 1.0
 * @since JDK 1.8
 */
public class [model] implements Serializable {

    private static final long serialVersionUID = 1L;
    // 业务编码，非空，一般由生成器生成，每个表有单独前缀
    private String            num;

    // TODO 添加其他属性

    // 更新时间，非空，默认与创建时间相同，日期格式yyyy-mm-dd hh:mm:ss
    private Date              updateDate;
    // 创建时间，非空，日期格式yyyy-mm-dd hh:mm:ss
    private Date              createDate;

}
