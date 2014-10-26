package com.comet.cms.controller;

import com.comet.cms.daoservice.CmsReceiverService;
import com.comet.cms.domain.CmsReceiver;
import com.comet.cms.manager.CmsReceiverManager;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.system.domain.SysRole;
import com.comet.system.manager.SysRoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class CmsReceiverController extends BaseCRUDActionController<CmsReceiver> {
    @Autowired
    private CmsReceiverService cmsReceiverService;

    @Autowired
    private CmsReceiverManager cmsReceiverManager;

    @Autowired
    private SysRoleManager sysRoleManager;

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
    public Page<CmsReceiver> grid(Page page, String condition, HttpServletRequest request) {
        try {
            page.setAutoCount(true);

            String hql = "from CmsReceiver t where 1=1 " + page.getOrderByString("t.id desc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsReceiverService.findByPage(page, queryTranslate.toString());
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
    }

    /**
     * 初始化
     *
     * @param model
     * @param articleId
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String initSelect(Model model, Long articleId) throws Exception {
        try {
            if(articleId != null) {
                List roles = cmsReceiverManager.querySelectedRoles(articleId);
                List users = cmsReceiverManager.querySelectedUsers(articleId);

                model.addAttribute("selectedRoles", roles);
                model.addAttribute("selectedUsers", users);
            }

            // 查询系统中所有的角色
            List<SysRole> list = sysRoleManager.getAllRoles();

            model.addAttribute("roleList", list);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsReceiverSelect";
    }
}