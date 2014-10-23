package com.comet.system.controller;

import com.comet.system.daoservice.SysLogService;
import com.comet.system.domain.SysLog;
import com.comet.system.domain.SysUser;
import com.comet.system.manager.SysCodeManager;
import com.comet.system.manager.SysLogManager;
import com.comet.system.utils.Constants;
import com.comet.system.utils.UserSessionUtils;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.DateTimeHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class SysLogController extends BaseCRUDActionController<SysLog> {
    @Autowired
	private SysLogService sysLogService;

    @Autowired
    private SysLogManager sysLogManager;

    @Autowired
    private SysCodeManager sysCodeManager;

    /**
     * 查询操作日志
     *
     * @param page
     * @param condition
     * @return
     */
	@RequestMapping
    @ResponseBody
	public Page<SysLog> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysLog t where 1=1 " + page.getOrderByString("t.id desc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysLogService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, SysLog entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysLogService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysDeptEdit";
    }

    /**
     * 查看操作日志
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping
    public String view(Model model, Long id) {
        SysLog sysLog = sysLogService.get(id);

        model.addAttribute("bean", sysLog);
        return "view/sys_new/sysLogView";
    }

    /**
     * 保存操作日志
     *
     * @param response
     * @param request
     * @param entity
     * @throws Exception
     */
    @RequestMapping
    public void save(HttpServletResponse response, HttpServletRequest request,
                     @ModelAttribute("bean") SysLog entity)
            throws Exception {
        try {
            saveLog(request, entity);

            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }
    }

    public void saveLog(HttpServletRequest request, SysLog entity) throws Exception {
        SysUser loginUser = UserSessionUtils.getInstance().getLoginedUser();

        entity.setIpAddress(request.getRemoteAddr());
        entity.setUser(loginUser);
        entity.setSessionid(request.getRequestedSessionId());

        // 获取浏览器及其版本
        String agent = request.getHeader("User-Agent");
//            String[] agents = agent.split(";");
        entity.setIeVersion(agent);

        if (StringUtils.isEmpty(entity.getLogTypeCode())) {
            entity.setLogTypeCode(Constants.LOG_TYPE_NORMAL);
        }

        entity.setLogType(sysCodeManager.getCodeListByCode(
                Constants.LOG_TYPE_CODE, entity.getLogTypeCode()));

        // 设置登入和登出时间
        if(entity.getLogTypeCode().equals(Constants.LOG_TYPE_LOGIN)) {
            entity.setEnterTime(DateTimeHelper.getTimestamp());
        } else if(entity.getLogTypeCode().equals(Constants.LOG_TYPE_LOGOUT)) {
            entity.setOutTime(DateTimeHelper.getTimestamp());
        }

        sysLogManager.save(entity);
    }

    /**
     * 删除操作日志
     *
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysLogService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}