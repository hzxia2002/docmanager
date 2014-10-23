package com.comet.system.controller;

import com.comet.system.domain.SysPrivilege;
import com.comet.system.domain.SysRolePrivilege;
import com.comet.system.manager.SysRoleManager;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.system.daoservice.SysPrivilegeService;
import com.comet.system.daoservice.SysRolePrivilegeService;
import com.comet.system.daoservice.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class SysRolePrivilegeController extends BaseCRUDActionController<SysRolePrivilege> {
    @Autowired
	private SysRolePrivilegeService sysRolePrivilegeService;

    @Autowired
    private SysPrivilegeService sysPrivilegeService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleManager sysRoleManager;

    /**
     * 取得树数据
     *
     * @throws Exception
     *             异常
     */
    @RequestMapping
    @ResponseBody
    public List<Map> tree(Long roleId) throws Exception {
        List<Map> ret = new ArrayList<Map>();

        // 取得角色权限
        Map<Long, SysPrivilege> rpMap = sysRoleManager.getRolePrivilegesAsMap(roleId);

        Map root = new HashMap();
        root.put("id", "-1");
        root.put("text", "授权资源");
        root.put("isexpand", true);
        root.put("isLoaded", true);

        if(rpMap.size() > 0) {
            root.put("ischecked", true);
        }

        ret.add(root);

        // 生成权限节点
        String hql = "from SysPrivilege order by code asc";
        List<SysPrivilege> sysPrivileges = sysPrivilegeService.findByQuery(hql);

        for (SysPrivilege sysPrivilege : sysPrivileges) {
            Map map = new HashMap();

            map.put("id", sysPrivilege.getId());
            map.put("text", sysPrivilege.getName());
            map.put("isexpand", true);
            map.put("isLoaded", true);

            if(sysPrivilege.getParent() != null) {
                map.put("pid", sysPrivilege.getParent().getId());
            } else {
                map.put("pid", -1);
            }

            if(rpMap.containsKey(sysPrivilege.getId())) {
                map.put("ischecked", true);
            }

            ret.add(map);
        }

        return ret;
    }

	@RequestMapping
    @ResponseBody
	public Page<SysRolePrivilege> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysRolePrivilege t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysRolePrivilegeService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, String roleId) throws Exception {
        try {
            model.addAttribute("roleId", roleId);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysRolePrivilegeEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SysRolePrivilege sysRolePrivilege = sysRolePrivilegeService.get(id);

        model.addAttribute("bean", sysRolePrivilege);
        return "view/sys_new/sysRolePrivilegeView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, String ids, Long roleId)
            throws Exception {
        try {
            sysRoleManager.saveRolePrivilege(ids, roleId);
            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysRolePrivilegeService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}