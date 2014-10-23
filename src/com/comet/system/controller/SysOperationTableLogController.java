package com.comet.system.controller;

import com.comet.system.daoservice.SysOperationTableLogService;
import com.comet.system.domain.SysOperationTableLog;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.orm.log.TableLog;
import com.comet.core.orm.log.TableLogUtils;
import com.comet.core.utils.ReflectionUtils;
import org.apache.commons.lang.StringUtils;
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
public class SysOperationTableLogController extends BaseCRUDActionController<SysOperationTableLog> {
    @Autowired
	private SysOperationTableLogService sysOperationTableLogService;

	@RequestMapping
    @ResponseBody
	public Page<SysOperationTableLog> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysOperationTableLog t where 1=1 " + page.getOrderByString("t.id desc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysOperationTableLogService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, SysOperationTableLog entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysOperationTableLogService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysOperationTableLogEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SysOperationTableLog sysOperationTableLog = sysOperationTableLogService.get(id);

        if(StringUtils.isNotBlank(sysOperationTableLog.getLogXml())) {
            TableLog tableLog = TableLogUtils.fromXml(sysOperationTableLog.getLogXml());

            model.addAttribute("tableLog", tableLog);
        }

        model.addAttribute("bean", sysOperationTableLog);

        return "view/sys_new/sysOperationTableLogView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysOperationTableLog entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "table",
                    "logXml",
                    "ipAddress",
                    "createTime",
                    "updateTime",
                    "updateUser",
                    "createUser"
            };

            SysOperationTableLog target;
            if (entity.getId() != null) {
                target = sysOperationTableLogService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysOperationTableLogService.save(target);

            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysOperationTableLogService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}