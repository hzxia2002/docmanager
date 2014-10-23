package com.comet.system.controller;

import com.comet.system.daoservice.SysMenuService;
import com.comet.system.manager.SysMenuManager;
import com.comet.system.manager.UserManager;
import com.comet.system.menu.LigerMenuGenerator;
import com.comet.system.menu.MenuGenerator;
import com.comet.system.tree.Node;
import com.comet.system.tree.TreeBranch;
import com.comet.system.tree.ZTreeNode;
import com.comet.system.utils.UserSessionUtils;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.domain.SysMenu;
import com.comet.system.domain.SysPrivilege;
import com.comet.system.domain.SysUser;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class SysMenuController extends BaseCRUDActionController<SysMenu> {
    @Autowired
	private SysMenuService sysMenuService;

    @Autowired
    private SysMenuManager sysMenuManager;

    @Autowired
    private UserManager userManager;

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
            String hql = "from SysMenu where parent.id is null order by treeId";

            if (!StringUtils.equals(uid, "root")) {
                hql = "from SysMenu where parent.id = " + uid + " order by treeId";
            }

            List<SysMenu> sysMenus = sysMenuService.findByQuery(hql);

            for (SysMenu sysMenu : sysMenus) {
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(sysMenu.getClass().getSimpleName() + "_" + sysMenu.getId());
                if(!sysMenu.getIsLeaf()){
                    treeNode.setIsParent(true);
                    treeNode.setOpen(false);
                }else{
                    treeNode.setIsLeaf(true);
                }
                treeNode.setName(sysMenu.getName());
                treeNode.setText(sysMenu.getName());
                treeNode.setUid(sysMenu.getId().toString());
                treeNode.setType(sysMenu.getClass().getSimpleName());
                treeNode.setTreeId(sysMenu.getTreeId());
                treeBranch.addTreeNode(treeNode);
            }
        }

        return treeBranch.getTreeNodeList();
	}

	@RequestMapping
    @ResponseBody
	public Page<SysMenu> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysMenu t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysMenuService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String initAdd(Model model, SysMenu entity) throws Exception {
        try {
            if(entity == null) {
                entity = new SysMenu();
            }

            entity.setIsValid(Boolean.TRUE);

            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysMenuEdit";
    }

    @RequestMapping
    public String init(Model model, SysMenu entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysMenuService.get(entity.getId());
            } else {
                entity = new SysMenu();
            }

            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysMenuEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SysMenu sysMenu = sysMenuService.get(id);

        model.addAttribute("bean", sysMenu);
        return "view/sys_new/sysMenuView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysMenu entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "name",
                    "privilege",
                    "menuLevel",
                    "url",
                    "jsEvent",
                    "parent",
                    "isValid",
                    "param",
                    "icon",
                    "target"
            };

            SysMenu target;
            if (entity.getId() != null) {
                target = sysMenuService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysMenuService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysMenuService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    @RequestMapping
    @ResponseBody
    public List<Map> getMenu() throws Exception {
        ArrayList<SysMenu> sysMenus = getSysMenu();
        
        return LigerMenuGenerator.generateTreeMenu(sysMenus);
    }

    @RequestMapping
    @ResponseBody
    public String getJuiMenu() throws Exception {
        ArrayList<SysMenu> sysMenus = getSysMenu();
        HashMap hashMap = new HashMap();
        hashMap.put("items",LigerMenuGenerator.generateJuiMenu(sysMenus));
        JSONObject jsonObject = JSONObject.fromObject(hashMap);
        System.out.println("jsonObject = " + jsonObject);
        return jsonObject.toString();
    }

    /**
     * 获取系统菜单
     * @return
     */
    public ArrayList<SysMenu> getSysMenu() {
        SysUser loginedUser = UserSessionUtils.getInstance().getLoginedUser();
        // 查询所有的有效菜单
        List<SysMenu> list = sysMenuManager.getAllMenu(Boolean.TRUE);
        ArrayList<SysMenu> sysMenus = new ArrayList<SysMenu>();

        List<SysPrivilege> privileges = userManager.getUserRolePrivileges(loginedUser, "1");
        StringBuilder sb = new StringBuilder();
        for (SysPrivilege privilege : privileges) {
            sb.append(",").append(privilege.getCode());
        }
        String sbStr = sb.append(",").toString();

        for (SysMenu sysMenu : list) {
            if(sbStr.indexOf("," + sysMenu.getPrivilege())>=0){
                sysMenus.add(sysMenu);
            }
        }
        return sysMenus;
    }

    @RequestMapping
    @ResponseBody
    public String getFgMenu() throws Exception {
        List<SysMenu> list = sysMenuManager.getAllMenu(Boolean.TRUE);

        return MenuGenerator.generateMenu(list);
    }
}