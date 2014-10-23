package com.comet.system.controller;

import com.comet.system.daoservice.SysRoleService;
import com.comet.system.domain.SysRole;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.manager.SysRoleManager;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class SysRoleController extends BaseCRUDActionController<SysRole> {
    @Autowired
	private SysRoleService sysRoleService;

    @Autowired
    private SysRoleManager sysRoleManager;

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
            treeNode.setName("根节点");
            treeNode.setText("根节点");
            treeNode.setUid("root");
            treeBranch.addTreeNode(treeNode);
        } else {
            String hql = "from SysRole order by code asc";

            List<SysRole> sysRoles = sysRoleService.findByQuery(hql);

            for (SysRole sysRole : sysRoles) {
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(sysRole.getClass().getSimpleName() + "_" + sysRole.getId());
                treeNode.setIsLeaf(true);
                treeNode.setName(sysRole.getRoleName());
                treeNode.setText(sysRole.getRoleName());
                treeNode.setUid(sysRole.getId().toString());
                treeNode.setType(sysRole.getClass().getSimpleName());
                treeBranch.addTreeNode(treeNode);
            }
        }

        return treeBranch.getTreeNodeList();
    }

	@RequestMapping
    @ResponseBody
	public Page<SysRole> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysRole t where 1=1 " + page.getOrderByString("t.id asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysRoleService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, SysRole entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysRoleService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysRoleEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SysRole sysRole = sysRoleService.get(id);

        model.addAttribute("bean", sysRole);
        return "view/sys_new/sysRoleView";
    }

    @RequestMapping
    public String initAdd(Model model, SysRole entity) throws Exception {
        try {
            if(entity == null) {
                entity = new SysRole();
            }

            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysRoleEdit";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysRole entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "code",
                    "roleName",
                    "description"
            };

            SysRole target;
            if (entity.getId() != null) {
                target = sysRoleService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysRoleService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysRoleManager.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}