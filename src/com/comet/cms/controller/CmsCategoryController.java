package com.comet.cms.controller;

import com.comet.cms.daoservice.CmsCategoryService;
import com.comet.cms.domain.CmsCategory;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.tree.Node;
import com.comet.system.tree.TreeBranch;
import com.comet.system.tree.ZTreeNode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class CmsCategoryController extends BaseCRUDActionController<CmsCategory> {
    @Autowired
    private CmsCategoryService cmsCategoryService;


    /**
     * 取得树数据
     *
     * @param type
     *            节点类型
     * @param uid
     *            节点ID
     * @throws Exception
     *             异常
     */
    @RequestMapping
    @ResponseBody
    public List<Node> tree(String type,String uid, String id) throws Exception {
        TreeBranch treeBranch = new TreeBranch();
        type = StringUtils.defaultString(type, "");

        if(StringUtils.isEmpty(uid)) {
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId("root");
            treeNode.setIsParent(true);
            treeNode.setIsLeaf(false);
            treeNode.setOpen(true);
//            treeNode.setHasChildren(true);
            treeNode.setName("目录树");
            treeNode.setText("目录树");
            treeNode.setUid("root");
            treeBranch.addTreeNode(treeNode);
        } else {
            String hql = "from CmsCategory where parent.id is null order by treeId";

            if (!StringUtils.equals(uid, "root")) {
                hql = "from CmsCategory where parent.id = " + uid + " order by treeId";
            }

            List<CmsCategory> categories = cmsCategoryService.findByQuery(hql);

            for (CmsCategory category : categories) {
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(category.getClass().getSimpleName() + "_" + category.getId());
                if(category.getIsLeaf() != null && !category.getIsLeaf()){
                    treeNode.setIsParent(true);
                    treeNode.setOpen(false);
                }else{
                    treeNode.setIsLeaf(true);
                }
                treeNode.setName(category.getName());
                treeNode.setText(category.getName());
                treeNode.setUid(category.getId().toString());
                treeNode.setType(category.getClass().getSimpleName());
                treeNode.setTreeId(category.getTreeId());
                treeBranch.addTreeNode(treeNode);
            }
        }

        return treeBranch.getTreeNodeList();
    }

    /**
     * 查询列表数据
     *
     * @param page
     * @param condition
     * @param request
     * @return
     */
    @RequestMapping
    @ResponseBody
    public Page<CmsCategory> grid(Page page, String condition, HttpServletRequest request) {
        try {
            page.setAutoCount(true);

            String hql = "from CmsCategory t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsCategoryService.findByPage(page, queryTranslate.toString());
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
    }

    /**
     * 初始化
     *
     * @param model
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String init(Model model, CmsCategory entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsCategoryService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsCategoryEdit";
    }

    /**
     * 新增初始化
     *
     * @param model
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String initAdd(Model model, CmsCategory entity) throws Exception {
        try {
            if(entity == null) {
                entity = new CmsCategory();
            }

            entity.setIsValid(true);

            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsCategoryEdit";
    }

    /**
     * 查看
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping
    public String view(Model model, Long id) {
        CmsCategory sysDept = cmsCategoryService.get(id);

        model.addAttribute("bean", sysDept);
        return "view/cms/cmsCategoryView";
    }

    /**
     * 保存目录数据
     *
     * @param response
     * @param model
     * @param entity
     * @throws Exception
     */
    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") CmsCategory entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "code",
                    "name",
                    "isValid",
                    "description"
            };

            CmsCategory target;
            if (entity.getId() != null) {
                target = cmsCategoryService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            cmsCategoryService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);

            return;
        }

        sendSuccessJSON(response, "保存成功");
    }

    /**
     * 删除目录
     *
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        cmsCategoryService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}