package com.comet.system.manager;

import com.comet.core.utils.StringHelper;
import com.comet.system.daoservice.SysPrivilegeService;
import com.comet.system.daoservice.SysRolePrivilegeService;
import com.comet.system.daoservice.SysRoleService;
import com.comet.system.daoservice.SysUserRoleService;
import com.comet.system.domain.SysPrivilege;
import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysRolePrivilege;
import com.comet.system.domain.SysUserRole;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Project:bcscmis
 *
 * <p>
 * 角色管理逻辑处理类
 * </p>
 *
 * Create On 2010-1-17 下午08:27:48
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Service
public class SysRoleManager {
	@Autowired
	private SysPrivilegeService sysPrivilegeService;

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysRolePrivilegeService sysRolePrivilegeService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

	/**
	 * 取得角色权限信息
	 *
	 * @param roleId
	 *            角色id
	 * @return 权限列表
	 */
	public List<SysPrivilege> getRolePrivileges(Long roleId) {
		String hql = "select t2 from SysRolePrivilege t1, SysPrivilege t2 ";
		hql += " where t1.privilege=t2.id and t1.role = " + roleId;
		hql += " order by t2.id asc";

		return sysPrivilegeService.find(hql);
	}

    /**
     * 取得角色权限
     *
     * @param roleId
     * @return
     */
    public Map<Long, SysPrivilege> getRolePrivilegesAsMap(Long roleId) {
        Map<Long, SysPrivilege> map = new HashMap<Long, SysPrivilege>();

        List<SysPrivilege> list = this.getRolePrivileges(roleId);

        for(SysPrivilege tmp : list) {
            map.put(tmp.getId(), tmp);
        }

        return map;
    }

	/**
	 * 保存角色权限
	 *
	 * @param ids
	 *            权限id，通过","分割
	 * @param roleId
	 *            角色id
	 */
	public void saveRolePrivilege(String ids, Long roleId) {
		String[] pids = StringUtils.split(ids, ",");
		SysRole role = sysRoleService.get(roleId);

		// 删除该角色已有的授权信息
		Iterator<SysRolePrivilege> it = role.getSysRolePrivileges().iterator();

		while(it.hasNext()) {
			sysRolePrivilegeService.delete(it.next());
			sysRolePrivilegeService.getSession().flush();
		}

		if (pids != null && pids.length > 0) {
			for (String sid : pids) {
                if(!sid.equals("-1")) {
                    Long id = new Long(sid);

                    SysPrivilege privilege = sysPrivilegeService.get(id);

                    if (privilege != null) {
                        SysRolePrivilege entity = new SysRolePrivilege();
                        entity.setRole(role);
                        entity.setPrivilege(privilege);

                        sysRolePrivilegeService.save(entity);
                    }
                }
			}
		}
	}

    /**
     * 取得角色名称字符串
     *
     * @param set
     * @return
     */
    public String getRoleNamesAsString(Collection<SysRole> set) {
        String ret = "";

        if(set != null && set.size() > 0) {
            for(SysRole role : set) {
                ret += role.getRoleName() + ",";
            }

            ret = StringHelper.removeLastLetter(ret);
        }

        return ret;
    }

    /**
     * 取得角色Id拼接的字符串
     *
     * @param set
     * @return
     */
    public String getRoleIdsAsString(Collection<SysRole> set) {
        String ret = "";

        if(set != null && set.size() > 0) {
            for(SysRole role : set) {
                ret += role.getId().toString() + ",";
            }

            ret = StringHelper.removeLastLetter(ret);
        }

        return ret;
    }

    /**
     * 取得所有角色
     *
     * @return
     */
    public List<SysRole> getAllRoles() {
        return sysRoleService.find("from SysRole order by code asc");
    }

    /**
     * 删除角色<br/>
     * <li>1.删除角色-用户关联信息</li>
     * <li>2.删除角色-权限关联信息</li>
     * @param id
     */
    public void delete(Long id) {
        // 1.删除角色-用户关联信息
        String hql = "from SysUserRole t where t.role.id=" + id;

        List<SysUserRole> list = sysUserRoleService.find(hql);

        for(SysUserRole userRole : list) {
            sysUserRoleService.delete(userRole);
        }

        // 2.删除角色-权限关联信息
        hql = "from SysRolePrivilege t where t.role.id=" + id;

        List<SysRolePrivilege> roleList = sysRolePrivilegeService.find(hql);

        for(SysRolePrivilege rolePrivilege : roleList) {
            sysRolePrivilegeService.delete(rolePrivilege);
        }

        // 删除角色
        sysRoleService.delete(id);
    }

    /**
     * 取系统角色
     * @param roleCode
     * @return
     */
    public SysRole getRole(String roleCode) {
        return sysRoleService.findUnique("from SysRole where code ='"+roleCode+"'");
    }
}