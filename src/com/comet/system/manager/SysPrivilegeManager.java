package com.comet.system.manager;

import com.comet.system.daoservice.SysPrivilegeService;
import com.comet.system.daoservice.SysRolePrivilegeService;
import com.comet.system.daoservice.SysUserPrivilegeService;
import com.comet.system.domain.SysPrivilege;
import com.comet.core.security.privilege.Privilege;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.system.domain.SysRolePrivilege;
import com.comet.system.domain.SysUserPrivilege;
import com.comet.system.domain.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Project:bcscmis
 *
 * <p>
 * 权限管理逻辑处理类
 * </p>
 *
 * Create On 2010-1-16 下午12:49:24
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Service
public class SysPrivilegeManager {
	@Autowired
	private SysPrivilegeService sysPrivilegeService;

    @Autowired
	private SysUserPrivilegeService sysUserPrivilegeService;

    @Autowired
    private SysRolePrivilegeService sysRolePrivilegeService;

	/**
	 * 取得所有的权限定义信息
	 *
	 * @return 权限列表
	 */
	public List<SysPrivilege> getAllPrivileges() {
		String hql = "from SysPrivilege order by treeId asc";
		List<SysPrivilege> list = sysPrivilegeService.find(hql);

		List<SysPrivilege> ret = new ArrayList<SysPrivilege>();

		if (list != null && list.size() > 0) {
			for (SysPrivilege p : list) {
				p.setPrivilegeType();

				ret.add(p);
			}
		}

		return ret;
	}

	/**
	 * 取得所有的按钮权限
	 *
	 * @param gridId
	 *            gridId
	 * @return 权限Map
	 */
	public Map<String, Privilege> getButtonPrivileges(String gridId) {
		List<Privilege> list = SpringSecurityUtils.getButtonPrivileges();

		Map<String, Privilege> ret = new HashMap<String, Privilege>();

		if (list != null && list.size() > 0) {
			for (Privilege p : list) {
				if (p.getCode().toLowerCase().indexOf(
						gridId.toLowerCase() + ".") != -1) {
					ret.put(p.getCode(), p);
				}
			}
		}

		return ret;
	}

	/**
	 * 取得用户按钮权限
	 *
	 * @param gridId
	 *            gridId
	 * @return 用户按钮权限列表
	 */
	public Map<String, Privilege> getUserButtonPrivileges(String gridId) {
		List<Privilege> list = SpringSecurityUtils.getUserButtonPrivileges();

		Map<String, Privilege> ret = new HashMap<String, Privilege>();

		if (list != null && list.size() > 0) {
			for (Privilege p : list) {
				if (p.getCode().toLowerCase().indexOf(
						gridId.toLowerCase() + ".") != -1) {
					ret.put(p.getCode(), p);
				}
			}
		}

		return ret;
	}

	/**
	 * 取得菜单权限
	 *
	 * @return 菜单权限Map
	 */
	public Map<String, Privilege> getMenuPrivilegesMap() {
		Map<String, Privilege> ret = SpringSecurityUtils.getMenuPrivilegesMap();

		return ret;
	}

	/**
	 * 取得菜单权限
	 *
	 * @return 菜单权限List
	 */
	public List<Privilege> getMenuPrivileges() {
		return SpringSecurityUtils.getMenuPrivileges();
	}

	/**
	 * 取得用户菜单权限
	 *
	 * @return 菜单权限Map
	 */
	public Map<String, Privilege> getUserMenuPrivilegesMap() {
		return SpringSecurityUtils.getUserMenuPrivilegesMap();
	}

	/**
	 * 通过权限代码取得权限
	 *
	 * @param code
	 *            权限代码
	 * @return SysPrivilege
	 */
	public SysPrivilege getPrivilegesByCode(String code) {
		String hql = "from SysPrivilege t where t.code = '" + code + "'";

		List<SysPrivilege> list = sysPrivilegeService.find(hql);

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

    /**
     * 判断用户是否有该权限
     * @param privilegeCode
     * @return
     */
    public boolean hasPrivilegeByCode(String privilegeCode){
        Collection<Privilege> privileges = SpringSecurityUtils.getUserAllPrivileges();
        for (Privilege privilege : privileges) {
            if(privilege.getCode().equals(privilegeCode)){
                return true;
            }
        }
        return false;
    }

    /**
     * 删除授权资源<br/>
     * <li>1.删除人员-授权资源关联信息</li>
     * <li>2.删除角色-授权资源关联信息</li>
     * @param id
     */
    public void delete(Long id) {
        // 1.删除人员-授权资源关联信息
        String hql = "from SysUserPrivilege t where t.privilege.id=" + id;

        List<SysUserPrivilege> list = sysUserPrivilegeService.find(hql);

        for(SysUserPrivilege userPrivilege : list) {
            sysUserPrivilegeService.delete(userPrivilege);
        }

        // 2.删除角色-权限关联信息
        hql = "from SysRolePrivilege t where t.privilege.id=" + id;

        List<SysRolePrivilege> roleList = sysRolePrivilegeService.find(hql);

        for(SysRolePrivilege rolePrivilege : roleList) {
            sysRolePrivilegeService.delete(rolePrivilege);
        }

        // 删除授权资源
        sysPrivilegeService.delete(id);
    }
}