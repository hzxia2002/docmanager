package com.comet.system.controller;

import com.comet.system.daoservice.SysUserService;
import com.comet.system.domain.SysUser;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.security.count.CountUser;
import com.comet.core.ui.grid.Grid;
import com.comet.system.manager.OnlineUserManager;
import com.comet.system.manager.SysPrivilegeManager;
import com.comet.system.manager.SysUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Project:bcscmis
 * <p/>
 * <p>
 * 用户管理Controller
 * </p>
 * <p/>
 * Create On 2009-12-22 下午01:37:33
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Controller
public class SysOnlineUserController extends BaseCRUDActionController<SysUser> {
    SysUserService sysUserService;

    @Autowired
    private SysUserManager sysUserManager;

    @Autowired
    private SysPrivilegeManager sysPrivilegeManager;

    @Autowired
    private OnlineUserManager onlineUserManager;

    /**
     * 设置Service
     *
     * @param sysUserService Service
     */
    @Autowired
    public void setEntityService(SysUserService sysUserService) {
        super.setEntityService(sysUserService);
        this.sysUserService = sysUserService;
    }

    /**
     * followingBar
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String followingBar(Model model) throws Exception {
        try {
            return "main/followingBar";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 在线用户列表
     * @param request
     * @param start
     * @param limit
     * @param response
     * @throws Exception
     */
    @RequestMapping
    public void gridDataCustom(HttpServletRequest request, @RequestParam int start, @RequestParam int limit, HttpServletResponse response) throws Exception {
        int pageNo = start / limit + 1;

        Map usersessions = (Map) request.getSession().getServletContext().getAttribute("usersessions");
        List<CountUser> countUsers = new ArrayList();
        synchronized (usersessions) {
            Iterator it = usersessions.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                CountUser tmp = (CountUser) usersessions.get(key);
                if (!"anonymouse".equals(tmp.getUsername())) {
                    countUsers.add(tmp);
                }
            }
        }
        Page<CountUser> page = new Page<CountUser>(pageNo, limit, countUsers);
        List<Map> rows = onlineUserManager.getRows(page.getRows());
        String s = Grid.toJSON(rows);
        sendJSON(response, s);
    }
}