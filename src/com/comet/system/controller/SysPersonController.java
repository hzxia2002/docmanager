package com.comet.system.controller;

import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysPersonService;
import com.comet.system.domain.SysDept;
import com.comet.system.domain.SysPerson;
import com.comet.system.manager.SysPersonManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class SysPersonController extends BaseCRUDActionController<SysPerson> {
    @Autowired
	private SysPersonService sysPersonService;

    @Autowired
    private SysPersonManager sysPersonManager;

	@RequestMapping
    @ResponseBody
	public Page<SysPerson> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysPerson t where 1=1 " + page.getOrderByString("t.id asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysPersonService.findByPage(page, queryTranslate.toString());

            if(page.getRows() != null && page.getRows().size() > 0) {
                List<SysPerson> list = page.getRows();
                List<SysPerson> ret = new ArrayList<SysPerson>();

                for(SysPerson person : list) {
                    if(person.getSysPersonDepts() != null && person.getSysPersonDepts().size() > 0) {
                        SysDept dept = person.getSysPersonDepts().iterator().next().getDept();

                        person.setDeptId(dept.getId());
                        person.setDeptName(dept.getName());
                    }

                    ret.add(person);
                }

                page.setRows(ret);
            }
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String initAdd(Model model, SysPerson entity) throws Exception {
        try {
            if(entity == null) {
                entity = new SysPerson();
            }

            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysPersonEdit";
    }

    @RequestMapping
    public String init(Model model, SysPerson entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysPersonManager.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysPersonEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SysPerson sysPerson = sysPersonManager.get(id);

        model.addAttribute("bean", sysPerson);
        return "view/sys/sysPersonView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysPerson entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "code",
                    "name",
                    "card",
                    "sex",
                    "mobile",
                    "officeTel",
                    "faxTel",
                    "email",
                    "zipcode",
                    "msnCode",
                    "qqCode",
                    "memo",
                    "workSiteId",
                    "deptId",
                    "isValid",
                    "isManager",
                    "position"
            };

            SysPerson target;
            if (entity.getId() != null) {
                target = sysPersonService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);

                sysPersonManager.update(target);
            } else {
                target = entity;

                sysPersonManager.save(target);
            }

            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysPersonManager.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    @RequestMapping
    @ResponseBody
    public List<SysPerson> getPersonList(Long deptId) {
        return sysPersonManager.getPersonByDept(deptId, true);
    }

    @RequestMapping
    @ResponseBody
    public List<SysPerson> getPersonByWorkSite(Long workSiteId) {
        return sysPersonManager.getPersonByWorkSite(workSiteId, true);
    }


}