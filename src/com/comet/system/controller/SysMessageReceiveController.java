package com.comet.system.controller;

import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysMessageReceiveService;
import com.comet.system.domain.SysMessageReceive;
import com.comet.system.manager.SysMessageReceiveManager;
import org.apache.commons.lang.exception.ExceptionUtils;
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
public class SysMessageReceiveController extends BaseCRUDActionController<SysMessageReceive> {
    @Autowired
	private SysMessageReceiveService sysMessageReceiveService;

    @Autowired
    private SysMessageReceiveManager sysMessageReceiveManager;

    /**
     * 查询列表数据
     *
     * @param page
     * @param condition
     * @return
     */
	@RequestMapping
    @ResponseBody
	public Page<SysMessageReceive> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysMessageReceive t where 1=1 " + page.getOrderByString("t.id desc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysMessageReceiveService.findByPage(page, queryTranslate.toString());
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
    public String init(Model model, @ModelAttribute("bean")SysMessageReceive entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysMessageReceiveService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysMessageReceiveEdit";
    }

    /**
     * 查看消息
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping
    public String view(Model model, Long id) {
        SysMessageReceive bean = sysMessageReceiveService.get(id);

        model.addAttribute("bean", bean);
        return "view/sys_new/sysMessageReceiveView";
    }

    /**
     * 保存消息
     *
     * @param response
     * @param model
     * @param entity
     * @throws Exception
     */
    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysMessageReceive entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "fdMessageCode",
                    "fdMessageHandlerName",
                    "fdMessageState",
                    "fdMessageDesc",
                    "fdBusinessModule",
                    "fdBusinessFunction"
            };

            SysMessageReceive target;
            if (entity.getId() != null) {
                target = sysMessageReceiveService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysMessageReceiveService.save(target);

            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }
    }

    /**
     * 删除消息
     *
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysMessageReceiveService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    /**
     * 消息解析
     *
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping
    public void doParse(HttpServletResponse response, Long id) throws Exception {
        try {
            sysMessageReceiveManager.parse(id);

            sendSuccessJSON(response, "消息解析成功");
        } catch (Exception e) {
            log.error("error", e);

            sendFailureJSON(response, "消息解析失败:" + ExceptionUtils.getRootCauseMessage(e));
        }
    }
}