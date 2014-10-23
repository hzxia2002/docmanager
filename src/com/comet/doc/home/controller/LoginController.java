package com.comet.doc.home.controller;

import com.comet.core.security.user.BaseUser;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.system.controller.SysLogController;
import com.comet.system.domain.SysLog;
import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysUser;
import com.comet.system.manager.SysUserManager;
import com.comet.system.utils.Constants;
import com.comet.system.utils.PrivilegeUtils;
import com.comet.system.utils.UserSessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 * 登录后页面跳转处理处理类
 * </p>
 * User: Hzxia
 * Date: 2012-2-20
 * Time: 13:33:50
 */
@Controller
public class LoginController extends SysLogController {
    @Autowired
    private SysUserManager sysUserManager;

    /**
     * <p></p>
     * 登录后页面跳转处理;<br/>
     * 1、如果是系统管理员，直接跳转后台管理页面;<br/>
     * 2、如果是普通用户，直接跳转普通用户查看页面;<br/>
     * </p>
     * @param model
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping
    public String dispatch(Model model, HttpSession session,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        BaseUser loginUser = SpringSecurityUtils.getCurrentUser();
        String url = "/index.jsp";

        if(loginUser != null) {
            // 取得当前登录用户
            SysUser sysUser = UserSessionUtils.getInstance().getLoginedUser();
        
            if(StringUtils.equals(sysUser.getUserType(),com.comet.doc.utils.Constants.USER_TYPE_MEMBER)) {
                url = "/portal/main.jsp";
            } else {
                Map<String, SysRole> map = sysUserManager.getUserRolesAsMap(loginUser.getId());
                
                if (PrivilegeUtils.isSysAdmin()) {
                    url = "/main.jsp";
                }
            }
        }

        System.out.println("url = " + url);
        // 记录系统日志
        SysLog sysLog = new SysLog();
        sysLog.setPageUrl(url);
        sysLog.setLogTypeCode(Constants.LOG_TYPE_LOGIN);

        super.saveLog(request, sysLog);

        model.addAttribute("url", url);
        session.setAttribute("defaultUrl", url);
        

//        Cookie cookie = new Cookie("JSESSIONID", request.getSession().getId());
//        response.addCookie(cookie);

//        response.sendRedirect("http://10.15.69.238" + request.getContextPath() + url);

        return "login_dispatch";
    }
}
