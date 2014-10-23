package com.comet.system.controller;

import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysSmsSendService;
import com.comet.system.domain.SysSmsSend;
import com.comet.system.sms.SmsSender;
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
public class SysSmsSendController extends BaseCRUDActionController<SysSmsSend> {
    private static Log log = LogFactory.getLog(SysSmsSendController.class);

    @Autowired
	private SysSmsSendService sysSmsSendService;

    @Autowired
    private SmsSender smsSender;

    /**
     * 查询列表数据
     *
     * @param page
     * @param condition
     * @return
     */
	@RequestMapping
    @ResponseBody
	public Page<SysSmsSend> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysSmsSend t where 1=1 ";

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysSmsSendService.findByPage(page, queryTranslate.toString());
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
    public String init(Model model, SysSmsSend entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysSmsSendService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysSmsSendEdit";
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
        SysSmsSend sysSmsSend = sysSmsSendService.get(id);

        model.addAttribute("bean", sysSmsSend);
        return "view/sys_new/sysSmsSendView";
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
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysSmsSend entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "user",
                    "content",
                    "receiver",
                    "createTime",
                    "createUser",
                    "sendTime",
                    "sendResult",
                    "sendStatus"
            };

            SysSmsSend target;
            if (entity.getId() != null) {
                target = sysSmsSendService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysSmsSendService.save(target);
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
        sysSmsSendService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    /**
     * 短信发送测试
     *
     * @param response
     * @param model
     * @param entity
     */
    @RequestMapping
    public void send(HttpServletResponse response, Model model, @ModelAttribute("bean") SysSmsSend entity) {
        try {
            smsSender.send(entity.getContent(), entity.getReceiver(), "030000002_001");
        } catch (Exception e) {
            logger.error("error", e);
            super.processException(response, e);

            return;
        }

        sendSuccessJSON(response, "发送成功!");
    }
}