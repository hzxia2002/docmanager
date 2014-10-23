package com.comet.system.controller;

import com.comet.system.daoservice.SysUserPrivilegeService;
import com.comet.system.domain.SysUserPrivilege;
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
public class SysUserPrivilegeController extends BaseCRUDActionController<SysUserPrivilege> {
    @Autowired
	private SysUserPrivilegeService sysUserPrivilegeService;

	@RequestMapping
    @ResponseBody
	public Page<SysUserPrivilege> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysUserPrivilege t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysUserPrivilegeService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, SysUserPrivilege entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysUserPrivilegeService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys/sysDeptEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SysUserPrivilege sysUserPrivilege = sysUserPrivilegeService.get(id);

        model.addAttribute("bean", sysUserPrivilege);
        return "view/sys/sysUserPrivilegeView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysUserPrivilege entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "user",
                    "privilege",
                    "isDeny"
            };

            SysUserPrivilege target;
            if (entity.getId() != null) {
                target = sysUserPrivilegeService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysUserPrivilegeService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysUserPrivilegeService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}