package com.comet.system.controller;

import com.comet.system.daoservice.SysUserRoleService;
import com.comet.system.domain.SysUserRole;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class SysUserRoleController extends BaseCRUDActionController<SysUserRole> {
    @Autowired
	private SysUserRoleService sysUserRoleService;

	@RequestMapping
    @ResponseBody
	public Page<SysUserRole> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysUserRole t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysUserRoleService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, SysUserRole entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysUserRoleService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys/sysDeptEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SysUserRole sysUserRole = sysUserRoleService.get(id);

        model.addAttribute("bean", sysUserRole);
        return "view/sys/sysUserRoleView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysUserRole entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "user",
                    "role"
            };

            SysUserRole target;
            if (entity.getId() != null) {
                target = sysUserRoleService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysUserRoleService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysUserRoleService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}