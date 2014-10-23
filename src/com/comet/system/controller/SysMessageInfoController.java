package com.comet.system.controller;

import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysMessageInfoService;
import com.comet.system.domain.SysMessageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class SysMessageInfoController extends BaseCRUDActionController<SysMessageInfo> {
    @Autowired
	private SysMessageInfoService sysMessageInfoService;

    /**
     * 查询列表数据
     *
     * @param page
     * @param condition
     * @return
     */
	@RequestMapping
    @ResponseBody
	public Page<SysMessageInfo> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysMessageInfo t where 1=1 " + page.getOrderByString("t.fdMessageCode asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysMessageInfoService.findByPage(page, queryTranslate.toString());
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
    public String init(Model model, @ModelAttribute("bean")SysMessageInfo entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysMessageInfoService.get(entity.getId());

                model.addAttribute("bean", entity);
            } else {
                entity = new SysMessageInfo();
                entity.setFdMessageState(1);
                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysMessageInfoEdit";
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
        SysMessageInfo sysParameter = sysMessageInfoService.get(id);

        model.addAttribute("bean", sysParameter);
        return "view/sys_new/sysMessageInfoView";
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
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysMessageInfo entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "fdMessageCode",
                    "fdMessageHandlerName",
                    "fdMessageState",
                    "fdMessageType",
                    "fdMessageDesc",
                    "fdBusinessModule",
                    "fdBusinessFunction"
            };

            SysMessageInfo target;
            if (entity.getId() != null) {
                target = sysMessageInfoService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysMessageInfoService.save(target);

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
        sysMessageInfoService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    /**
     * 取得列表
     *
     * @param fdMessageType
     * @return
     */
    @RequestMapping
    @ResponseBody
    public List<SysMessageInfo> getList(String fdMessageType) {
        try {
            String hql = "from SysMessageInfo t where 1=1 ";

            if(StringUtils.isNotEmpty(fdMessageType)) {
                hql += " and t.fdMessageType = '" + fdMessageType + "'";
            }

            hql += " order by t.fdMessageCode asc";

            return sysMessageInfoService.find(hql);
        } catch (Exception e) {
            log.error("error", e);

            return null;
        }
    }
}