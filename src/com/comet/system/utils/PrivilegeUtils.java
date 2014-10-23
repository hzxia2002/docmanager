package com.comet.system.utils;

import com.comet.system.manager.SysUserManager;
import com.comet.core.utils.Constant;
import com.comet.core.utils.SpringUtils;
import com.comet.system.domain.SysPrivilege;
import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysUser;
import com.comet.system.domain.SysUserPrivilege;
import com.comet.system.domain.SysUserRole;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Project:bcscmis
 *
 * <p>
 * 数据授权处理工具类
 * </p>
 *
 * Create On 2010-1-17 下午11:40:43
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
public class PrivilegeUtils {
	/**
	 * 取得项目数据授权过滤条件
	 *
	 * @param prefix
	 * @return 过滤条件
	 */
	public static String getProjectCondition(String prefix) {
		boolean isTag0 = false;
		boolean isTag1 = false;

		SysUser loginUser = UserSessionUtils.getInstance().getLoginedUser();

		Iterator<SysUserRole> roles = null; //loginUser.getSysUserRoles().iterator();

		// 根据角色名称进行判断
		while (roles.hasNext()) {
			SysRole role = roles.next().getRole();
			String code = StringUtils.defaultIfEmpty(role.getCode(), "");

			if (code.equals(Constant.ROLE_PROJECT_TAG_0)) {
				isTag0 = true;
			} else if (code.equals(Constant.ROLE_PROJECT_TAG_1)) {
				isTag1 = true;
			}
		}

		if (isTag0 && isTag1) {
			return " ";
		} else if (isTag0) {
			return " and " + prefix + ".isTag = 0 ";
		} else if (isTag1) {
			return " and " + prefix + ".isTag = 1 ";
		} else {
			return " and 1<>1 ";
		}
	}

	/**
	 * 取得项目数据授权过滤条件
	 *
	 * @return 过滤条件
	 */
	public static String getProjectCondition() {
		return getProjectCondition("");
	}

	/**
	 * 将权限List转成Map
	 *
	 * @param list
	 *            权限List
	 * @return 权限Map
	 */
	public static Map<Long, SysPrivilege> listToMap(List<SysPrivilege> list) {
		Map<Long, SysPrivilege> ret = new HashMap<Long, SysPrivilege>();

		if (list != null && list.size() > 0) {
			for (SysPrivilege bean : list) {
				ret.put(bean.getId(), bean);
			}
		}

		return ret;
	}

	/**
	 * 将权限List转成Map
	 *
	 * @param list
	 *            权限List
	 * @return 权限Map
	 */
	public static Map<Long, SysUserPrivilege> userPrivilegeListToMap(
			List<SysUserPrivilege> list) {
		Map<Long, SysUserPrivilege> ret = new HashMap<Long, SysUserPrivilege>();

		if (list != null && list.size() > 0) {
			for (SysUserPrivilege bean : list) {
				ret.put(bean.getPrivilege().getId(), bean);
			}
		}

		return ret;
	}

    /**
     * 取得用户角色
     *
     * @return
     */
    public static Map<String, SysRole> getUserRoles() {
        SysUser loginUser = UserSessionUtils.getInstance().getLoginedUser();
        SysUserManager sysUserManager = (SysUserManager)SpringUtils.getBean("sysUserManager");

        Map<String, SysRole> map = sysUserManager.getUserRolesAsMap(loginUser.getId());

        return map;
    }

    /**
     * 是否系统管理员
     *
     * @return
     */
    public static boolean isSysAdmin() {
        Map<String, SysRole> map = getUserRoles();

        if(map.containsKey(Constants.ROLE_SYS_ADMIN)) {
            return true;
        }

        return false;
    }

    /**
     * 是否普通用户
     *
     * @return
     */
    public static boolean isUser() {
        Map<String, SysRole> map = getUserRoles();

        if(map.containsKey(Constants.ROLE_USER)) {
            return true;
        }

        return false;
    }
}
