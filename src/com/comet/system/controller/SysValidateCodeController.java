package com.comet.system.controller;

import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysValidateCodeService;
import com.comet.system.domain.SysValidateCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class SysValidateCodeController extends BaseCRUDActionController<SysValidateCode> {
    private static Log log = LogFactory.getLog(SysValidateCodeController.class);

    @Autowired
	private SysValidateCodeService sysValidateCodeService;

    /**
     * 查询数据
     *
     * @param page
     * @param condition
     * @return
     */
	@RequestMapping
    @ResponseBody
	public Page<SysValidateCode> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysValidateCode t where 1=1 " + page.getOrderByString("t.id desc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysValidateCodeService.findByPage(page, queryTranslate.toString());
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
    public String init(Model model, SysValidateCode entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysValidateCodeService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysValidateCodeEdit";
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
        SysValidateCode bizValidateCode = sysValidateCodeService.get(id);

        model.addAttribute("bean", bizValidateCode);
        return "view/sys_new/sysValidateCodeView";
    }

    /**
     * 保存
     *
     * @param response
     * @param model
     * @param entity
     * @throws Exception
     */
    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysValidateCode entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "nickName",
                    "email",
                    "mobile",
                    "validateCode",
                    "validateCodeType",
                    "createTime",
                    "updateTime",
                    "updateUser",
                    "createUser"
            };

            SysValidateCode target;
            if (entity.getId() != null) {
                target = sysValidateCodeService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysValidateCodeService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    /**
     * 删除
     *
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysValidateCodeService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}