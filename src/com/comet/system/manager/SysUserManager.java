package com.comet.system.manager;

import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.BeanMapConvertUtils;
import com.comet.core.utils.CryptUtil;
import com.comet.core.utils.DateTimeHelper;
import com.comet.system.daoservice.SysPersonService;
import com.comet.system.daoservice.SysPrivilegeService;
import com.comet.system.daoservice.SysRoleService;
import com.comet.system.daoservice.SysUserPrivilegeService;
import com.comet.system.daoservice.SysUserRoleService;
import com.comet.system.daoservice.SysUserService;
import com.comet.system.domain.SysDept;
import com.comet.system.domain.SysPerson;
import com.comet.system.domain.SysPersonDept;
import com.comet.system.domain.SysPrivilege;
import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysUser;
import com.comet.system.domain.SysUserPrivilege;
import com.comet.system.domain.SysUserRole;
import com.comet.system.utils.CommonUtils;
import com.comet.system.utils.Constants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * </p>
 * User: <a href="mailto:hzxia2002@gmail.com">Jackie</a> Date: 2009-11-23 Time:
 * 23:40:36 Version: 1.0
 */
@Service
public class SysUserManager {
    private static Logger log = LoggerFactory.getLogger(SysUserManager.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPersonService sysPersonService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleManager sysRoleManager;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserPrivilegeService sysUserPrivilegeService;

    @Autowired
    private SysPrivilegeService sysPrivilegeService;

    @Autowired
    private SysPersonManager sysPersonManager;

    /**
     * 通过用户名取得用户
     *
     * @param userName
     * @return
     */
    public SysUser getByUsername(String userName) {
        List<SysUser> userList = sysUserService.findByProperty("loginName",
                userName);

        if(userList != null && userList.size() > 0) {
            return userList.get(0);
        }

        return null;
    }

    /**
     * 以Map形式返回用户拥有的角色
     *
     * @param userId
     * @return
     */
    public Map<String, SysRole> getUserRolesAsMap(Long userId) {
        Map<String, SysRole> map = new HashMap<String, SysRole>();
        List<SysRole> list = this.getUserRoles(userId);

        for(SysRole role : list) {
            map.put(role.getCode(), role);
        }

        return map;
    }

    /**
     * 保存用户信息
     *
     * @param bean  用户
     * @param roles 角色
     */
    public void save(SysUser bean, String roles) {
        boolean hasId = (bean.getId() != null);

        sysUserService.save(bean);

        if (roles != null && roles.length() > 0) {
            String[] roleId = StringUtils.split(roles, ",");

            if (roleId != null && roleId.length > 0) {
                for (int i = 0; i < roleId.length; i++) {
                    SysRole role = sysRoleService.get(new Long(roleId[i]));
                    SysUserRole userRole = new SysUserRole();

                    userRole.setUser(bean);
                    userRole.setRole(role);
                    sysUserRoleService.save(userRole);
                }
            }
        }
    }

    /**
     * 更新用户信息
     *
     * @param bean  用户
     * @param roles 角色
     */
    public void update(SysUser bean, String roles) {
        Iterator<SysUserRole> it = bean.getSysUserRoles().iterator();

        if (it.hasNext()) {
            while (it.hasNext()) {
                sysUserRoleService.delete((SysUserRole) it.next());
            }

            sysUserRoleService.getSession().flush();
        }

        this.save(bean, roles);
    }

    /**
     * 保存用户信息
     *
     * @param person 人员
     * @param bean  用户
     * @param roles 角色
     */
    public void save(SysPerson person, SysUser bean, String roles) {
        boolean hasId = (bean.getId() != null);

        // 保存人员信息
        sysPersonManager.save(person);

        // 保存用户信息
        bean.setUserId(CommonUtils.getUserId(person.getId()));
        bean.setPerson(person);
        sysUserService.save(bean);

        if (roles != null && roles.length() > 0) {
            String[] roleId = StringUtils.split(roles, ",");

            if (roleId != null && roleId.length > 0) {
                for (int i = 0; i < roleId.length; i++) {
                    SysRole role = sysRoleService.get(new Long(roleId[i]));
                    SysUserRole userRole = new SysUserRole();

                    userRole.setUser(bean);
                    userRole.setRole(role);
                    sysUserRoleService.save(userRole);
                }
            }
        }
    }

    /**
     * 更新用户信息
     *
     * @param bean  用户
     * @param roles 角色
     */
    public void update(SysPerson person, SysUser bean, String roles) {
        sysPersonManager.save(person);

        bean.setPerson(person);

        Iterator<SysUserRole> it = bean.getSysUserRoles().iterator();

        if (it.hasNext()) {
            while (it.hasNext()) {
                sysUserRoleService.delete((SysUserRole) it.next());
            }

            sysUserRoleService.getSession().flush();
        }

        this.save(bean, roles);
    }

    /**
     * 取得人员所在的部门
     *
     * @param id 用户id
     * @return 部门
     */
    public SysDept getUserDept(Long id) {
        SysUser user = sysUserService.get(id);

        SysPerson person = user.getPerson();

        if (person != null) {
            Set<SysPersonDept> depts = person.getSysPersonDepts();

            if (depts != null && depts.size() > 0) {
                Iterator<SysPersonDept> it = depts.iterator();

                return it.next().getDept();
            }
        }

        return null;
    }

    /**
     * 按照条件查询用户
     *
     * @param page
     * @param condition
     * @return
     */
	public Page<SysUser> queryPage(Page page, String condition) {
        try {
            page.setAutoCount(false);

            String hql = "select count(distinct t.id) from SysUser t, SysPersonDept t1, SysDept t2 " +
                    " where t.person.id=t1.person.id and t1.dept.id=t2.id ";
            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);
            List list0 = sysUserService.find(queryTranslate.toString());

            if(list0 != null && list0.size() > 0) {
                Object obj = list0.get(0);
                page.setRecords(Integer.parseInt(obj.toString()));
            }

            hql = "select distinct(t) from SysUser t, SysPersonDept t1, SysDept t2 " +
                    " where t.person.id=t1.person.id and t1.dept.id=t2.id "
                    + page.getOrderByString("t.id asc");

            queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysUserService.findByPage(page, queryTranslate.toString());

            List<SysUser> list = page.getRows();

            if(list != null && list.size() > 0) {
                List<SysUser> ret = new ArrayList<SysUser>();
                for(SysUser user : list) {
                    // 取得角色名称
                    List<SysRole> roles = getUserRoles(user.getId());

                    String roleNames = sysRoleManager.getRoleNamesAsString(roles);
                    user.setRoleNames(roleNames);

                    ret.add(user);
                }

                page.setRows(ret);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return  page;
    }

    /**
     * 删除用户信息 1) 删除用户的角色关联信息； 2)删除用户的权限信息；3) 删除用户信息。
     *
     * @param id 用户ID
     */
    public void delete(Long id) throws Exception {
        try {
            SysUser bean = sysUserService.get(id);
            Iterator<SysUserRole> it = bean.getSysUserRoles().iterator();
            Iterator<SysUserPrivilege> pit = bean.getSysUserPrivileges().iterator();

            // 删除用户角色
            while (it.hasNext()) {
                SysUserRole entity = it.next();

                sysUserRoleService.delete(entity);
            }

            // 删除用户权限
            while (pit.hasNext()) {
                SysUserPrivilege entity = pit.next();

                sysUserPrivilegeService.delete(entity);
            }

            sysUserService.delete(id);
        } catch (Exception e) {
            log.error("error", e);
            throw e;
        }
    }

    /**
     * 根据部门查询用户
     *
     * @return 人员列表
     */
    public List<SysUser> getUserByDept(Long deptId) {
        StringBuffer hql = new StringBuffer("from SysUser t where ");

        // 只查询未离职的用户
//        if (isOver != null && isOver) {
//            hql.append(" t.person.isOver = " + isOver);
//        } else {
//            hql.append(" (t.person.isOver = null or t.person.isOver = "
//                    + isOver + ") ");
//        }

        hql
                .append(" and t.person in (select person from SysPersonDept t2 where t2.dept = "
                        + deptId + " ) order by t.id ");

        return sysUserService.find(hql.toString());
    }

    /**
     * 部门下否有用户
     *
     * @param deptId 部门ID
     * @return 是否有该人员
     */
    public Boolean isDeptHasUser(Long deptId) {
        boolean ret = false;

        List<SysUser> list = this.getUserByDept(deptId);

        if (list != null && list.size() > 0) {
            ret = true;
        }

        return ret;
    }

    /**
     * 取得用户特殊授权数据
     *
     * @param id 用户ID
     * @return 权限列表
     */
    public List<SysPrivilege> getUserSpecialPrivileges(Long id) {
        String sql = "select t1 from SysUserPrivilege t, SysPrivilege t1"
                + " where t.privilege = t1.id and t.user = " + id;

        return sysPrivilegeService.find(sql);
    }

    /**
     * 取得用户特殊授权数据
     *
     * @param id 用户ID
     * @return 用户权限列表
     */
    public List<SysUserPrivilege> getSpecialPrivileges(Long id) {
        String sql = "from SysUserPrivilege t where t.user = " + id;

        return sysUserPrivilegeService.find(sql);
    }

    /**
     * 取得用户角色授权权限
     *
     * @param id 用户ID
     * @return 用户权限列表
     */
    public List<SysPrivilege> getUserRolePrivileges(Long id) {
        String sql = "select distinct(t2) from SysRolePrivilege t1, "
                + "SysPrivilege t2, SysUserRole t3";
        sql += " where t1.privilege = t2.id";
        sql += " and t1.role = t3.role and t3.user = " + id;

        return sysPrivilegeService.find(sql);
    }

    /**
     * 取得用户的所有角色
     *
     * @param id
     * @return
     */
    public List<SysRole> getUserRoles(Long id) {
        String sql = "select t.role from SysUserRole t ";
        sql += " where t.user.id = " + id;
        sql += " order by t.role.code asc";

        return sysRoleService.find(sql);
    }

    /**
     * 保存用户授权数据
     *
     * @param list   SysPrivilege List
     * @param userId userId
     */
    public void saveUserPrivilege(List<SysPrivilege> list, Long userId) {
        // 获取用户信息
        SysUser user = sysUserService.get(userId);

        for (SysPrivilege bean : list) {
            // 删除已经有的授权信息
            String hql = "from SysUserPrivilege where user = " + userId
                    + " and privilege=" + bean.getId();

            List<SysUserPrivilege> tmpList = sysUserPrivilegeService.find(hql);

            for (SysUserPrivilege tmp : tmpList) {
                sysUserPrivilegeService.delete(tmp);
            }
            sysUserPrivilegeService.getSession().flush();

            // 保存新的授权信息
            SysUserPrivilege userPrivilege = new SysUserPrivilege();
            userPrivilege.setUser(user);
            userPrivilege.setPrivilege(sysPrivilegeService.get(bean.getId()));

//            if (bean.getIsDeny() == 1) {
//                userPrivilege.setIsDeny(false);
//                sysUserPrivilegeService.save(userPrivilege);
//            } else if (bean.getIsDeny() == 2) {
//                userPrivilege.setIsDeny(true);
//                sysUserPrivilegeService.save(userPrivilege);
//            }
        }
    }

    /**
     * 通过用户名取得用户信息
     *
     * @param user
     * @return
     */
    public SysUser getUserByLoginName(SysUser user) {
        String hql = "from SysUser t where t.loginName = '" + user.getLoginName() + "'";

        List<SysUser> list = sysUserService.find(hql);

        if(list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }


    /**
     * 根据昵称取得人员信息
     *
     * @param nickName
     * @return
     */
    public SysPerson getPersonByNickName(String nickName) {
        if(nickName == null) {
            return null;
        }

        String hql = "select t.person from SysUser t where t.nickName = '" + nickName + "'";

        List list = sysUserService.find(hql);

        if(list != null && list.size() > 0) {
            return (SysPerson)list.get(0);
        }

        return null;
    }

    /**
     * 根据nickName获取用户信息
     *
     * @param nickName
     * @return
     */
    public SysUser getUserByNickName(String nickName) {
        String hql = "from SysUser t where t.nickName = '" + nickName + "' or t.loginName = '" + nickName + "'";

        List list = sysUserService.find(hql);

        if(list != null && list.size() > 0) {
            return (SysUser)list.get(0);
        }

        return null;
    }

    /**
     * 用户注册
     *
     * @param map
     * @return
     */
    public SysUser saveUser(Map map) {
        // 保存SysPerson信息
        SysPerson person = new SysPerson();
        person = BeanMapConvertUtils.mapToBean(map, SysPerson.class);
        person.setCreateTime(DateTimeHelper.getTimestamp());
        person.setCreateUser("system");

        sysPersonService.save(person);

        // 保存用户信息
        SysUser sysUser = new SysUser();

        sysUser = BeanMapConvertUtils.mapToBean(map, SysUser.class);

        sysUser.setPerson(person);
        sysUser.setLoginName(sysUser.getNickName());
        sysUser.setCreateTime(DateTimeHelper.getTimestamp());
        sysUser.setStatus("1");
        // 设置密码
        sysUser.setPassword(CryptUtil.encrypt(sysUser.getPassword()));
//        sysUser.setPassword(sysUser.getPassword());

        // 设置用户id
//        sysUser.setUserId(CommonUtils.generateUserId());

        sysUser.setCreateUser("system");

        sysUserService.save(sysUser);

        return sysUser;
    }

    /**
     * 通过USER_ID获取用户信息
     *
     * @param userId
     * @return
     */
    public SysUser getUserByUserId(String userId) {
        String hql = "from SysUser t where t.userId = '" + userId + "'";

        List list = sysUserService.find(hql);

        if(list != null && list.size() > 0) {
            return (SysUser)list.get(0);
        }

        return null;
    }
    
    
    public  BigInteger getUserAmount(){
    	String sql=" select count(*) from sys_user";
    	List list = sysUserService.findBySql(sql);
    	if(list!=null&&!list.isEmpty())
              return (BigInteger) list.get(0);
    	return null;
    }

    /**
     * 通过Email获取用户信息
     *
     * @param email
     * @return
     */
    public SysUser getUserByEmail(String email) {
        String hql = "from SysUser t where t.person.email = '" + email + "'";

        List list = sysUserService.find(hql);

        if(list != null && list.size() > 0) {
            return (SysUser)list.get(0);
        }

        return null;
    }

    /**
     * 根据用户昵称/Email/登录名
     *
     * @param name
     * @return
     */
    public SysUser getUserByEmailOrUsername (String name) {
        String hql = "from SysUser t where (t.person.email = '" + name + "'";
        hql += " or t.loginName='" + name + "' or t.nickName = '" + name + "')";

        List list = sysUserService.find(hql);

        if(list != null && list.size() > 0) {
            return (SysUser)list.get(0);
        }

        return null;
    }

    /**
     * 通过手机号获取用户信息
     *
     * @param mobile
     * @return
     */
    public SysUser getUserByMobile(String mobile) {
        String hql = "from SysUser t where t.person.mobile = '" + mobile + "' and t.status = '" + Constants.USER_STATUS_NORMAL + "'";

        List list = sysUserService.find(hql);

        if(list != null && list.size() > 0) {
            return (SysUser)list.get(0);
        }

        return null;
    }

    /**
     * 检查身份证号的唯一性
     *
     * @param card
     * @return
     */
    public SysUser getUserByCard(String card) {
        String hql = "from SysUser t where t.person.card = '" + card + "'";

        List<SysUser> list = sysUserService.find(hql);

        if(list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    /**
     * 保存注册用户信息
     *
     * @param user
     */
    public void saveRegUser(SysUser user) {
        // 保存用户
        SysPerson person = new SysPerson();

        person.setIsValid(Boolean.TRUE);
        person.setCard(user.getCard());
        person.setCardType(user.getCardType());
        person.setMobile(user.getMobile());
        person.setName(user.getDisplayName());
        person.setIsValidate(Boolean.FALSE);

        sysPersonService.save(person);

        // 保存用户
        // 设置会员卡号
        user.setUserId(CommonUtils.getUserId(person.getId()));
        user.setStatus(Constants.USER_STATUS_NORMAL);
        user.setOpenDate(new Date(System.currentTimeMillis()));
        user.setPassword(CryptUtil.encrypt(user.getPassword()));
        user.setPerson(person);
        user.setUserType(Constants.MEMBER);

        sysUserService.save(user);
    }
}
