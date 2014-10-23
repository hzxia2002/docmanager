package com.comet.system.daoservice;

import com.comet.core.orm.hibernate.EntityService;
import com.comet.system.domain.SysMessageReceiveBack;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:system
 *注：此文件内容不需要修改
 */
@Service
public class SysMessageReceiveBackService extends EntityService<SysMessageReceiveBack,Long> {
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, SysMessageReceiveBack.class);
    }
}