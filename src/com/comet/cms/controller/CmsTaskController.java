package com.comet.cms.controller;

import com.comet.cms.daoservice.CmsArticleService;
import com.comet.cms.daoservice.CmsReceiverService;
import com.comet.cms.daoservice.CmsTaskService;
import com.comet.cms.domain.*;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.core.utils.DateTimeHelper;
import com.comet.core.utils.FileUtils;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysRoleService;
import com.comet.system.daoservice.SysUserRoleService;
import com.comet.system.daoservice.SysUserService;
import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysUser;
import com.comet.system.domain.SysUserRole;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class CmsTaskController extends BaseCRUDActionController<CmsCategory> {
    @Autowired
    private CmsTaskService cmsTaskService;
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

            String hql = "from CmsTask t where 1=1 " + page.getOrderByString("t.id desc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsTaskService.findByPage(page, queryTranslate.toString());
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
    public String init(Model model, CmsTask entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                 entity = cmsTaskService.get(entity.getId());
            } else if(entity != null) {
//                entity.setCreateTime(new Date(System.currentTimeMillis()));
//                entity.setIsValid(Boolean.TRUE);
            }

            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsArticleEdit";
    }
}