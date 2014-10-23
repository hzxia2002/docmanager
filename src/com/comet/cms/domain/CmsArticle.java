package com.comet.cms.domain;

import com.comet.cms.domain.base.BaseCmsArticle;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 12-5-14
 * Time: 下午9:06
 * To change this template use File | Settings | File Templates.
 */
public class CmsArticle extends BaseCmsArticle {
    private static final long serialVersionUID = 1L;

    private String categoryName;

    private String categoryCode;

    private String imagePath;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public CmsArticle() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public CmsArticle(Long id) {
        super(id);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getImagePath() {
        String tmp = this.getThumbPath();
        if(tmp != null && tmp != "") {
            return tmp.replace("/s_", "/");
        }

        return tmp;
    }
}
