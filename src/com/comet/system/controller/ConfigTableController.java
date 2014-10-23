package com.comet.system.controller;

import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.orm.log.TableLogUtils;
import com.comet.core.security.user.BaseUser;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.core.utils.DateTimeHelper;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.ConfigTableService;
import com.comet.system.domain.ConfigTable;
import com.comet.system.manager.ConfigTableManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class ConfigTableController extends BaseCRUDActionController<ConfigTable> {
    @Autowired
	private ConfigTableService configTableService;

    @Autowired
    private ConfigTableManager configTableManager;

    /**
     * 查询数据
     *
     * @param page
     * @param condition
     * @return
     */
	@RequestMapping
    @ResponseBody
	public Page<ConfigTable> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from ConfigTable t where 1=1 " + page.getOrderByString("t.tableName desc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = configTableService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, ConfigTable entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = configTableService.get(entity.getId());
            } else {
                entity = new ConfigTable();
            }

            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/configTableEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        ConfigTable configTable = configTableService.get(id);

        model.addAttribute("bean", configTable);
        return "view/sys_new/configTableView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") ConfigTable entity)
            throws Exception {
        try {
            String[] columns = new String[]{
//                    "id",
                    "tableName",
                    "className",
                    "isLog",
                    "extendXml"
//                    "createTime",
//                    "updateTime",
//                    "updateUser",
//                    "createUser"
            };

            BaseUser loginUser = SpringSecurityUtils.getCurrentUser();

            ConfigTable target;
            if (entity.getId() != null) {
                target = configTableService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);

                target.setUpdateTime(DateTimeHelper.getTimestamp());
                target.setUpdateUser(loginUser.getLoginName());
            } else {
                target = entity;

                target.setCreateTime(DateTimeHelper.getTimestamp());
                target.setCreateUser(loginUser.getLoginName());
            }

            configTableService.save(target);

            // 刷新缓存
            refresh();

            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        configTableService.delete(id);

        // 刷新缓存
        refresh();

        sendSuccessJSON(response, "删除成功");
    }

    /**
     * 刷新配置缓存信息
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping
    public void refresh(HttpServletResponse response) throws Exception {
        refresh();

        sendSuccessJSON(response, "刷新成功");
    }

    protected void refresh() {
        Map map = configTableManager.getAllConfig();
        TableLogUtils.setTableConfigMap(map);
    }
}