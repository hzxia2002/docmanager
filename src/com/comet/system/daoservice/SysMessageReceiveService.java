package com.comet.system.daoservice;

import com.comet.core.orm.hibernate.EntityService;
import com.comet.system.domain.SysMessageReceive;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:system
 *注：此文件内容不需要修改
 */
@Service
public class SysMessageReceiveService extends EntityService<SysMessageReceive,Long> {
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, SysMessageReceive.class);
    }
}