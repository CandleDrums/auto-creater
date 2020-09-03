/**
 * @Project auto-creater-service
 * @Package com.cds.auto.creater.service.impl
 * @Class DBConnectionServiceImpl.java
 * @Date Jun 4, 2020 10:51:26 AM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.auto.creater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cds.app.creater.dal.dao.DBConnectionDAO;
import com.cds.app.creater.dal.model.DBConnectionDO;
import com.cds.auto.creater.service.DBConnectionService;
import com.cds.base.biz.service.impl.BasicServiceImpl;
import com.cds.base.dal.dao.BasicDAO;
import com.cds.base.generator.mybatis.config.DBConnectionConfig;

/**
 * @Description 数据库连接信息Service实现
 * @Notes 未填写备注
 * @author liming
 * @Date Jun 4, 2020 10:51:26 AM
 */
@Service
public class DBConnectionServiceImpl extends BasicServiceImpl<DBConnectionConfig, DBConnectionDO>
    implements DBConnectionService {
    @Autowired
    private DBConnectionDAO dbConnectionDAO;

    @Override
    protected BasicDAO<DBConnectionDO> getDAO() {
        return dbConnectionDAO;
    }

}
