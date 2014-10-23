package com.comet.cms.domain;

import com.comet.cms.domain.base.BaseCmsCategory;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 12-5-14
 * Time: 下午9:06
 * To change this template use File | Settings | File Templates.
 */
public class CmsCategory extends BaseCmsCategory {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public CmsCategory () {
        super();
    }

    /**
     * Constructor for primary key
     */
    public CmsCategory (Long id) {
        super(id);
    }
}
