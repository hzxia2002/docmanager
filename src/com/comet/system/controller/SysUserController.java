package com.comet.system.controller;

import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.utils.CryptUtil;
import com.comet.core.utils.DateTimeHelper;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysDeptService;
import com.comet.system.daoservice.SysPersonService;
import com.comet.system.daoservice.SysUserService;
import com.comet.system.domain.SysDept;
import com.comet.system.domain.SysPerson;
import com.comet.system.domain.SysPersonDept;
import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysUser;
import com.comet.system.manager.SysRoleManager;
import com.comet.system.manager.SysUserManager;
import com.comet.system.utils.UserSessionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class SysUserController extends BaseCRUDActionController<SysUser> {
    @Autowired
	private SysUserService sysUserService;

    @Autowired
    private SysUserManager sysUserManager;

    @Autowired
    private SysRoleManager sysRoleManager;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysPersonService sysPersonService;

    /**
     * 查询数据
     *
     * @param page
     * @param condition
     * @return
     */
    @RequestMapping
    @ResponseBody
	public Page<SysUser> grid(Page page, String condition) {
		try {
            page = sysUserManager.queryPage(page, condition);
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
     * @param deptId
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String init(Model model, SysUser entity,Long deptId) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysUserService.get(entity.getId());
                entity.setPassword("");

                // 取得用户已有的角色
                List<SysRole> roles = sysUserManager.getUserRoles(entity.getId());

                entity.setRoleNames(sysRoleManager.getRoleIdsAsString(roles));

                if(entity.getPerson() != null && entity.getPerson().getSysPersonDepts() != null) {
                    Set<SysPersonDept> set = entity.getPerson().getSysPersonDepts();

                    for(SysPersonDept spd : set) {
                        entity.setDeptId(spd.getDept().getId());
                        entity.setDeptName(spd.getDept().getName());
                    }
                }

                this.getAllRoles(model);

            }

            if(deptId!=null){
                SysDept sysDept = sysDeptService.get(deptId);
                entity.setDeptId(sysDept.getId());
                entity.setDeptName(sysDept.getName());
            }
            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysUserEdit";
    }

    /**
     * 新增初始化
     *
     * @param model
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String initAdd(Model model, SysUser entity) throws Exception {
        try {
            if(entity == null) {
                entity = new SysUser();
            }

            Long deptId = entity.getDeptId();

            if(deptId != null) {
                SysDept dept = sysDeptService.get(deptId);
                entity.setDeptName(dept.getName());
            }

            model.addAttribute("bean", entity);
            this.getAllRoles(model);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysUserAdd";
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
        SysUser sysUser = sysUserService.get(id);

        List<SysRole> roles = sysUserManager.getUserRoles(id);

        sysUser.setRoleNames(sysRoleManager.getRoleNamesAsString(roles));


        sysUser.setRoleNames(sysRoleManager.getRoleIdsAsString(roles));

        if(sysUser.getPerson() != null && sysUser.getPerson().getSysPersonDepts() != null) {
            Set<SysPersonDept> set = sysUser.getPerson().getSysPersonDepts();

            for(SysPersonDept spd : set) {
                sysUser.setDeptId(spd.getDept().getId());
                sysUser.setDeptName(spd.getDept().getName());
            }
        }

        this.getAllRoles(model);

        model.addAttribute("bean", sysUser);
        return "view/sys_new/sysUserView";
    }


    @RequestMapping
    public String viewLogin(Model model) {
        SysUser sysUser = UserSessionUtils.getInstance().getLoginedUser();

        List<SysRole> roles = sysUserManager.getUserRoles(sysUser.getId());

        sysUser.setRoleNames(sysRoleManager.getRoleNamesAsString(roles));

        if(sysUser.getPerson() != null && sysUser.getPerson().getSysPersonDepts() != null) {
            Set<SysPersonDept> set = sysUser.getPerson().getSysPersonDepts();

            for(SysPersonDept spd : set) {
                sysUser.setDeptId(spd.getDept().getId());
                sysUser.setDeptName(spd.getDept().getName());
            }
        }

        model.addAttribute("bean", sysUser);
        return "view/sys_new/sysUserView";
    }

    /**
     * 保存
     *
     * @param response
     * @param model
     * @param entity
     * @param ids
     * @throws Exception
     */
    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysUser entity, String ids)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "person",
//                    "loginName",
//                    "password",
                    "displayName",
                    "status",
                    "reasonDesc",
                    "openDate",
                    "closeDate"
            };

            String[] personColumns = new String[]{
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
                    "isValidate",
                    "isManager",
                    "position"
            };

            SysUser target;
            SysPerson targetPerson = null;

            if (entity.getId() != null) {
                target = sysUserService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);

                // 更新密码信息
                if(StringUtils.isNotBlank(entity.getPassword())) {
                    target.setPassword(CryptUtil.encrypt(entity.getPassword()));
                }

                SysPerson person = entity.getPerson();

                if(person != null && person.getId() != null) {
                    targetPerson = sysPersonService.get(person.getId());

                    person.setName(entity.getDisplayName());
                    person.setDeptId(entity.getDeptId());

                    ReflectionUtils.copyBean(person, targetPerson, personColumns);
                    targetPerson.setDeptId(person.getDeptId());

                    sysUserManager.update(targetPerson, target, ids);
                } else if(person != null) {
                    person.setName(entity.getDisplayName());
                    person.setDeptId(entity.getDeptId());

                    sysUserManager.update(person, target, ids);
                } else {
                    sysUserManager.update(target, ids);
                }
            } else {
                target = entity;

                if(StringUtils.isNotBlank(entity.getPassword())) {
                    target.setPassword(CryptUtil.encrypt(entity.getPassword()));
                }

                SysPerson person = entity.getPerson();
                person.setName(entity.getDisplayName());
                person.setDeptId(entity.getDeptId());

                sysUserManager.save(person, target, ids);
            }

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
        sysUserManager.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    /**
     * 取得所有的角色
     *
     * @param model
     */
    protected void getAllRoles(Model model) {
        List<SysRole> list = sysRoleManager.getAllRoles();

        model.addAttribute("roleList", list);
    }

    /**
     * 修改密码
     *
     * @param model
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping
    public void modifyPassword(Model model, @ModelAttribute("bean") SysUser bean, HttpServletResponse response)
            throws Exception {
        // 取得当前用户
        SysUser user = UserSessionUtils.getInstance().getLoginedUser();

        // 校核当前密码
        String password = bean.getPassword();

        if(!password.equals(CryptUtil.decrypt(user.getPassword()))) {
            sendFailureJSON(response, "原密码不正确，请重试!");
            return;
        }

        // 校核新密码
        if(!bean.getPassword1().equals(bean.getPassword2())) {
            sendFailureJSON(response, "新密码和新密码确认不匹配!");
            return;
        }

        try {
            user.setPassword(CryptUtil.encrypt(bean.getPassword1()));
            user.setUpdateTime(DateTimeHelper.getTimestamp());
            user.setUpdateUser(user.getLoginName());

            sysUserService.update(user);

            sendSuccessJSON(response, "修改密码成功");
        } catch (Exception e) {
            log.error("error", e);
            sendErrorJSON(response, "修改密码失败，原因[" + ExceptionUtils.getRootCauseMessage(e) + "]!");
        }
    }

    public static void main(String[] args) {
        String k002AE = CryptUtil.encrypt("K002AE");
        System.out.println("k002AE = " + k002AE);
    }
}