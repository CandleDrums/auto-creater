/**
 * @Project [module]-dep-dal
 * @Package com.cds.[module].dep.dal.dao.impl
 * @Class [model]DAOImpl.java
 * @Date [date]
 * @Copyright (c) 2019 CandleDrums.com All Right Reserved.
 */
package com.cds.[module].dep.dal.dao.impl;

import org.springframework.stereotype.Repository;
import com.cds.base.dep.dal.dao.impl.MybatisBasicDAOImpl;
import com.cds.[module].dep.dal.dao.[model]DAO;
import com.cds.[module].dep.dal.model.[model]DO;

/**
 * @Description [model_desc]DAO实现
 * @Notes 未填写备注
 * @author auto create
 * @Date [date]
 * @version 1.0
 * @since JDK 1.8
 */
@Repository
public class [model]DAOImpl extends MybatisBasicDAOImpl<[model]DO, [idType]> implements [model]DAO {
}