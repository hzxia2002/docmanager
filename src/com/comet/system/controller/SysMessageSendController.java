package com.comet.system.controller;

import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysMessageSendService;
import com.comet.system.domain.SysMessageSend;
import com.comet.system.manager.SysMessageSendManager;
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
public class SysMessageSendController extends BaseCRUDActionController<SysMessageSend> {
    @Autowired
	private SysMessageSendService sysMessageSendService;

    @Autowired
    private SysMessageSendManager sysMessageSendManager;

    /**
     * 查询数据列表
     *
     * @param page
     * @param condition
     * @return
     */
	@RequestMapping
    @ResponseBody
	public Page<SysMessageSend> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysMessageSend t where 1=1 " + page.getOrderByString("t.id desc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysMessageSendService.findByPage(page, queryTranslate.toString());
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
    public String init(Model model, @ModelAttribute("bean")SysMessageSend entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysMessageSendService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysMessageSendEdit";
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
        SysMessageSend bean = sysMessageSendService.get(id);

        model.addAttribute("bean", bean);
        return "view/sys_new/sysMessageSendView";
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
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysMessageSend entity)
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

            SysMessageSend target;
            if (entity.getId() != null) {
                target = sysMessageSendService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysMessageSendService.save(target);

            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }
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
        sysMessageSendService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    /**
     * 重发消息
     *
     * @param response
     * @param ids
     * @throws Exception
     */
    @RequestMapping
    public void resend(HttpServletResponse response, String ids) throws Exception {
        if(StringUtils.isNotEmpty(ids)) {
            String[] idArray = StringUtils.split(ids, ",");

            sysMessageSendManager.resend(idArray);
        }

        sendSuccessJSON(response, "重发成功");
    }
}