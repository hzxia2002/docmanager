package com.comet.system.controller;

import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysEmailSendService;
import com.comet.system.domain.SysEmailSend;
import com.comet.system.mail.MailSendService;
import org.apache.commons.lang.StringUtils;
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
public class SysEmailSendController extends BaseCRUDActionController<SysEmailSend> {
    private static Log log = LogFactory.getLog(SysEmailSendController.class);

    @Autowired
	private SysEmailSendService sysEmailSendService;

    @Autowired
    private MailSendService mailSendService;

    /**
     * 查询数据
     *
     * @param page
     * @param condition
     * @return
     */
	@RequestMapping
    @ResponseBody
	public Page<SysEmailSend> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysEmailSend t where 1=1 " ;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysEmailSendService.findByPage(page, queryTranslate.toString());
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
    public String init(Model model, SysEmailSend entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysEmailSendService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysEmailSendEdit";
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
        SysEmailSend sysEmailSend = sysEmailSendService.get(id);

        model.addAttribute("bean", sysEmailSend);
        return "view/sys_new/sysEmailSendView";
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
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysEmailSend entity)
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

            SysEmailSend target;
            if (entity.getId() != null) {
                target = sysEmailSendService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysEmailSendService.save(target);
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
        sysEmailSendService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    /**
     * 发送测试
     *
     * @param response
     * @param model
     * @param entity
     * @throws Exception
     */
    @RequestMapping
    public void send(HttpServletResponse response, Model model, @ModelAttribute("bean") SysEmailSend entity)
            throws Exception {
        try {
            String receiver = entity.getReceiver();

            String[] receivers = StringUtils.split(receiver, ";");

            for(String tmp : receivers) {
                mailSendService.sendMailHtml(tmp, "", entity.getTitle(), entity.getContent());
            }
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
            return;
        }

        sendSuccessJSON(response, "发送成功");
    }
}