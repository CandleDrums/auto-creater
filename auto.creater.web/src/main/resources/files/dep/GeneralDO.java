/**
 * @Project [module]-dep-dal
 * @Package com.cds.[module].dep.dal.model
 * @Class [model]DO.java
 * @Date [date]
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds.[module].dep.dal.model;

import com.cds.base.dep.dal.mapper.MybatisMapperGenerator;
import com.cds.base.dep.dal.mapper.generator.TableAnnotation;
import com.cds.base.dep.dal.model.GeneralModel;

/**
 * @Description [model_desc]
 * @Notes 未填写备注
 * @author auto create
 * @Date [date]
 * @version 1.0
 * @since JDK 1.8
 */
@TableAnnotation("[table_name]")
public class [model]DO extends GeneralModel {

    private static final long serialVersionUID = 1L;
    
    // TODO 添加其他属性

    public static void main(String[] args) {
        MybatisMapperGenerator.build([model]DO.class);
    }

   

}
