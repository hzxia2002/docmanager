package com.comet.system.controller;

import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.system.daoservice.SysMessageReceiveBackService;
import com.comet.system.domain.SysMessageReceiveBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class SysMessageReceiveBackController extends BaseCRUDActionController<SysMessageReceiveBack> {
    @Autowired
	private SysMessageReceiveBackService sysMessageReceiveBackService;

    /**
     * 查询
     *
     * @param page
     * @param condition
     * @return
     */
	@RequestMapping
    @ResponseBody
	public Page<SysMessageReceiveBack> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysMessageReceiveBack t where 1=1 " + page.getOrderByString("t.id desc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysMessageReceiveBackService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
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
        SysMessageReceiveBack bean = sysMessageReceiveBackService.get(id);

        model.addAttribute("bean", bean);
        return "view/sys_new/sysMessageReceiveBackView";
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
        sysMessageReceiveBackService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}