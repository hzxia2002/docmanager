package com.comet.cms.daoservice;

import com.comet.cms.domain.DocAttachments;
import com.comet.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tcg
 * User: tcg
 *
 */
@Service
public class DocAttachmentsService extends EntityService<DocAttachments,Long> {
     @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, DocAttachments.class);
    }
}