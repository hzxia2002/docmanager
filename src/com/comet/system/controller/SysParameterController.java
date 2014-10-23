package com.comet.system.controller;

import com.comet.system.daoservice.SysParameterService;
import com.comet.system.domain.SysParameter;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
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
public class SysParameterController extends BaseCRUDActionController<SysParameter> {
    @Autowired
	private SysParameterService sysParameterService;

	@RequestMapping
    @ResponseBody
	public Page<SysParameter> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysParameter t where 1=1 " + page.getOrderByString("t.id asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysParameterService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, SysParameter entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysParameterService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysParameterEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SysParameter sysParameter = sysParameterService.get(id);

        model.addAttribute("bean", sysParameter);
        return "view/sys_new/sysParameterView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysParameter entity)
            throws Exception {
        try {
            String[] columns = new String[]{
//                    "id",
                    "code",
                    "name",
                    "value",
                    "constraint",
                    "clobvalue"
//                    "createTime",
//                    "updateTime",
//                    "createUser",
//                    "updateUser"
            };

            SysParameter target;
            if (entity.getId() != null) {
                target = sysParameterService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysParameterService.save(target);

            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysParameterService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}