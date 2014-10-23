package com.comet.system.controller;

import com.comet.system.daoservice.SysUserService;
import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysUser;
import com.comet.system.manager.SysRoleManager;
import com.comet.system.manager.SysUserManager;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.utils.ReflectionUtils;
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
public class SysUserSiteController extends BaseCRUDActionController<SysUser> {
    @Autowired
	private SysUserService sysUserService;

    @Autowired
    private SysUserManager sysUserManager;

    @Autowired
    private SysRoleManager sysRoleManager;

    @RequestMapping
    @ResponseBody
	public Page<SysUser> grid(Page page, String condition) {
		try {
            page = sysUserManager.queryPage(page, condition);
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, SysUser entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysUserService.get(entity.getId());

                // 取得用户已有的角色
                List<SysRole> roles = sysUserManager.getUserRoles(entity.getId());

                entity.setRoleNames(sysRoleManager.getRoleIdsAsString(roles));

                this.getAllRoles(model);
                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys/sysUserSiteEdit";
    }

    @RequestMapping
    public String initAdd(Model model, SysUser entity) throws Exception {
        try {
            if(entity == null) {
                entity = new SysUser();
            }

            model.addAttribute("bean", entity);
            this.getAllRoles(model);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys/sysUserSiteAdd";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SysUser sysUser = sysUserService.get(id);

        List<SysRole> roles = sysUserManager.getUserRoles(id);

        sysUser.setRoleNames(sysRoleManager.getRoleNamesAsString(roles));

        model.addAttribute("bean", sysUser);
        return "view/sys/sysUserSiteView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysUser entity, String ids)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "person",
                    "loginName",
                    "password",
                    "displayName",
                    "status",
                    "reasonDesc",
                    "openDate",
                    "closeDate"
            };

            SysUser target;
            if (entity.getId() != null) {
                target = sysUserService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);

                sysUserManager.update(target, ids);
            } else {
                target = entity;
                sysUserManager.save(target, ids);
            }

            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysUserManager.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    /**
     * 取得所有的角色
     *
     * @param model
     */
    protected void getAllRoles(Model model) {
        List<SysRole> list = sysRoleManager.getAllRoles();

        model.addAttribute("roleList", list);
    }
}