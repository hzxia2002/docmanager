package com.comet.cms.utils;

import com.comet.cms.domain.CmsArticle;
import com.comet.cms.domain.CmsCategory;
import com.comet.cms.manager.CmsCategoryManager;
import com.comet.core.utils.SpringUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Created by dell on 2014/8/9.
 */
public class CmsUtils {
    /**
     * 取得栏目名称
     *
     * @param bean
     * @param separator
     * @return
     */
    public static String getNames(CmsCategory bean, String separator) {
        String ret = bean.getName();

        if(bean.getParent() != null) {
            ret = getNames(bean.getParent(), separator) + separator + ret;
        }

        return ret;
    }

    /**
     * 取得文章链接的URL
     *
     * @param bean
     * @param context
     * @return
     */
    public static String getUrl(CmsArticle bean, String context) {
        if(StringUtils.isNotBlank(bean.getLinkUrl())) {
            return bean.getLinkUrl();
        } else {
            return context + "/news/view.do?id=" + bean.getId();
        }
    }

    /**
     * 取得频道链接地址
     *
     * @param channelCode
     * @param context
     * @return
     */
    public static String getChannelUrl(String channelCode, String context) {
        CmsCategoryManager manager = (CmsCategoryManager) SpringUtils.getBean("cmsCategoryManager");

        CmsCategory bean = manager.getByCode(channelCode);

        if(bean != null) {
            if(bean.getIsLeaf()) {
                return context + "/news/list.do?categoryCode=" + channelCode;
            } else {
                return context + "/news/channelList.do?categoryCode=" + channelCode;
            }
        }

        return context + "/news/list.do?categoryCode=" + channelCode;
    }

    /**
     * 按照长度取得文字的内容
     *
     * @param content
     * @param length
     * @param escapeXml
     * @return
     */
    public static String getContent(String content, int length, boolean escapeXml) {
        content = StringUtils.defaultIfEmpty(content, "");

        // 去掉字段中的html
        if(!escapeXml) {
            content = content.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", "");
        }

        if(content.length() <= length) {
            return content;
        } else {
            return content.substring(0, length) + "...";
        }
    }

    /**
     * 按照长度取得文字的内容，默认去掉字符中的html标记
     *
     * @param content
     * @param length
     * @return
     */
    public static String getContent(String content, int length) {
        return getContent(content, length, false);
    }
}
