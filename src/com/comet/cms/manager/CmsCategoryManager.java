package com.comet.cms.manager;

import com.comet.cms.daoservice.CmsCategoryService;
import com.comet.cms.domain.CmsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-6-5
 * Time: 下午10:37
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CmsCategoryManager {

    @Autowired
    private CmsCategoryService cmsCategoryService;

    /**
     * 通过目录编码取得目录下的子目录列表
     *
     * @param code
     * @return
     */
    public List<CmsCategory> getCategories(String code) {
        String hql = "from CmsCategory t where t.parent.id = (select id from CmsCategory t1" +
                " where t1.code = '" + code + "') order by t.treeId asc";

        List<CmsCategory> list = cmsCategoryService.find(hql);

        return list;
    }

    /**
     * 根据Code获取OaPublicType
     *
     * @param code
     * @return
     */
    public CmsCategory getByCode(String code) {
        String hql = "from CmsCategory where code = '" + code + "'";
        List<CmsCategory> list = cmsCategoryService.find(hql);

        if (list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    /**
     * 根据Code取得子节点列表
     *
     * @param code
     * @return
     */
    public List<CmsCategory> getChildrenByCode(String code) {
        return getChildrenByCode(code, false);
    }

    /**
     * 取得一个栏目下的子栏目
     *
     * @param code 栏目代码
     * @param showAll 是否显示所有子栏目
     * @return
     */
    public List<CmsCategory> getChildrenByCode(String code, Boolean showAll) {
        String hql = "from CmsCategory t where t.parent.code = '" + code + "' order by t.treeId asc";

        if(showAll) {
            // 查询TreeId
            CmsCategory category = getByCode(code);

            if(category != null) {
                hql = "from CmsCategory t where t.treeId like '" + category.getTreeId() + ".%'";
                hql += " order by t.treeId asc";
            }
        }

        return cmsCategoryService.find(hql);
    }
}
