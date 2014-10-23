package com.comet.cms.manager;

import com.comet.cms.daoservice.CmsArticleService;
import com.comet.cms.domain.CmsArticle;
import com.comet.cms.domain.CmsCategory;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.utils.DateTimeHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-6-5
 * Time: 下午10:37
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CmsArticleManager {
    private static final Logger logger = LoggerFactory
            .getLogger(CmsArticleManager.class);

    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired
    private CmsCategoryManager cmsCategoryManager;

    /**
     * 通过categoryId取得目录下的文章列表
     *
     * @param categoryId
     * @return
     */
    public List<CmsArticle> getArticles(Long categoryId) {
        String hql = "from CmsArticle t where t.category.id = " + categoryId
                + " order by t.id asc";

        List<CmsArticle> list = cmsArticleService.find(hql);

        return list;
    }

    /**
     * 根据栏目编号和编码查询文章列表(不含文章内容)
     *
     * @param type 栏目ID
     * @param typeCode 栏目代码
     * @param size 查询记录条数
     * @param showContent 是否显示内容
     * @param queryChildren 是否查询子栏目
     * @return
     */
    public List<CmsArticle> query(Long type, String typeCode, int size, Boolean showContent, Boolean queryChildren)
            throws Exception {
        List<CmsArticle> ret = new ArrayList<CmsArticle>();

        try {
            String hql = "select title, publishDate, createUser, t.category.name, id, thumbPath, linkUrl";

            if(showContent != null && showContent) {
                hql += ",content";
            }

            hql += " from CmsArticle t where isValid = 1" +
                    " and DATE_FORMAT(PUBLISH_DATE, '%Y-%m-%d')<= '" + DateTimeHelper.getCurrentDate() + "'";

            if(queryChildren) {
                // 取得栏目信息
                CmsCategory typeBean = cmsCategoryManager.getByCode(typeCode);

                if(typeBean != null) {
                    hql += " and category.treeId like '" + typeBean.getTreeId() + "%'";
                } else {
                    hql += " and category.code = '" + typeCode + "'";
                }
            } else {
                if(type != null) {
                    hql += " and category.id = " + type;
                }

                if(StringUtils.isNotEmpty(typeCode)) {
                    hql += " and category.code = '" + typeCode + "'";
                }
            }

            hql += " order by isTop desc, publishDate desc";

            Page page = new Page();

            page.setPageSize(10);

            if(size > 0) {
                page.setPageSize(size);
            }

            page = cmsArticleService.findByPage(page, hql);

            for(Object tmp : page.getRows()) {
                Object[] obj = (Object[])tmp;

                CmsArticle info = new CmsArticle();
                info.setTitle((String) obj[0]);
                info.setPublishDate((Date) obj[1]);
                info.setCreateUser((String) obj[2]);
                info.setCategoryName((String) obj[3]);
                info.setId((Long) obj[4]);
                info.setThumbPath((String) obj[5]);
                info.setLinkUrl((String) obj[6]);

                if(showContent != null && showContent) {
                    info.setContent((String)obj[7]);
                }

                ret.add(info);
            }
        } catch (Exception e) {
            logger.error("error", e);
            throw e;
        }

        return ret;
    }

    /**
     * 根据栏目编号和编码查询文章列表，默认不查询子栏目内容
     *
     * @param type
     * @param typeCode
     * @param size
     * @param showContent
     * @return
     * @throws Exception
     */
    public List<CmsArticle> query(Long type, String typeCode, int size, Boolean showContent) throws Exception {
        return query(type, typeCode, size, showContent, false);
    }

    /**
     * 分页查询数据
     *
     * @param page
     * @param bean
     * @param showContent
     * @return
     * @throws Exception
     */
    public Page<CmsArticle> queryPage(Page page, CmsArticle bean, Boolean showContent)
            throws Exception {
        try {
            CmsCategory category = cmsCategoryManager.getByCode(bean.getCategoryCode());

            String hql = "select title, publishDate, createUser, t.category.name, id, thumbPath, linkUrl";

            if(showContent != null && showContent) {
                hql += ",content";
            }

            hql += " from CmsArticle t where isValid = 1" +
                    " and DATE_FORMAT(PUBLISH_DATE, '%Y-%m-%d')<= '" + DateTimeHelper.getCurrentDate() + "'";

            if(bean.getCategory() != null && bean.getCategory().getId() != null) {
                hql += " and t.category.id = " + bean.getCategory().getId();
            }

            if(category != null) {
                if (StringUtils.isNotEmpty(bean.getCategoryCode())) {
                    hql += " and t.category.treeId like '" + category.getTreeId() + "%'";
                }
            }

            hql += " order by t.isTop desc, t.publishDate desc";

            page = cmsArticleService.findByPage(page, hql);
            List ret = new ArrayList();

            for(Object tmp : page.getRows()) {
                Object[] obj = (Object[])tmp;

                CmsArticle info = new CmsArticle();
                info.setTitle((String)obj[0]);
                info.setPublishDate((Date) obj[1]);
                info.setCreateUser((String) obj[2]);
                info.setCategoryName((String) obj[3]);
                info.setId((Long) obj[4]);
                info.setThumbPath((String) obj[5]);
                info.setLinkUrl((String)obj[6]);

                if(showContent != null && showContent) {
                    info.setContent((String)obj[7]);
                }

                ret.add(info);
            }

            page.setRows(ret);
        } catch (Exception e) {
            logger.error("error", e);
            throw e;
        }

        return page;
    }

    /**
     * 获取格式化后的信息内容
     * 去掉了换行符，如需要换行则用JspHelper.FormatOutPutString或sys:toHtml标签
     * @param content 信息内容
     * @param wordCount 字符数
     * @return .
     */
    public String getFormatContent(String content,int wordCount){
        if(StringUtils.isNotEmpty(content)){
            content = content.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", "");
            content = content.replaceAll("\r","");
            content = content.replaceAll("\n","");

            if(wordCount>0 && content.length()>wordCount){
                content = content.substring(0,wordCount)+"...";
            }
        }

        return content;
    }
}
