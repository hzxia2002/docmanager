package com.comet.system.daoservice;

import com.comet.core.orm.hibernate.EntityService;
import com.comet.system.domain.SysValidateCode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tcg
 * User: tcg
 *
 */
@Service
public class SysValidateCodeService extends EntityService<SysValidateCode,Long> {
     @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, SysValidateCode.class);
    }
}